package com.buzz.yora.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.buzz.yora.R;
import com.buzz.yora.services.Account;
import com.squareup.otto.Subscribe;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private Callbacks callbacks;
    private View progressBar;
    private EditText userNameText;
    private EditText passwordText;
    private String defaultLoginButtonText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginButton = (Button) view.findViewById(R.id.fragment_login_login_button);
        loginButton.setOnClickListener(this);

        progressBar = view.findViewById(R.id.fragment_login_progress_bar);
        userNameText = (EditText) view.findViewById(R.id.fragment_login_username);
        passwordText = (EditText) view.findViewById(R.id.fragment_login_password);

        defaultLoginButtonText = loginButton.getText().toString();

        return view;
    }

    /*
        This method was deprecated in API level 23.
        Use onAttach(Context) instead.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {

            progressBar.setVisibility(View.VISIBLE);
            loginButton.setText("");
            loginButton.setEnabled(false);
            userNameText.setEnabled(false);
            passwordText.setEnabled(false);

            bus.post(new Account.LoginWithUserNameRequest(
                    userNameText.getText().toString(),
                    passwordText.getText().toString()
            ));
        }
    }

    @Subscribe
    public void onLoginWithUserName(Account.LoginWithUserNameResponse response) {
        response.showErrorToast(getActivity());

        if (response.didSucceed()) {
            callbacks.onLoggedIn();
            return;
        }

        userNameText.setError(response.getPropertyError("userName"));
        userNameText.setEnabled(true);

        passwordText.setError(response.getPropertyError("password"));
        passwordText.setEnabled(true);

        progressBar.setVisibility(View.GONE);
        loginButton.setText(defaultLoginButtonText);
        loginButton.setEnabled(true);
    }

    public interface Callbacks {
        void onLoggedIn();
    }
}
