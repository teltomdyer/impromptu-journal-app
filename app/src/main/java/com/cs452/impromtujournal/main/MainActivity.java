package com.cs452.impromtujournal.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.bydate.ByDateFragment;
import com.cs452.impromtujournal.bylocation.ByLocationFragment;
import com.cs452.impromtujournal.bylocation.ByLocationViewModel;
import com.cs452.impromtujournal.databinding.ActivityMainBinding;
import com.cs452.impromtujournal.test.TestFragment;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel = new MainViewModel("by_date");
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(mainViewModel);
        binding.setCallback(new MenuClickCallback());

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            Fragment fragment = new ByDateFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, TestFragment.TAG).commit();
        }
    }

    public class MenuClickCallback {
        public void onClick(String fragmentName) {
            Log.d("MAIN_ACTIVITY", "ONCLICK");
                Fragment fragment = null;
                if (StringUtils.equals(fragmentName, "by_date"))
                    fragment = new ByDateFragment();
                else if (StringUtils.equals(fragmentName, "by_location"))
                    fragment = new ByLocationFragment();

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, fragmentName).commit();
                    mainViewModel.setFragment(fragmentName);
                    binding.setModel(mainViewModel);
                }
        }
    }
}
