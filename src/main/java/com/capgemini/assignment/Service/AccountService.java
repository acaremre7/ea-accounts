package com.capgemini.assignment.Service;

import com.capgemini.assignment.Dao.AccountDao;
import com.capgemini.assignment.Model.Account;
import com.capgemini.assignment.Model.Transaction;
import com.capgemini.assignment.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class AccountService {

    private static final long ALLOWED_MAX_ACCOUNTS = 100;

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountDao accountDao;

    public long createAccount(long customerID, long initialCredit) {
        long accountID = generateNewAccountID(customerID);
        Account account = new Account(accountID);
        accountDao.createAccount(account);
        userService.addUserAccount(customerID, account.getAccountID());

        if (initialCredit != 0) {
            Transaction transaction = new Transaction(account.getAccountID(), initialCredit);
            transactionService.processTransaction(transaction);
        }
        return accountID;
    }

    public void updateBalance(long accountID, long amount) {
        accountDao.updateAccountBalance(accountID, amount);
    }

    public long getAccountBalance(long accountID) {
        return accountDao.getAccount(accountID).getBalance();
    }

    private long generateNewAccountID(long customerID) {
        User user = userService.getUser(customerID);
        List<Long> existingAccountsList = user.getAccounts();
        long result;
        do {
            result = Math.abs(new Random().nextLong() % ALLOWED_MAX_ACCOUNTS);
        } while (existingAccountsList.contains(result));
        return result;
    }
}
