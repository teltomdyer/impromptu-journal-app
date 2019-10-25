package com.cs452.impromtujournal.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentTestBinding;
import com.cs452.impromtujournal.test.model.test.Test;

import org.jetbrains.annotations.NotNull;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TestFragment extends Fragment {

    public static final String TAG = "test";
    private FragmentTestBinding binding;
    private TestsViewModel testsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeViewModel();
    }

    private void observeViewModel() {
        testsViewModel = ViewModelProviders.of(this, new TestsViewModel.Factory()).get(TestsViewModel.class);
        testsViewModel.getTestsLiveData().observe(this, testList -> binding.setModel(testList.get(testList.size() - 1)));
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        binding.setNewTest(new Test());
        binding.setCallback(new OnSaveCallback());
        return binding.getRoot();
    }

    public class OnSaveCallback {
        public void onClick(Test test) {
            Log.d(TAG, "Saving project: " + test.getName());
            testsViewModel.saveTest(test);
            binding.setNewTest(new Test());
        }
    }
}
