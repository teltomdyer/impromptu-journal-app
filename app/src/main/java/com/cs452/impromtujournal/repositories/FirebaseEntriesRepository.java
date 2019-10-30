package com.cs452.impromtujournal.repositories;

import com.cs452.impromtujournal.model.objects.Entry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class FirebaseEntriesRepository extends LiveData<List<Entry>> {
    private static final String LOG_TAG = "FIREBASE_ENTRIES_REPOSITORY";

    private Map<String, Entry> entries = new HashMap<>();

    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    public FirebaseEntriesRepository(Query query) {
        this.query = query;
    }

    public FirebaseEntriesRepository(DatabaseReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        query.addChildEventListener(listener);
    }

    @Override
    protected void onInactive() {
        query.removeEventListener(listener);
    }

    private void update() {
        List<Entry> updatedProjects = new ArrayList<>();
        updatedProjects.addAll(entries.values());
        postValue(updatedProjects);
    }

    private class MyValueEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            if (!dataSnapshot.exists())
                return;
            entries.put(dataSnapshot.getKey(), dataSnapshot.getValue(Entry.class));
            update();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            if (!dataSnapshot.exists())
                return;

            entries.put(dataSnapshot.getKey(), dataSnapshot.getValue(Entry.class));
            update();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            if (!dataSnapshot.exists())
                return;
            entries.remove(dataSnapshot.getKey());
            update();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("Can't listen to query " + query);
        }
    }
}
