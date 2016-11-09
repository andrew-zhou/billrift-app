package com.BillRift;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

/**
 * Created by Andrew on 11/9/2016.
 */

public class BalanceListActivity extends FragmentActivity {
    public static final String GROUP_ID_KEY = "GROUP_ID_KEY";

    private Integer groupId;
    private BalanceListFragment fragment;

    public static Intent makeIntent(Context from, Integer groupId) {
        Intent intent = new Intent(from, BalanceListActivity.class);
        intent.putExtra(GROUP_ID_KEY, groupId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groupId = getIntent().getIntExtra(GROUP_ID_KEY, -1);

        setContentView(R.layout.activity_balances);
        LinearLayout layout = (LinearLayout)findViewById(R.id.linear_layout);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setId(R.id.auto);

        fragment = new BalanceListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TransactionListFragment.GROUP_ID_KEY, groupId);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(container.getId(), fragment).commit();

        layout.addView(container);
    }
}
