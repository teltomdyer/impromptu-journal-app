package com.cs452.impromtujournal.model;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static final boolean TESTING = true;
    public static List<Entry> entryList = new ArrayList<>();
    static {
        entryList.add(new Entry("1", "First journal entry", "Provo", 1239567891234L, "prompt1", "user1"));
        entryList.add(new Entry("2", "Second journal entry", "Provo", 1234567892234L, "prompt2", "user1"));
        entryList.add(new Entry("3", "Third journal entry that is very long and wont fit across the screen if it isn't cut", "Provo", 1238567893234L, "prompt3", "user1"));
        entryList.add(new Entry("4", "Fourth journal entry", "Salt Lake", 1234567894234L, "prompt1", "user1"));
        entryList.add(new Entry("5", "Fifth journal entry", "Highland", 1237567891534L, "prompt2", "user1"));
    }
}
