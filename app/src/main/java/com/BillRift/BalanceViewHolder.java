/*
    BalanceViewHolder.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.view.View;
import android.widget.TextView;

import com.BillRift.presenters.BalancePresenter;
import com.BillRift.views.BalanceView;

public class BalanceViewHolder extends MvpViewHolder<BalancePresenter> implements BalanceView {
    private TextView balTV;

    public BalanceViewHolder(View itemView) {
        super(itemView);
        balTV = (TextView) itemView.findViewById(R.id.balance_text);
    }

    public void setText(String text) {
        balTV.setText(text);
    }
}
