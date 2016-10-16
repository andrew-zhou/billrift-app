package com.BillRift.presenters;

import com.BillRift.models.Transaction;
import com.BillRift.views.TransactionView;

/**
 * Created by Yenny on 2016-10-15.
 */

public class TransactionPresenter extends BasePresenter<Transaction, TransactionView> {
    public TransactionPresenter(Transaction transaction) {
        super();
        setModel(transaction);
    }

    @Override
    protected void updateView() {
        view().setText(model.getFrom() + (model.getAmount() > 0 ? " paid $" : " borrowed $")
                + Double.toString(model.getAmount()) + (model.getAmount() > 0 ? " to " : " from ")
                + model.getTo());
    }


}
