package com.BillRift.databases;

import android.support.annotation.NonNull;

import com.BillRift.TokenManager;
import com.BillRift.models.Transaction;
import com.BillRift.security.CryptManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDatabase {
    private static TransactionDatabase instance;
    private final Map<Integer, String> transactions;

    private TransactionDatabase() {
        transactions = new HashMap<>();
    }

    public static synchronized TransactionDatabase getInstance() {
        if (instance == null) {
            instance = new TransactionDatabase();
        }
        return instance;
    }

    public void saveTransaction(@NonNull Transaction transaction) {
        synchronized (transactions) {
            transactions.put(transaction.getId(), CryptManager.encryptObject(transaction, TokenManager.getToken()));
        }
    }

    public Transaction getTransaction(Integer id) {
        synchronized (transactions) {
            return (Transaction) CryptManager.decryptString(transactions.get(id), TokenManager.getToken());
        }
    }

    public List<Transaction> getTransactionsForGroup(Integer groupId) {
        synchronized (transactions) {
            ArrayList<Transaction> transactionList = new ArrayList<>();
            for (String transactionString : transactions.values()) {
                Transaction transaction = (Transaction) CryptManager.decryptString(transactionString, TokenManager.getToken());
                if (transaction.getGroupId().equals(groupId)) {
                    transactionList.add(transaction);
                }
            }
            return transactionList;
        }
    }

}