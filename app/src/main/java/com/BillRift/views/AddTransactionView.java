/*
    AddTransactionView.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift.views;

import com.BillRift.models.User;

import java.util.List;

public interface AddTransactionView {
    void showLoading();
    void showView(List<String> names);
    void setAmount(String amount);
    void setSelectedFrom(String selectedName);
    void setDescription(String amount);
    void setSelectedTo(String selectedName);
    void onSubmit();
    void onScan();
    void showError(String msg);
}
