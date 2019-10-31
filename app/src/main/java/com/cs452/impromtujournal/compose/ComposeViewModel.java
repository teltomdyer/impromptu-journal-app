package com.cs452.impromtujournal.compose;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.objects.Prompt;
import com.cs452.impromtujournal.repositories.DjangoEntriesRepository;
import com.cs452.impromtujournal.repositories.DjangoPromptsRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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

    public MutableLiveData<PostResponse> postEntry(Entry entry) {
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
