package com.BillRift.databases;

import android.support.annotation.NonNull;

import com.BillRift.TokenManager;
import com.BillRift.models.Transaction;
import com.BillRift.security.CryptManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yenny on 2016-10-15.
 */

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
            transactions.put(transaction.getId(), CryptManager.encryptObject(transaction, TokenManager.getToken() /* TODO: Get token here */ ));
        }
    }

    public Transaction getTransaction(Integer id) {
        synchronized (transactions) {
            return (Transaction) CryptManager.decryptString(transactions.get(id), TokenManager.getToken() /* TODO: Get token here */ );
        }
    }

    public List<Transaction> getTransactionsForGroup(Integer groupId) {
        List<Transaction> list = new ArrayList<>();
        for (int i = 1; i <= 21; i++) {
            Transaction transaction = new Transaction();
            transaction.setId(i);
            transaction.setTitle("Test title: " + Integer.toString(i));
            transaction.setAmount(i - 10);
            transaction.setFrom("Test from: " + Integer.toString(i));
            transaction.setTo("Test to: " + Integer.toString(i));
            list.add(transaction);
        }
        return list;

        // TODO: Do not mock data and uncomment this section
//        synchronized (transactions) {
//            ArrayList<Transaction> transactionList = new ArrayList<>();
//            for (String transactionString : transactions.values()) {
//                Transaction transaction = (Transaction) CryptManager.decryptString(transactionString, TokenManager.getToken() /* TODO: Get token here */ );
//                if (transaction.getGroupId() == groupId) {
//                    transactionList.add(transaction);
//                }
//            }
//            return transactionList;
//        }
    }

}