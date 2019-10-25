package com.cs452.impromtujournal.activities;

import android.os.Bundle;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.ActivityMainBinding;
import com.cs452.impromtujournal.main.MainFragment;
import com.cs452.impromtujournal.test.TestFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment(getSupportFragmentManager());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, TestFragment.TAG).commit();
        }
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
