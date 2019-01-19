package com.capgemini.assignment.Dao;

import com.capgemini.assignment.Model.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountDao {
    private Map<Long, Account> accountMap = new HashMap<>();

    public Account getAccount(long accountID) {
        return accountMap.get(accountID);
    }

    public void createAccount(Account account) {
        accountMap.put(account.getAccountID(), account);
    }

    public void updateAccountBalance(long accountID, long amount) {
        Account account = accountMap.get(accountID);
        account.setBalance(account.getBalance() + amount);
    }
}
