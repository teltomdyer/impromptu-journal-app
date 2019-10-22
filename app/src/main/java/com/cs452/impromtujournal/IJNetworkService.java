package com.cs452.impromtujournal;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IJNetworkService {
    private final String BASE_URL = "https://arcane-hollows-70997.herokuapp.com/";
    public IJService ijService;

    public IJNetworkService() {
        this.ijService = buildIJService();
    }

    private IJService buildIJService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(new OkHttpClient())
                .build();

        return retrofit.create(IJService.class);
    }
}
