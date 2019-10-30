package com.cs452.impromtujournal.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static final boolean TESTING = false;
    public static final boolean USE_FIREBASE = false;
    public static List<Entry> entryList = new ArrayList<>();
    static {
        entryList.add(new Entry("1oijhsdf9", "First journal entry", "Provo", "test date", "prompt1", "user1"));
        entryList.add(new Entry("9sdjfhui", "Second journal entry", "Provo", "test date", "prompt2", "user1"));
        entryList.add(new Entry("ionwdkln8", "Third journal entry that is very long and wont fit across the screen if it isn't cut", "Provo", "test date", "prompt3", "user1"));
        entryList.add(new Entry("89sdkjfnio", "Fourth journal entry", "Salt Lake", "test date", "prompt1", "user1"));
        entryList.add(new Entry("9sdnjwn", "Fifth journal entry", "Highland", "test date", "prompt2", "user1"));
    }

    public static List<User> userList = new ArrayList<>();

    public static void populateFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/entries");
        for (Entry entry : entryList) {
            reference.child(entry.getEntryId()).setValue(entry);
        }
    }
}
