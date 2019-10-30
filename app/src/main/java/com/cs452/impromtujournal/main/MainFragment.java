package com.cs452.impromtujournal.main;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.bydate.ByDateFragment;
import com.cs452.impromtujournal.bylocation.ByLocationFragment;
import com.cs452.impromtujournal.compose.ComposeFragment;
import com.cs452.impromtujournal.databinding.FragmentMainBinding;
import com.cs452.impromtujournal.login.LoginFragment;
import com.cs452.impromtujournal.test.TestFragment;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class MainFragment extends Fragment {
    private MainFragmentModel mainFragmentModel = new MainFragmentModel("by_date");
    private FragmentMainBinding binding;
    private FragmentManager fragmentManager;

    public MainFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setModel(mainFragmentModel);
        binding.setCallback(new MenuClickCallback());

        Fragment fragment = new ByDateFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container_2, fragment, TestFragment.TAG).commit();


        return binding.getRoot();
    }

    public class MenuClickCallback {
        public void onClick(String fragmentName) {
            Log.d("MAIN_ACTIVITY", "ONCLICK");
            Fragment fragment = null;
            if (StringUtils.equals(fragmentName, "by_date"))
                fragment = new ByDateFragment();
            else if (StringUtils.equals(fragmentName, "by_location"))
                fragment = new ByLocationFragment();
            else if (StringUtils.equals(fragmentName, "compose"))
                fragment = new ComposeFragment();

            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_2, fragment, fragmentName).commit();
                mainFragmentModel.setFragment(fragmentName);
                binding.setModel(mainFragmentModel);
            }
        }
    }
}
