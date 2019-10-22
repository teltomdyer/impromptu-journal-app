package com.cs452.impromtujournal;

import com.cs452.impromtujournal.test.model.PostTestResponse;
import com.cs452.impromtujournal.test.model.Test;
import com.cs452.impromtujournal.test.model.TestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IJService {

    @GET("journal/tests/")
    Call<TestResponse> getTests();

    @POST("journal/tests/")
    Call<PostTestResponse> postTests(@Body Test test);

}
