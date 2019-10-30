package com.cs452.impromtujournal.repositories;

import android.util.Log;

import com.cs452.impromtujournal.IJNetworkService;
import com.cs452.impromtujournal.IJService;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.api.GetEntriesResponse;
import com.cs452.impromtujournal.model.objects.TestData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DjangoEntriesRepository {
    public final String TAG = "ENTRIES_REPOSITORY";

        private IJService ijService;
        private static DjangoEntriesRepository instance;
        private final MutableLiveData<List<Entry>> entriesData = new MutableLiveData<>();

        private List<Entry> entries = new ArrayList<>();

        private DjangoEntriesRepository() {
            this.ijService = new IJNetworkService().ijService;
        }

        public static DjangoEntriesRepository getInstance() {
            if (instance == null)
                instance = new DjangoEntriesRepository();
            return instance;
        }

        public MutableLiveData<List<Entry>> getEntries() {
            if (TestData.TESTING) {
                entriesData.setValue(TestData.entryList);
                return entriesData;
            }

            ijService.getEntries()
                    .enqueue(new Callback<GetEntriesResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<GetEntriesResponse> call, @NotNull Response<GetEntriesResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d(TAG, response.toString());
                                entries = response.body().entryList;
                                entriesData.setValue(entries);
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<GetEntriesResponse> call, @NotNull Throwable t) {
                            Log.d(TAG, t.getLocalizedMessage());
                        }
                    });

            return entriesData;
        }

//        void saveEntry(Entry entry) {
//            ijService.postEntries(entry)
//                    .enqueue(new Callback<PostEntriesResponse>() {
//                        @Override
//                        public void onResponse(@NotNull Call<PostEntriesResponse> call, @NotNull Response<PostEntriesResponse> response) {
//                            if (response.isSuccessful()) {
//                                entries.add(entry);
//                                entriesData.setValue(entries);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(@NotNull Call<PostEntriesResponse> call, @NotNull Throwable t) {
//                            Log.d(TAG, t.getLocalizedMessage());
//                        }
//                    });
//        }

}
