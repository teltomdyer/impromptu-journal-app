package com.cs452.impromtujournal.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs452.impromtujournal.R;
import com.cs452.impromtujournal.bydate.ByDateFragment;
import com.cs452.impromtujournal.databinding.FragmentByDateBinding;
import com.cs452.impromtujournal.databinding.FragmentByLocationBinding;
import com.cs452.impromtujournal.databinding.FragmentLoginBinding;
import com.cs452.impromtujournal.model.State;
import com.cs452.impromtujournal.model.User;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LoginFragment extends Fragment {

    private List<User> users;
    private Map<String, String> userPasswordMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.setCallback(new LoginCallback());
        binding.setUser(new User());
        observeViewModel();
        return binding.getRoot();
    }

    private void observeViewModel() {
        LoginViewModel loginViewModel = ViewModelProviders.of(this, new LoginViewModel.Factory()).get(LoginViewModel.class);
        loginViewModel.getLocationsLiveData().observe(this, this::updateUi);
    }

    private void updateUi(List<User> users) {
        this.users = users;
        for (User u : users) {
            userPasswordMap.put(u.getUsername(), u.getPassword());
        }
    }

    public class LoginCallback {
        public void loginOnClick(User user) {
            Log.d("LOGIN_FRAGMENT", "Login callback for " + user.getUsername());
            if (!loginUser(user)) {
                Toast.makeText(getContext(), "Username or password incorrect", Toast.LENGTH_LONG).show();
                return;
            }
            Fragment fragment = new ByDateFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_2, fragment, "By Date Fragment").commit();
        }

        public void signUpCallback(User user) {
            if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
                Toast.makeText(getContext(), "Must provide username and password", Toast.LENGTH_LONG).show();
            }


        }
    }

    private boolean loginUser(User user) {
        if (!userPasswordMap.containsKey(user.getUsername()))
            return false;
        State.currentUser = user;
        return StringUtils.equals(user.getPassword(), userPasswordMap.get(user.getUsername()));
    }

}
