package com.cs452.impromtujournal;

import com.cs452.impromtujournal.model.GetEntriesResponse;
import com.cs452.impromtujournal.test.model.test.PostTestResponse;
import com.cs452.impromtujournal.test.model.test.Test;
import com.cs452.impromtujournal.test.model.test.TestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IJService {

    @GET("journal/tests/")
    Call<TestResponse> getTests();

    @POST("journal/tests/")
    Call<PostTestResponse> postTests(@Body Test test);


    @GET("journal/entryList/")
    Call<GetEntriesResponse> getEntries();

}
