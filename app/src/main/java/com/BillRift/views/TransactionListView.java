/*
    TransactionListView.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift.views;

import com.BillRift.models.Transaction;

import java.util.List;

public interface TransactionListView {
    void showTransactions(List<Transaction> transactionList);
    void showLoading();
    void showEmpty();
    void goToAddTransaction();
    void goToShowBalances();
    void showAddGroupMemberDialog();
    void showError(String msg);
}
