package com.cs452.impromtujournal.compose;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentComposeBinding;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.Prompt;
import com.cs452.impromtujournal.model.objects.State;
import com.cs452.impromtujournal.test.model.test.Test;
import com.cs452.impromtujournal.util.TimeUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ComposeFragment extends Fragment {

    private List<Prompt> promptList;
    private FragmentComposeBinding binding;
    private ComposeViewModel composeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_compose, container, false);
        binding.setEntry(new Entry());
        binding.setCallback(new SaveEntryCallback(this));
        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        composeViewModel = ViewModelProviders.of(this, new ComposeViewModel.Factory()).get(ComposeViewModel.class);
        composeViewModel.getPromptsLiveData().observe(this, promptList -> {
            this.promptList = promptList;
            if (binding.getPrompt() == null)
                binding.setPrompt(randomPrompt(promptList));
        });
    }

    public class SaveEntryCallback {
        private Fragment parent;

        public SaveEntryCallback(Fragment parent) {
            this.parent = parent;
        }

        public void onClick(Entry entry, Prompt prompt) {
            if (StringUtils.isEmpty(entry.getEntryContent())) {
                Toast.makeText(getContext(), "Cannot save entry with no content", Toast.LENGTH_LONG).show();
                return;
            }
            entry.setUsername(State.currentUser.getUsername());
            entry.setPromptId(prompt.getPromptId());
            entry.setTimestamp(TimeUtil.nowIsoTime());
            Log.d("NEW_ENTRY", entry.getTimestamp());
            composeViewModel.postEntry(entry).observe(parent, postResponse -> {
                if (postResponse == null) {
                    Toast.makeText(getContext(), "Error saving entry", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (postResponse.success) {
                    Toast.makeText(getContext(), "Entry saved", Toast.LENGTH_LONG).show();
                    sucessfulSave();
                }
            });
        }
    }

    private void sucessfulSave() {
        binding.setEntry(new Entry());
        binding.setPrompt(randomPrompt(promptList));
    }

    private Prompt randomPrompt(List<Prompt> promptList) {
        Random random = new Random();
        int promptId = random.nextInt(promptList.size());
        return promptList.get(promptId);
    }

}
