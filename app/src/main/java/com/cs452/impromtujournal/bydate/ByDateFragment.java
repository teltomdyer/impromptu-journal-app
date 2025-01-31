package com.cs452.impromtujournal.bydate;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentByDateBinding;
import com.cs452.impromtujournal.main.EntryDialogController;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.State;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ByDateFragment extends Fragment {

    public static final String TAG = "BY_DATE_FRAGMENT";
    private EntryListAdapter entryListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentByDateBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_by_date, container, false);

        entryListAdapter = new EntryListAdapter(new EntryDialogController(getContext(), this).entryClickCallback);
        RecyclerView recyclerView = binding.entriesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(entryListAdapter);

        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        EntriesViewModel entriesViewModel = ViewModelProviders.of(this, new EntriesViewModel.Factory()).get(EntriesViewModel.class);
        entriesViewModel.getEntriesLiveData().observe(this, this::updateUi);
    }

    private void updateUi(List<Entry> entryList) {
        List<Entry> sortedFiltered = new ArrayList<>();
        for (Entry entry : entryList) {
            if (StringUtils.equals(entry.getUsername(), State.currentUser.getUsername()))
                sortedFiltered.add(entry);
        }
        sortedFiltered.sort((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
        entryListAdapter.setEntryList(sortedFiltered);
    }
}
