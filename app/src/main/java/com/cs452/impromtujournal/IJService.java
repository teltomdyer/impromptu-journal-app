package com.cs452.impromtujournal;

import com.cs452.impromtujournal.model.api.GetPromptsResponse;
import com.cs452.impromtujournal.model.objects.Entry;
import com.cs452.impromtujournal.model.api.GetEntriesResponse;
import com.cs452.impromtujournal.model.api.GetUsersResponse;
import com.cs452.impromtujournal.model.objects.User;
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

    @GET("journal/entries/")
    Call<GetEntriesResponse> getEntries();

    @GET("journal/users/")
    Call<GetUsersResponse> getUsers();

    @POST("journal/users/")
    Call<Entry.PostUserResponse> postUser(@Body User user);

    @GET("journal/prompts/")
    Call<GetPromptsResponse> getPrompts();

}
