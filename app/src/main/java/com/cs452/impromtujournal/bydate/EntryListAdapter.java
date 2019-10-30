package com.cs452.impromtujournal.bydate;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.ListItemEntryBinding;
import com.cs452.impromtujournal.main.EntryDialogController;
import com.cs452.impromtujournal.model.objects.Entry;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.EntryViewHolder> implements Filterable {

    List<Entry> baseList;
    List<Entry> entryList;
    EntryDialogController.EntryClickCallback entryClickCallback;

    public EntryListAdapter(EntryDialogController.EntryClickCallback entryClickCallback) {
        this.entryClickCallback = entryClickCallback;
    }

    public void setEntryList(List<Entry> entryList) {
        Log.d("ENTRY_LIST_ADAPTER", "Updated list: " + entryList.size());
        this.baseList = entryList;
        this.entryList = entryList;
        notifyDataSetChanged();
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemEntryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item_entry, parent, false);

//        binding.setCallback(clickCallback);
        return new EntryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.binding.setEntry(entryList.get(position));
        holder.binding.setCallback(entryClickCallback);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return entryList == null ? 0 : entryList.size();
    }



    public class EntryViewHolder extends RecyclerView.ViewHolder {
        final ListItemEntryBinding binding;

        public EntryViewHolder(ListItemEntryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private ValueFilter valueFilter;
    @Override
    public android.widget.Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            entryList = baseList;

            if (!StringUtils.isEmpty(constraint)) {
                List<Entry> filterList = new ArrayList<>();
                for (int i = 0; i < entryList.size(); i++) {
                    if ((entryList.get(i).getEntryContent().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(entryList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = entryList.size();
                results.values = entryList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            entryList = (List<Entry>) results.values;
            notifyDataSetChanged();
        }
    }
}
