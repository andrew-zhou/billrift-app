package com.BillRift.presenters;

import com.BillRift.databases.TransactionDatabase;
import com.BillRift.models.Transaction;
import com.BillRift.views.TransactionListView;

import java.util.ArrayList;
import java.util.List;

public class TransactionListPresenter extends BasePresenter<List<Transaction>, TransactionListView> {
    private boolean isLoaded = false;

    public TransactionListPresenter(int groupId) {
        super();
        setModel(new ArrayList<Transaction>());
        // TODO: Make call to load groups to database from server
        // In the callback:
        // transactionList = returnedValue;
        // isLoaded = true;
        // updateView();

        // For mocked purposes:
        setModel(TransactionDatabase.getInstance().getTransactionsForGroup(groupId));
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
                view().showTransactions(model);
            }
        }
    }

    public void addTransaction() {
        view().goToAddTransaction();
    }

    public void showBalances() {
        view().goToShowBalances();
    }

    public void addGroupMemberButtonClicked() {
        view().showAddGroupMemberDialog();
    }

    public void addGroupMember(String email) {
        // TODO: Network requests
    }
}
