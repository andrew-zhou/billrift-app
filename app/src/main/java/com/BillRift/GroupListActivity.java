/*
    GroupListActivity.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupListActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_grouplist);
    }

    @Subscribe
    public void goToGroupTransactions(GroupListMessageEvent event) {
        Intent intent = TransactionListActivity.makeIntent(this, event.getGroupId());
        startActivity(intent);
    }
}
