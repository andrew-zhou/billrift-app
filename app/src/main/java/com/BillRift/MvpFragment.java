package com.BillRift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.BillRift.presenters.BasePresenter;

public abstract class MvpFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter = createPresenter();
        } else {
            PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onPause();
        Bundle bundle = new Bundle();
        PresenterManager.getInstance().savePresenter(presenter, bundle);
        presenter.unbindView();
    }

    protected abstract P createPresenter();
}