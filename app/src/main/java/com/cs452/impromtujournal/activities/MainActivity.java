package com.cs452.impromtujournal.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.login.LoginFragment;
import com.cs452.impromtujournal.model.objects.TestData;
import com.cs452.impromtujournal.test.TestFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        // Add project list fragment if this is first creation
        TestData.populateFirebase();
        if (savedInstanceState == null) {
//            Fragment fragment = new MainFragment(getSupportFragmentManager());
            Fragment fragment = new LoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, TestFragment.TAG).commit();
        }
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
