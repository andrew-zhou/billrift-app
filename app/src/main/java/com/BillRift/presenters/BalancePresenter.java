package com.BillRift.presenters;

import android.util.Log;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Balance;
import com.BillRift.models.User;
import com.BillRift.views.BalanceView;

import java.text.NumberFormat;

public class BalancePresenter extends BasePresenter<Balance, BalanceView> {
    public BalancePresenter(Balance b) {
        super();
        setModel(b);
    }

    @Override
    protected void updateView() {
        User from = UserDatabase.getInstance().getUser(model.getFrom());
        User to = UserDatabase.getInstance().getUser(model.getTo());
        if (from == null || to == null) {
            Log.d("ERROR", "Null value for from or to");
            return;
        }
        view().setText(from.getDisplayName() + " owes " + to.getDisplayName() + " a total of " + NumberFormat.getCurrencyInstance().format(model.getAmount()));
    }
}
