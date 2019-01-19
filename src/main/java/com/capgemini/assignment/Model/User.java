package com.capgemini.assignment.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long customerID;
    private String name;
    private String surname;
    private List<Long> accounts;

    public User(long customerID, String name, String surname) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
        this.accounts = new ArrayList<>();
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Long> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Long> accounts) {
        this.accounts = accounts;
    }
}
