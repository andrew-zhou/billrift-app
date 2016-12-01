/*
    GroupViewHolder.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.BillRift.MvpViewHolder;
import com.BillRift.R;
import com.BillRift.models.Group;
import com.BillRift.presenters.GroupPresenter;

import java.text.NumberFormat;

public class GroupViewHolder extends MvpViewHolder<GroupPresenter> implements GroupView {
    private final TextView groupName;
    private final TextView personalBalance;
    private ProgressDialog progressDialog;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupName = (TextView) itemView.findViewById(R.id.group_name);
        personalBalance = (TextView) itemView.findViewById(R.id.personal_balance);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGroupClicked();
            }
        });
    }

    @Override
    public void setGroupName(String name) {
        groupName.setText(name);
    }

    @Override
    public void setPersonalBalance(double balance) {
        personalBalance.setText(NumberFormat.getCurrencyInstance().format(balance));
    }

    @Override
    public void showProgressBar(boolean show) {
        // This is unfortunately how we can get context from the view holder
        Context context = groupName.getContext();
        if (show) {
            showProgressDialog(context);
        } else {
            hideProgressDialog();
        }
    }

    @Override
    public void showError(String msg) {
        Context context = groupName.getContext();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    private void showProgressDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getString(R.string.loading));
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}
