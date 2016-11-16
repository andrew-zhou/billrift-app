package com.BillRift.presenters;

import com.BillRift.models.Transaction;
import com.BillRift.views.TransactionView;

import java.text.NumberFormat;

public class TransactionPresenter extends BasePresenter<Transaction, TransactionView> {
    public TransactionPresenter(Transaction transaction) {
        super();
        setModel(transaction);
    }

    @Override
    protected void updateView() {
        view().setText(model.getFrom() + " paid " + NumberFormat.getCurrencyInstance().format(model.getAmount()) + " to " + model.getTo());
    }


}
