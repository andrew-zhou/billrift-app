package com.BillRift.presenters;

import com.BillRift.GroupListMessageEvent;
import com.BillRift.models.Group;
import com.BillRift.views.GroupView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupPresenter extends BasePresenter<Group, GroupView> {
    @Override
    protected void updateView() {
        view().setGroupName(model.getName());
    }

    public void onGroupClicked() {
        EventBus.getDefault().post(new GroupListMessageEvent(model.getId()));
    }
}