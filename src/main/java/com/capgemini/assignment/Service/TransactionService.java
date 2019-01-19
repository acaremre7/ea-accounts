package com.capgemini.assignment.Service;

import com.capgemini.assignment.Dao.TransactionDao;
import com.capgemini.assignment.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private AccountService accountService;

    public void processTransaction(Transaction transaction) {
        transactionDao.commitTransaction(transaction);
        accountService.updateBalance(transaction.getAccountID(), transaction.getAmount());
    }

    public List<Transaction> getAccountTransactions(long accountID) {
        return transactionDao.getTransactions(accountID);
    }

}
