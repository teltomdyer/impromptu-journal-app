package com.cs452.impromtujournal.main;

public class MainViewModel {
    public String fragment;

    public MainViewModel() {
    }

    public MainViewModel(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
