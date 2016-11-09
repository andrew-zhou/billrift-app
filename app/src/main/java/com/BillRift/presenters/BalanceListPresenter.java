package com.BillRift.presenters;

import com.BillRift.databases.BalanceDatabase;
import com.BillRift.models.Balance;
import com.BillRift.views.BalanceListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 11/9/2016.
 */

public class BalanceListPresenter extends BasePresenter<List<Balance>, BalanceListView> {
    private boolean isLoaded = false;

    public BalanceListPresenter(int groupId) {
        super();
        setModel(new ArrayList<Balance>());

        // TODO: Make call to load balances to database from server
        // In the callback:
        // transactionList = returnedValue;
        // isLoaded = true;
        // updateView();

        // For mocked purposes:
        setModel(BalanceDatabase.getInstance().getBalancesForGroup(groupId));
        isLoaded = true;
    }

    @Override
    protected void updateView() {
        if (!isLoaded) {
            view().showLoading();
        } else {
            if (model.isEmpty()) {
                view().showEmpty();
            } else {
                view().showBalances(model);
            }
        }
    }
}
