package com.cs452.impromtujournal.model.objects;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static final boolean TESTING = false;
    public static final boolean USE_FIREBASE = false;
    public static List<Entry> entryList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Prompt> promptList = new ArrayList<>();

    static {
        entryList.add(new Entry("1oijhsdf9", "First journal entry", "Provo", "test date", "prompt1", "user1"));
        entryList.add(new Entry("9sdjfhui", "Second journal entry", "Provo", "test date", "prompt2", "user1"));
        entryList.add(new Entry("ionwdkln8", "Third journal entry that is very long and wont fit across the screen if it isn't cut", "Provo", "test date", "prompt3", "user1"));
        entryList.add(new Entry("89sdkjfnio", "Fourth journal entry", "Salt Lake", "test date", "prompt1", "user1"));
        entryList.add(new Entry("9sdnjwn", "Fifth journal entry", "Highland", "test date", "prompt2", "user1"));

        userList.add(new User("testuser", "Test", "User", "password", true));
        userList.add(new User("testuser2", "Test2", "User", "password", true));
        userList.add(new User("testuser3", "Test3", "User", "password", true));
    }



    public static void populateFirebase() {
        populateEntries();
        populateUsers();
        populatePrompts();
    }

    public static void populateEntries() {
        DatabaseReference entryReference = FirebaseDatabase.getInstance().getReference("/entries");
        entryReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (Entry entry : entryList) {
                    if (snapshot.hasChild(entry.getEntryId())) {
                        // entry exists
                        System.out.println("Entry " + entry.getEntryId() + " already exists");
                    } else {
                        // entry doesn't exist
                        entryReference.child(entry.getEntryId()).setValue(entry);
                        System.out.println("Entry " + entry.getEntryId() + " added");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void populateUsers() {
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("/users");
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (User user : userList) {
                    if (snapshot.hasChild(user.getUsername())) {
                        // user exists
                        System.out.println("User " + user.getUsername() + " already exists");
                    } else {
                        // user doesn't exist
                        userReference.child(user.getUsername()).setValue(user);
                        System.out.println("User " + user.getUsername() + " added");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void populatePrompts() {

    }
}
