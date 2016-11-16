package com.BillRift.presenters;

import android.util.Log;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Transaction;
import com.BillRift.models.User;
import com.BillRift.views.TransactionView;

import java.text.NumberFormat;

public class TransactionPresenter extends BasePresenter<Transaction, TransactionView> {
    public TransactionPresenter(Transaction transaction) {
        super();
        setModel(transaction);
    }

    @Override
    protected void updateView() {
        User from = UserDatabase.getInstance().getUser(model.getFrom());
        User to = UserDatabase.getInstance().getUser(model.getTo());
        if (from == null || to == null) {
            Log.d("ERROR", "Null value for from or to");
            return;
        }
        view().setText(model.getTitle() + ": " + from.getDisplayName() + " paid " + NumberFormat.getCurrencyInstance().format(model.getAmount()) + " to " + to.getDisplayName());
    }


}
