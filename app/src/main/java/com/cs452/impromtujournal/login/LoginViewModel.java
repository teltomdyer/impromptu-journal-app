package com.cs452.impromtujournal.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.TestData;
import com.cs452.impromtujournal.model.objects.User;
import com.cs452.impromtujournal.repositories.DjangoUsersRepository;
import com.cs452.impromtujournal.repositories.FirebaseUsersRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<List<User>> userMutableLiveData;
    private MutableLiveData<PostResponse> postUserMutableLiveData;
    private DjangoUsersRepository djangoUsersRepository;

    LoginViewModel() {
        djangoUsersRepository = DjangoUsersRepository.getInstance();
        userMutableLiveData = djangoUsersRepository.getUsers();
    }

    public LiveData<List<User>> getUsersLiveData() {
        if (TestData.USE_FIREBASE) {
            return new FirebaseUsersRepository(FirebaseDatabase.getInstance().getReference("/users"));
        }
        return userMutableLiveData;
    }

    public LiveData<PostResponse> saveUser(User user) {
        if (TestData.USE_FIREBASE) {
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("/users");
            return new FirebaseUsersRepository(userReference).postUser(userReference, user);
        }
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
