package com.cs452.impromtujournal.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs452.impromtujournal.model.api.PostResponse;
import com.cs452.impromtujournal.model.objects.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUsersRepository extends LiveData<List<User>> {
    private static final String LOG_TAG = "FIREBASE_USERS_REPOSITORY";

    private Map<String, User> users = new HashMap<>();

    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    public FirebaseUsersRepository(Query query) {
        this.query = query;
    }

    public FirebaseUsersRepository(DatabaseReference ref) {
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
        List<User> updatedProjects = new ArrayList<>();
        updatedProjects.addAll(users.values());
        postValue(updatedProjects);
    }

    public LiveData<PostResponse> postUser(DatabaseReference userReference, User user) {
        userReference.child(user.getUsername()).setValue(user);
        PostResponse postResponse = new PostResponse();
        postResponse.success = true;
        MutableLiveData liveData = new MutableLiveData<PostResponse>();
        liveData.setValue(postResponse);
        return liveData;
    }

    private class MyValueEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            if (!dataSnapshot.exists())
                return;
            users.put(dataSnapshot.getKey(), dataSnapshot.getValue(User.class));
            update();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            if (!dataSnapshot.exists())
                return;

            users.put(dataSnapshot.getKey(), dataSnapshot.getValue(User.class));
            update();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            if (!dataSnapshot.exists())
                return;
            users.remove(dataSnapshot.getKey());
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
