package com.cs452.impromtujournal.model.api;

import com.cs452.impromtujournal.model.objects.Entry;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetEntriesResponse {
    @SerializedName("Entries")
    public List<Entry> entryList;
}
