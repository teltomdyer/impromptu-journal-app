package com.cs452.impromtujournal.model.api;

import com.cs452.impromtujournal.model.objects.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsersResponse {
    @SerializedName("Users")
    public List<User> userList;
}
