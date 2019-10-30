package com.cs452.impromtujournal.login;

import com.cs452.impromtujournal.model.User;
import com.cs452.impromtujournal.repositories.DjangoUsersRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<List<User>> mutableLiveData;
    private DjangoUsersRepository djangoUsersRepository;

    LoginViewModel() {
        djangoUsersRepository = DjangoUsersRepository.getInstance();
        mutableLiveData = djangoUsersRepository.getUsers();
    }

    LiveData<List<User>> getLocationsLiveData() {
        return mutableLiveData;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        Factory() {

        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new LoginViewModel();
        }
    }
}
