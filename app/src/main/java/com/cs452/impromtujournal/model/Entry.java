package com.cs452.impromtujournal.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
    private String entryId;
    private String entryContent;
    private String city;
    private Long timestamp;
    private String promptId;
    private String userId;

    public Entry() {
    }

    public Entry(String entryId,
                 String entryContent,
                 String city,
                 Long timestamp,
                 String promptId,
                 String userId) {
        this.entryId = entryId;
        this.entryContent = entryContent;
        this.city = city;
        this.timestamp = timestamp;
        this.promptId = promptId;
        this.userId = userId;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIsoTime() {
        return new SimpleDateFormat("MMM d, yyyy").format(timestamp);
    }

    public String getPromptId() {
        return promptId;
    }

    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

