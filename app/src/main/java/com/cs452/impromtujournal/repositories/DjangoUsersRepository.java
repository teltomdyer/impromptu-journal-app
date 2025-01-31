package com.cs452.impromtujournal.repositories;

import android.security.keystore.UserPresenceUnavailableException;
import android.util.Log;

import com.cs452.impromtujournal.IJNetworkService;
import com.cs452.impromtujournal.IJService;
import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.User;
import com.cs452.impromtujournal.model.api.GetUsersResponse;
import com.cs452.impromtujournal.model.objects.TestData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DjangoUsersRepository {
    public final String TAG = "ENTRIES_REPOSITORY";

    private IJService ijService;
    private static DjangoUsersRepository instance;
    private final MutableLiveData<List<User>> usersData = new MutableLiveData<>();
    private final MutableLiveData<PostResponse> postUserData = new MutableLiveData<>();

    private List<User> users = new ArrayList<>();

    private DjangoUsersRepository() {
        this.ijService = new IJNetworkService().ijService;
    }

    public static DjangoUsersRepository getInstance() {
        if (instance == null)
            instance = new DjangoUsersRepository();
        return instance;
    }

    public MutableLiveData<List<User>> getUsers() {
        if (TestData.TESTING) {
            usersData.setValue(TestData.userList);
            return usersData;
        }

        ijService.getUsers()
                .enqueue(new Callback<GetUsersResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<GetUsersResponse> call, @NotNull Response<GetUsersResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, response.toString());
                            users = response.body().userList;
                            usersData.setValue(users);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<GetUsersResponse> call, @NotNull Throwable t) {
                        Log.d(TAG, t.getLocalizedMessage());
                    }
                });

        return usersData;
    }

    public MutableLiveData<PostResponse> postUser(User user) {
        ijService.postUser(user)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<PostResponse> call, @NotNull Response<PostResponse> response) {
                        if (response.isSuccessful()) {
                            postUserData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PostResponse> call, @NotNull Throwable t) {
                        Log.d(TAG, t.getLocalizedMessage());
                    }
                });

        return postUserData;
    }
}