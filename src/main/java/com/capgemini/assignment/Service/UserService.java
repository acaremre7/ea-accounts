package com.capgemini.assignment.Service;

import com.capgemini.assignment.Dao.UserDao;
import com.capgemini.assignment.Model.Transaction;
import com.capgemini.assignment.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public Map<Long, User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(long customerID) {
        return userDao.getUser(customerID);
    }

    public void addUserAccount(long customerID, long accountID) {
        userDao.addUserAccount(customerID, accountID);
    }

    public Map<Long, List<Transaction>> getUserTransactions(long customerID) {
        Map<Long, List<Transaction>> transactionMap = new HashMap<>();
        User user = getUser(customerID);
        List<Long> accountList = user.getAccounts();
        for (Long accountID : accountList) {
            List<Transaction> transactionList = transactionService.getAccountTransactions(accountID);
            if (transactionList != null && !transactionList.isEmpty()) {
                transactionMap.put(accountID, transactionList);
            }
        }
        return transactionMap;
    }

    public long getTotalBalanceOfUser(long customerID) {
        User user = userDao.getUser(customerID);
        List<Long> userAccounts = user.getAccounts();
        long totalBalance = 0;

        for (Long accountID : userAccounts) {
            totalBalance += accountService.getAccountBalance(accountID);
        }
        return totalBalance;
    }
}
