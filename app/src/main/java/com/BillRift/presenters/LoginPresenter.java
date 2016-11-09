package com.BillRift.presenters;

import android.content.Intent;

import com.BillRift.views.LoginView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginPresenter extends BasePresenter<Object, LoginView> {
    private GoogleApiClient googleApiClient;

    @Override
    protected void updateView() { }

    public void onLoginButtonClicked() {
        view().startGoogleLogin(googleApiClient);
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }
}
