package com.capgemini.assignment.Dao;

import com.capgemini.assignment.Model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {
    private Map<Long, User> userMap = new HashMap<>();

    public User getUser(long customerID) {
        return userMap.get(customerID);
    }

    public void createUser(User user) {
        userMap.put(user.getCustomerID(), user);
    }

    public void addUserAccount(long customerID, Long accountID) {
        User user = userMap.get(customerID);
        user.getAccounts().add(accountID);
    }

    public Map<Long, User> getAllUsers() {
        return userMap;
    }
}
