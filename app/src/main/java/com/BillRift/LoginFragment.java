package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.BillRift.models.User;
import com.BillRift.presenters.LoginPresenter;
import com.BillRift.views.LoginView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginFragment extends MvpFragment<LoginPresenter> implements LoginView {

    private Listener listener;
    private SignInButton signInButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signInButton = (SignInButton)view.findViewById(R.id.signInButton);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gsio = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gsio.
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage((FragmentActivity) getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.w("GoogleSignIn", "Connection failed: " + connectionResult);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsio)
                .build();
        presenter.setGoogleApiClient(googleApiClient);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gsio.getScopeArray());
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
    public void startGoogleLogin(GoogleApiClient googleApiClient) {
        if(listener != null) {
            listener.goToGoogleLogin(googleApiClient);
        }
    }

    @Override
    public void goToGroupsView() {
        if(listener != null) {
            listener.goToGroupsView();
        }
    }

    @Override
    public void showGroupErrorMessage(String message) {
        if(listener != null) {
            listener.showGroupErrorMessage(message);
        }
    }

    public interface Listener {
        void goToGoogleLogin(GoogleApiClient googleApiClient);
        void goToGroupsView();
        void showGroupErrorMessage(String message);
    }

    public void getUserGroups(User curUser) {
        presenter.getUserGroups(curUser);
    }
}
