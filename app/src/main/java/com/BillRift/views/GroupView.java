/*
    GroupView.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift.views;

import com.BillRift.models.Group;

public interface GroupView {
    void setGroupName(String name);
    void setPersonalBalance(double balance);
    void showProgressBar(boolean show);
    void showError(String msg);
}
