package com.cs452.impromtujournal.bylocation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentByLocationBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ByLocationFragment extends Fragment {

    public static final String TAG = "BY_DATE_FRAGMENT";
    private LocationListAdapter locationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentByLocationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_by_location, container, false);

        locationListAdapter = new LocationListAdapter();
        RecyclerView recyclerView = binding.locationsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(locationListAdapter);

        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        ByLocationViewModel entriesViewModel = ViewModelProviders.of(this, new ByLocationViewModel.Factory()).get(ByLocationViewModel.class);
        entriesViewModel.getLocationsLiveData().observe(this, this::updateUi);
    }

    private void updateUi(List<LocationListItem> locationListItems) {
        locationListItems.sort((o1, o2) -> o1.city.compareTo(o2.city));
        locationListAdapter.setLocationList(locationListItems);
    }
}
