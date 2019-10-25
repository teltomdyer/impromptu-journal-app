package com.cs452.impromtujournal.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static final boolean TESTING = true;
    public static final boolean USE_FIREBASE = true;
    public static List<Entry> entryList = new ArrayList<>();
    static {
        entryList.add(new Entry("1oijhsdf9", "First journal entry", "Provo", 1239567891234L, "prompt1", "user1"));
        entryList.add(new Entry("9sdjfhui", "Second journal entry", "Provo", 1234567892234L, "prompt2", "user1"));
        entryList.add(new Entry("ionwdkln8", "Third journal entry that is very long and wont fit across the screen if it isn't cut", "Provo", 1238567893234L, "prompt3", "user1"));
        entryList.add(new Entry("89sdkjfnio", "Fourth journal entry", "Salt Lake", 1234567894234L, "prompt1", "user1"));
        entryList.add(new Entry("9sdnjwn", "Fifth journal entry", "Highland", 1237567891534L, "prompt2", "user1"));
    }

    public static void populateFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/entries");
        for (Entry entry : entryList) {
            reference.child(entry.getEntryId()).setValue(entry);
        }
    }
}
