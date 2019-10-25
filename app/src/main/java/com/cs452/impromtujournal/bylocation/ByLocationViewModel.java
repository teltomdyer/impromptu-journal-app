package com.cs452.impromtujournal.bylocation;

import com.cs452.impromtujournal.repositories.DjangoEntriesRepository;
import com.cs452.impromtujournal.model.Entry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ByLocationViewModel extends ViewModel {
    private MutableLiveData<List<LocationListItem>> mutableLiveData;
    private DjangoEntriesRepository djangoEntriesRepository;

    ByLocationViewModel() {
        djangoEntriesRepository = DjangoEntriesRepository.getInstance();
        mutableLiveData = new MutableLiveData<>();
    }

    LiveData<List<LocationListItem>> getLocationsLiveData() {
        return Transformations.map(djangoEntriesRepository.getEntries(), entries -> {
            Map<String, List<Entry>> locationMap = new HashMap<>();
            for (Entry entry : entries) {
                if (!locationMap.containsKey(entry.getCity()))
                    locationMap.put(entry.getCity(), new ArrayList<>());
                locationMap.get(entry.getCity()).add(entry);
            }

            List<LocationListItem> locationListItems = new ArrayList<>();
            for (String key : locationMap.keySet()) {
                locationListItems.add(new LocationListItem(key, locationMap.get(key)));
            }

            return locationListItems;
        });
    }

//    public void saveEntry(Entry entry) {
//        djangoEntriesRepository.saveEntry(entry);
//    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        Factory() {

        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ByLocationViewModel();
        }
    }
}
