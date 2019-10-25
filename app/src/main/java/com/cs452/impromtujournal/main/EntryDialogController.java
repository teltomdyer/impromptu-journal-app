package com.cs452.impromtujournal.main;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.DialogViewEntryBinding;
import com.cs452.impromtujournal.model.Entry;

import androidx.databinding.DataBindingUtil;

public class EntryDialogController {
    private Context context;

    public EntryDialogController(Context context) {
        this.context = context;
    }

    public EntryClickCallback entryClickCallback = new EntryClickCallback();

    public class EntryClickCallback {
        public void onClick(Entry entry) {
            final Dialog dialog = new Dialog(context);
            DialogViewEntryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_view_entry, null, false);

            binding.setEntry(entry);
            binding.setExit((ExitCallback) () -> {
                dialog.dismiss();
            });
            dialog.setContentView(binding.getRoot());
            dialog.setTitle("View entry");
            dialog.show();
        }
    }

    public interface ExitCallback {
        public void onClick();
    }
}
