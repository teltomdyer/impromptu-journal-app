package com.cs452.impromtujournal.main;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.compose.ComposeViewModel;
import com.cs452.impromtujournal.databinding.DialogViewEntryBinding;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.Prompt;

import org.apache.commons.lang3.StringUtils;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EntryDialogController {
    private Context context;
    private Fragment parent;
    private DialogViewEntryBinding binding;

    public EntryDialogController(Context context, Fragment parent) {
        this.context = context;
        this.parent = parent;
    }

    public EntryClickCallback entryClickCallback = new EntryClickCallback();

    public class EntryClickCallback {
        public void onClick(Entry entry) {
            final Dialog dialog = new Dialog(context);
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_view_entry, null, false);
            observeViewModel(entry);
            binding.setEntry(entry);
            binding.setExit((ExitCallback) () -> {
                dialog.dismiss();
            });
            dialog.setContentView(binding.getRoot());
            dialog.setTitle("View entry");
            dialog.show();
        }
    }

    private void observeViewModel(Entry entry) {
        ComposeViewModel composeViewModel = ViewModelProviders.of(parent, new ComposeViewModel.Factory()).get(ComposeViewModel.class);
        composeViewModel.getPromptsLiveData().observe(parent, prompts -> {
            for (Prompt prompt : prompts) {
                if (StringUtils.equals(prompt.getPromptId(), entry.getPromptId()))
                    binding.setPrompt(prompt);
            }
        });
    }

    public interface ExitCallback {
        public void onClick();
    }
}
