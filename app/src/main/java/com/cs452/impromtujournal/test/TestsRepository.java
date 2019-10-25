package com.cs452.impromtujournal.test;

import android.util.Log;

import com.cs452.impromtujournal.IJNetworkService;
import com.cs452.impromtujournal.IJService;
import com.cs452.impromtujournal.test.model.test.PostTestResponse;
import com.cs452.impromtujournal.test.model.test.Test;
import com.cs452.impromtujournal.test.model.test.TestResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class TestsRepository {
    public final String TAG = "TEST_REPOSITORY";

    private IJService ijService;
    private static TestsRepository instance;
    private final MutableLiveData<List<Test>> testsData = new MutableLiveData<>();

    private List<Test> tests = new ArrayList<>();

    private TestsRepository() {
        this.ijService = new IJNetworkService().ijService;
    }

    static TestsRepository getInstance() {
        if (instance == null)
            instance = new TestsRepository();
        return instance;
    }

    MutableLiveData<List<Test>> getTests() {
        ijService.getTests()
            .enqueue(new Callback<TestResponse>() {
                @Override
                public void onResponse(@NotNull Call<TestResponse> call, @NotNull Response<TestResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, response.toString());
                        tests = response.body().tests;
                        testsData.setValue(tests);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<TestResponse> call, @NotNull Throwable t) {
                    Log.d(TAG, t.getLocalizedMessage());
                }
            });

        return testsData;
    }

    void saveTest(Test test) {
        ijService.postTests(test)
                .enqueue(new Callback<PostTestResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<PostTestResponse> call, @NotNull Response<PostTestResponse> response) {
                        if (response.isSuccessful()) {
                            tests.add(test);
                            testsData.setValue(tests);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PostTestResponse> call, @NotNull Throwable t) {
                        Log.d(TAG, t.getLocalizedMessage());
                    }
                });
    }

}
