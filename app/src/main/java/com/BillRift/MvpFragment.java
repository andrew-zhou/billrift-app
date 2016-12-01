/*
    MvpFragment.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.BillRift.presenters.BasePresenter;

public abstract class MvpFragment<P extends BasePresenter> extends Fragment {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter = createPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        presenter.bindView(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
        presenter.unbindView();
    }

    protected abstract P createPresenter();
}