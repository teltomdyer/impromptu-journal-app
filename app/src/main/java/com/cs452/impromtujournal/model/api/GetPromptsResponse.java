package com.cs452.impromtujournal.model.api;

import com.cs452.impromtujournal.model.objects.Prompt;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPromptsResponse {
    @SerializedName("Prompts")
    public List<Prompt> promptList;
}
