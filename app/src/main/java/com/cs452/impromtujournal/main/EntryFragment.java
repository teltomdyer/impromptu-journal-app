package com.cs452.impromtujournal.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.bylocation.LocationListAdapter;
import com.cs452.impromtujournal.databinding.FragmentByLocationBinding;
import com.cs452.impromtujournal.databinding.FragmentEntryBinding;
import com.cs452.impromtujournal.model.Entry;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntryFragment extends Fragment {

    private Entry entry;

    public EntryFragment(Entry entry) {
        this.entry = entry;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentEntryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false);
        binding.setEntry(entry);

        return binding.getRoot();
    }
}
