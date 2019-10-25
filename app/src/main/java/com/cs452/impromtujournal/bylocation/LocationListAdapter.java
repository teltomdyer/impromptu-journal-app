package com.cs452.impromtujournal.bylocation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.ListItemLocationBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    List<LocationListItem> baseList;
    List<LocationListItem> locationList;
//    ProjectClickCallback clickCallback;

    public LocationListAdapter() {
//        this.clickCallback = clickCallback;
    }

    public void setLocationList(List<LocationListItem> locationList) {
        Log.d("ENTRY_LIST_ADAPTER", "Updated list: " + locationList.size());
        this.baseList = locationList;
        this.locationList = locationList;
        notifyDataSetChanged();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemLocationBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item_location, parent, false);

//        binding.setCallback(clickCallback);
        return new LocationListAdapter.LocationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(LocationListAdapter.LocationViewHolder holder, int position) {
        holder.binding.setLocationListItem(locationList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return locationList == null ? 0 : locationList.size();
    }



    public class LocationViewHolder extends RecyclerView.ViewHolder {
        final ListItemLocationBinding binding;

        public LocationViewHolder(ListItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
