package com.cs452.impromtujournal.model.objects;

public class Prompt {
    String promptId;
    String promptContent;

    public Prompt() {
    }

    public Prompt(String promptId, String promptContent) {
        this.promptId = promptId;
        this.promptContent = promptContent;
    }

    public String getPromptId() {
        return promptId;
    }

    public void setPromptId(String promptId) {
        this.promptId = promptId;
    }

    public String getPromptContent() {
        return promptContent;
    }

    public void setPromptContent(String promptContent) {
        this.promptContent = promptContent;
    }
}
