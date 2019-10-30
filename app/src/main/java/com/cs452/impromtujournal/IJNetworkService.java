package com.cs452.impromtujournal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IJNetworkService {
    private final String BASE_URL = "https://impromptu-journal.herokuapp.com/";
    public IJService ijService;

    public IJNetworkService() {
        this.ijService = buildIJService();
    }

    private IJService buildIJService() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient())
                .build();

        return retrofit.create(IJService.class);
    }
}
