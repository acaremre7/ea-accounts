package com.capgemini.assignment.Dao;

import com.capgemini.assignment.Model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionDao {
    private Map<Long, List<Transaction>> transactionMap = new HashMap<>();

    public List<Transaction> getTransactions(long accountID) {
        return transactionMap.get(accountID);
    }

    public void commitTransaction(Transaction transaction) {
        List<Transaction> transactionList = transactionMap.get(transaction.getAccountID());
        if (transactionList == null) {
            transactionList = new ArrayList<>();
        }
        transactionList.add(transaction);
        transactionMap.put(transaction.getAccountID(), transactionList);
    }

}
