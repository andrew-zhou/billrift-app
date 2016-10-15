package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.BillRift.presenters.LoginPresenter;
import com.BillRift.views.LoginView;

public class LoginFragment extends MvpFragment<LoginPresenter> implements LoginView {

    private Listener listener;

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

    public interface Listener {
        void goToGroupsActivity();
    }
}
