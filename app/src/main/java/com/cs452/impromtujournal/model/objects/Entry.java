package com.cs452.impromtujournal.model.objects;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;

public class Entry {
    private String entryId;
    private String entryContent;
    private String city = "provo";
    private String timestamp;
    private String promptId;
    private String username;

    public Entry() {
        this.entryId = UUID.randomUUID().toString();
    }

    public Entry(String entryId,
                 String entryContent,
                 String city,
                 String timestamp,
                 String promptId,
                 String username) {
        this.entryId = entryId;
        this.entryContent = entryContent;
        this.city = city;
        this.timestamp = timestamp;
        this.promptId = promptId;
        this.username = username;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIsoTime() {
        int plusIndex = timestamp.indexOf("+");
        if (plusIndex > -1) {
            timestamp = timestamp.substring(0, plusIndex);
        }

        String timestamp1 = timestamp;
        if (!timestamp.contains("Z")) {
            timestamp1 = timestamp + "Z";
        }
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        return sdfDate.format(Instant.parse(timestamp1).toEpochMilli());
    }

    public String getPromptId() {
        return promptId;
    }

    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class PostUserResponse {
        public Boolean success;
    }
}

