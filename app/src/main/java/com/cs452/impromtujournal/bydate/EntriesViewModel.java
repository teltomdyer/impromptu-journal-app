package com.cs452.impromtujournal.bydate;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.TestData;
import com.cs452.impromtujournal.repositories.DjangoEntriesRepository;
import com.cs452.impromtujournal.repositories.FirebaseEntriesRepository;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EntriesViewModel extends ViewModel {
    private MutableLiveData<List<Entry>> mutableLiveData;
    private DjangoEntriesRepository djangoEntriesRepository;

    EntriesViewModel() {
        djangoEntriesRepository = DjangoEntriesRepository.getInstance();
        mutableLiveData = djangoEntriesRepository.getEntries();
    }

    LiveData<List<Entry>> getEntriesLiveData() {
        if (TestData.USE_FIREBASE)
            return new FirebaseEntriesRepository(FirebaseDatabase.getInstance().getReference("/entries"));
        return mutableLiveData;
    }

    public MutableLiveData<PostResponse> postEntry(Entry entry) {
        return djangoEntriesRepository.postEntry(entry);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        Factory() {

        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new EntriesViewModel();
        }
    }
}
