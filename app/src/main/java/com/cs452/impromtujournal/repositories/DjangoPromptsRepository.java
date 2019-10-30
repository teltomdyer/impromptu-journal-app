package com.cs452.impromtujournal.repositories;

import android.util.Log;

import com.cs452.impromtujournal.IJNetworkService;
import com.cs452.impromtujournal.IJService;
import com.cs452.impromtujournal.model.api.GetPromptsResponse;
import com.cs452.impromtujournal.model.objects.Prompt;
import com.cs452.impromtujournal.model.objects.TestData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DjangoPromptsRepository {
    public final String TAG = "PROMPTS_REPOSITORY";

    private IJService ijService;
    private static DjangoPromptsRepository instance;
    private final MutableLiveData<List<Prompt>> promptsData = new MutableLiveData<>();

    private List<Prompt> prompts = new ArrayList<>();

    private DjangoPromptsRepository() {
        this.ijService = new IJNetworkService().ijService;
    }

    public static DjangoPromptsRepository getInstance() {
        if (instance == null)
            instance = new DjangoPromptsRepository();
        return instance;
    }

    public MutableLiveData<List<Prompt>> getPrompts() {
        if (TestData.TESTING) {
            promptsData.setValue(TestData.promptList);
            return promptsData;
        }

        ijService.getPrompts()
                .enqueue(new Callback<GetPromptsResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<GetPromptsResponse> call, @NotNull Response<GetPromptsResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, response.toString());
                            prompts = response.body().promptList;
                            promptsData.setValue(prompts);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<GetPromptsResponse> call, @NotNull Throwable t) {
                        Log.d(TAG, t.getLocalizedMessage());
                    }
                });

        return promptsData;
    }
}