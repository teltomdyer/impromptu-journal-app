package com.cs452.impromtujournal.test;

import com.cs452.impromtujournal.test.model.Test;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class TestsViewModel extends ViewModel {
    private MutableLiveData<List<Test>> mutableLiveData;
    private TestsRepository testsRepository;

    private TestsViewModel() {
        testsRepository = TestsRepository.getInstance();
        mutableLiveData = testsRepository.getTests();
    }

    LiveData<List<Test>> getTestsLiveData() {
        return mutableLiveData;
    }

    public void saveTest(Test test) {
        testsRepository.saveTest(test);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        Factory() {

        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TestsViewModel();
        }
    }
}
