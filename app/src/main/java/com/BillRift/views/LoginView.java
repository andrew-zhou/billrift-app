package com.BillRift.views;

import com.google.android.gms.common.api.GoogleApiClient;

public interface LoginView {
    void startGoogleLogin(GoogleApiClient googleApiClient);
    void goToGroupsView();
    void showGroupErrorMessage(String message);
}
