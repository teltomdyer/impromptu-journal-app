package com.cs452.impromtujournal.compose;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentComposeBinding;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.Prompt;

import java.util.List;
import java.util.Random;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ComposeFragment extends Fragment {

    private List<Prompt> promptList;
    private FragmentComposeBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_compose, container, false);
        binding.setCallback(new SaveEntryCallback());
        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        ComposeViewModel composeViewModel = ViewModelProviders.of(this, new ComposeViewModel.Factory()).get(ComposeViewModel.class);
        composeViewModel.getPromptsLiveData().observe(this, promptList -> {
            this.promptList = promptList;
            if (binding.getPrompt() == null) {
                Random random = new Random();
                int promptId = random.nextInt(promptList.size());
                binding.setPrompt(promptList.get(promptId));
            }
        });
    }

    public class SaveEntryCallback {
        public void onClick(Entry entry, Prompt prompt) {
            entry.setPromptId(prompt.getPromptId());
            //TODO save prompt
        }
    }

}
