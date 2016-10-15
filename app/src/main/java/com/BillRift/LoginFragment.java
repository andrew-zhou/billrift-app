package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.BillRift.presenters.LoginPresenter;
import com.BillRift.views.LoginView;
import com.google.android.gms.common.SignInButton;

public class LoginFragment extends MvpFragment<LoginPresenter> implements LoginView {

    private Listener listener;
    private SignInButton signInButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signInButton = (SignInButton)view.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void finishLogin() {
        if(listener != null) {
            listener.goToGroupsActivity();
        }
    }

    public interface Listener {
        void goToGroupsActivity();
    }
}