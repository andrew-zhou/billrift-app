/*
    TransactionViewHolder.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.view.View;
import android.widget.TextView;

import com.BillRift.presenters.TransactionPresenter;
import com.BillRift.views.TransactionView;

public class TransactionViewHolder extends MvpViewHolder<TransactionPresenter> implements TransactionView {
    private TextView transactionTextView;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        transactionTextView = (TextView)itemView.findViewById(R.id.transaction_text);
    }

    @Override
    public void setText(String text) {
        transactionTextView.setText(text);
    }
}
