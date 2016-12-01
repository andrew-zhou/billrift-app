/*
    TransactionAdapter.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.BillRift.models.Transaction;
import com.BillRift.presenters.TransactionPresenter;

public class TransactionAdapter extends MvpRecyclerListAdapter<Transaction, TransactionPresenter, TransactionViewHolder> {
    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_row, parent, false));
    }

    @NonNull
    @Override
    protected TransactionPresenter createPresenter(@NonNull Transaction model) {
        return new TransactionPresenter(model);
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Transaction model) {
        return model.getId();
    }
}
