package com.capgemini.assignment.Model;

public class Account {
    private long accountID;
    private long balance;

    public Account(long accountID) {
        this.accountID = accountID;
        this.balance = 0;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
