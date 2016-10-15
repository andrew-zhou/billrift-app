package com.BillRift.presenters;

import com.BillRift.views.LoginView;

public class LoginPresenter extends BasePresenter<Object, LoginView> {
    @Override
    protected void updateView() {

    }

    public void onLoginButtonClicked() {
        // TODO: Do OAuth
        view().finishLogin();
    }
}
