package com.cs452.impromtujournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cs452.impromtujournal.test.TestFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            TestFragment fragment = new TestFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, TestFragment.TAG).commit();
        }
    }
}
