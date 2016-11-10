package com.BillRift.databases;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.TokenManager;
import com.BillRift.models.Balance;
import com.BillRift.security.CryptManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 11/9/2016.
 */

public class BalanceDatabase {
    private static BalanceDatabase instance;
    private final Map<String, String> balances;

    public BalanceDatabase() {
        this.balances = new HashMap<>();
    }

    public static synchronized BalanceDatabase getInstance() {
        if (instance == null) {
            instance = new BalanceDatabase();
        }
        return instance;
    }

    public void saveBalance(@NonNull Balance balance) {
        synchronized(balances) {
            balances.put(balance.getKey(), CryptManager.encryptObject(balance, TokenManager.getToken() /* TODO: Get token here */ ));
        }
    }

    @Nullable
    public Balance getBalance(String from, String to) {
        synchronized(balances) {
            return (Balance) CryptManager.decryptString(balances.get(Balance.getKey(from, to)), TokenManager.getToken() /* TODO: Get token here */ );
        }
    }

    public List<Balance> getBalancesForGroup(int groupId) {
        // Mock data
        List<Balance> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Balance Balance = new Balance(Integer.toString(i % 5), Integer.toString((i+1) % 5), i+10, 1);
            list.add(Balance);
        }
        return list;
        
        // TODO: Uncomment when no more mocking data
//        synchronized (balances) {
//            List<Balance> b = new ArrayList<>();
//            for (String s : balances.values()) {
//                Balance balance = (Balance) CryptManager.decryptString(s, TokenManager.getToken() /* TODO: Get token here */ );
//                if (balance.getGroup() == groupId) {
//                    b.add(balance);
//                }
//            }
//            return b;
//        }
    }
}
