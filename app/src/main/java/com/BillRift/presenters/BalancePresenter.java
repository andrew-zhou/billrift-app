package com.BillRift.presenters;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Balance;
import com.BillRift.models.User;
import com.BillRift.views.BalanceView;

import java.text.NumberFormat;

/**
 * Created by Andrew on 11/9/2016.
 */

public class BalancePresenter extends BasePresenter<Balance, BalanceView> {
    public BalancePresenter(Balance b) {
        super();
        setModel(b);
    }

    @Override
    protected void updateView() {
        view().setText("Fake mock data");
        // TODO: When no longer mocking data, uncomment this
//        User from = UserDatabase.getInstance().getUser(model.getFrom());
//        User to = UserDatabase.getInstance().getUser(model.getTo());
//        view().setText(from.getDisplayName() + " owes " + to.getDisplayName() + " a total of " + NumberFormat.getCurrencyInstance().format(model.getAmount()));
    }
}
