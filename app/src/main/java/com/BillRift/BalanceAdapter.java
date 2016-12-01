/*
    BalanceAdapter.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.BillRift.models.Balance;
import com.BillRift.presenters.BalancePresenter;

public class BalanceAdapter extends MvpRecyclerListAdapter<Balance, BalancePresenter, BalanceViewHolder> {
    @Override
    public BalanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BalanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_row, parent, false));
    }

    @NonNull
    @Override
    protected BalancePresenter createPresenter(@NonNull Balance model) {
        return new BalancePresenter(model);
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Balance model) {
        return model.getKey();
    }
}
