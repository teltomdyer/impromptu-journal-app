package com.cs452.impromtujournal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetEntriesResponse {
    @SerializedName("Entries")
    public List<Entry> entryList;
}
