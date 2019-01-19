package com.capgemini.assignment.Model;

public class Transaction {
    private long accountID;
    private long amount;

    public Transaction(long accountID, long amount) {
        this.accountID = accountID;
        this.amount = amount;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
