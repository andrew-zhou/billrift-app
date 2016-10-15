package com.BillRift.views;

import android.support.annotation.Nullable;
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
    @Nullable
    private OnGroupClickListener listener;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupName = (TextView) itemView.findViewById(R.id.group_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGroupClicked();
            }
        });
    }

    public void setListener(@Nullable OnGroupClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setGroupName(String name) {
        groupName.setText(name);
    }

    @Override
    public void goToTransactionView(Group group) {
        if (listener != null) {
            listener.onGroupClick(group);
        }
    }

    public interface OnGroupClickListener {
        void onGroupClick(Group group);
    }
}
