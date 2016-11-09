package com.BillRift.views;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.BillRift.MvpViewHolder;
import com.BillRift.R;
import com.BillRift.models.Group;
import com.BillRift.presenters.GroupPresenter;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupViewHolder extends MvpViewHolder<GroupPresenter> implements GroupView {
    private final TextView groupName;
    private final TextView personalBalance;

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
        personalBalance.setText(String.valueOf(balance));
    }
}
