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

public class BalanceDatabase {
    private static BalanceDatabase instance;
    private final Map<String, String> balances;

    private BalanceDatabase() {
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
            balances.put(balance.getKey(), CryptManager.encryptObject(balance, TokenManager.getToken()));
        }
    }

    @Nullable
    public Balance getBalance(String from, String to) {
        synchronized(balances) {
            return (Balance) CryptManager.decryptString(balances.get(Balance.getKey(from, to)), TokenManager.getToken());
        }
    }

    public List<Balance> getBalancesForGroup(int groupId) {
        synchronized (balances) {
            List<Balance> b = new ArrayList<>();
            for (String s : balances.values()) {
                Balance balance = (Balance) CryptManager.decryptString(s, TokenManager.getToken());
                if (balance.getGroup() == groupId) {
                    b.add(balance);
                }
            }
            return b;
        }
    }
}
