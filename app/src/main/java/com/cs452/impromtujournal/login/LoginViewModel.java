package com.cs452.impromtujournal.login;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.TestData;
import com.cs452.impromtujournal.model.objects.User;
import com.cs452.impromtujournal.repositories.DjangoUsersRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<List<User>> userMutableLiveData;
    private MutableLiveData<PostResponse> postUserMutableLiveData;
    private DjangoUsersRepository djangoUsersRepository;

    LoginViewModel() {
        djangoUsersRepository = DjangoUsersRepository.getInstance();
        userMutableLiveData = djangoUsersRepository.getUsers();
    }

    LiveData<List<User>> getLocationsLiveData() {
        if (TestData.USE_FIREBASE) {
            // TODO return firebase instance
        }
        return userMutableLiveData;
    }

    LiveData<PostResponse> saveUser(User user) {
        return djangoUsersRepository.postUser(user);
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
