package com.cs452.impromtujournal.compose;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.Prompt;
import com.cs452.impromtujournal.model.objects.TestData;
import com.cs452.impromtujournal.repositories.DjangoEntriesRepository;
import com.cs452.impromtujournal.repositories.DjangoPromptsRepository;
import com.cs452.impromtujournal.repositories.FirebaseEntriesRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ComposeViewModel extends ViewModel {
    private MutableLiveData<List<Prompt>> mutableLiveData;
    private DjangoPromptsRepository djangoPromptsRepository;
    private DjangoEntriesRepository djangoEntriesRepository;

    ComposeViewModel() {
        djangoPromptsRepository = DjangoPromptsRepository.getInstance();
        djangoEntriesRepository = DjangoEntriesRepository.getInstance();
        mutableLiveData = djangoPromptsRepository.getPrompts();
    }

    public LiveData<List<Prompt>> getPromptsLiveData() {
        return mutableLiveData;
    }

    public LiveData<PostResponse> postEntry(Entry entry) {
        if (TestData.USE_FIREBASE) {
            DatabaseReference entryReference = FirebaseDatabase.getInstance().getReference("/entries");
            return new FirebaseEntriesRepository(entryReference).postEntry(entryReference, entry);
        }
        return djangoEntriesRepository.postEntry(entry);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        public Factory() {

        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ComposeViewModel();
        }
    }
}
