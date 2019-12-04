package com.cs452.impromtujournal.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.databinding.FragmentLoginBinding;
import com.cs452.impromtujournal.main.MainFragment;
import com.cs452.impromtujournal.model.objects.State;
import com.cs452.impromtujournal.model.objects.User;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginFragment extends Fragment {

    private List<User> users;
    private Map<String, String> userPasswordMap = new HashMap<>();
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.setCallback(new LoginCallback(this));
        binding.setUser(new User());
        binding.setModel(new LoginModel(binding));
        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        loginViewModel = ViewModelProviders.of(this, new LoginViewModel.Factory()).get(LoginViewModel.class);
        loginViewModel.getUsersLiveData().observe(this, this::updateUi);
    }

    private void updateUi(List<User> users) {
        this.users = users;
        for (User u : users) {
            userPasswordMap.put(u.getUsername(), u.getPassword());
        }
    }

    public class LoginCallback {

        private Fragment parent;

        public LoginCallback(Fragment parent) {
            this.parent = parent;
        }

        public void loginOnClick(User user) {
            // When the Login button is clicked, gets binding data from the UI for the proposed user

            // loginUser is used to check if the user exists and is authorized to login
            if (!loginUser(user)) {
                Toast.makeText(getContext(), "Username or password incorrect", Toast.LENGTH_LONG).show();
                return;
            }

            // If the user exists and is authorized, login the existing user
            logUserIn(user);
        }

        public void signUpCallback(User user) {
            if (StringUtils.isEmpty(user.getUsername()) ||
                    StringUtils.isEmpty(user.getPassword()) ||
                    StringUtils.isEmpty(user.getFirstName()) ||
                    StringUtils.isEmpty(user.getLastName())) {

                Toast.makeText(getContext(), "Must provide all fields", Toast.LENGTH_LONG).show();
                return;
            }

            loginViewModel.saveUser(user).observe(parent, postResponse -> {
                if (postResponse != null && postResponse.success) {
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
                    logUserIn(user);
                }
                else
                    Toast.makeText(getContext(), "Error creating user", Toast.LENGTH_LONG).show();
            });
        }
    }

    private void logUserIn(User user) {
        State.currentUser = user;
        Activity activity = getActivity();
        if (activity == null) {
            Toast.makeText(getContext(), "Can't get activity", Toast.LENGTH_LONG).show();
            return;
        }

        // The user is authenticated, move to the main app with that the currentUser set
        Fragment fragment = new MainFragment(getActivity().getSupportFragmentManager());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "By Date Fragment").commit();
    }

    private boolean loginUser(User user) {
        // Check if username exists

        if (!userPasswordMap.containsKey(user.getUsername())) {
            // If the username doesn't even exist, return false
            return false;
        }

        // If the username exists, check if the password is correct.
        return StringUtils.equals(user.getPassword(), userPasswordMap.get(user.getUsername()));
    }

    public class LoginModel {
        private FragmentLoginBinding binding;
        private String toggleState = "login";

        public LoginModel(FragmentLoginBinding binding) {
            this.binding = binding;
        }

        public String getToggleState() {
            return toggleState;
        }

        public void setToggleState(String toggleState) {
            this.toggleState = toggleState;
            binding.setModel(this);
        }
    }

}
