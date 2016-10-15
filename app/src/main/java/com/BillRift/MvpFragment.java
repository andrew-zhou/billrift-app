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
        presenter = createPresenter();
        presenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }

    protected abstract P createPresenter();
}