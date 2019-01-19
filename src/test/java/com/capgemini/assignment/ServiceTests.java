package com.capgemini.assignment;

import com.capgemini.assignment.Model.Transaction;
import com.capgemini.assignment.Model.User;
import com.capgemini.assignment.Service.AccountService;
import com.capgemini.assignment.Service.TransactionService;
import com.capgemini.assignment.Service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    ;
    @Autowired
    private AccountService accountService;

    private User user1 = new User(1000, "Dummy", "User");
    private User user2 = new User(1001, "Dummy", "User");
    private User user3 = new User(1002, "Dummy", "User");
    private User user4 = new User(1003, "Dummy", "User");

    @Before
    public void setUp() {
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
    }

    @Test
    public void createUserTest() {
        userService.createUser(user4);
        Assert.assertEquals(userService.getUser(1003), user4);
    }

    @Test
    public void getAllUsersTest() {
        Assert.assertTrue(userService.getAllUsers().containsValue(user1));
        Assert.assertEquals(userService.getAllUsers().size(), 3);
    }

    @Test
    public void getUserTest() {
        Assert.assertEquals(userService.getUser(1000), user1);
    }

    @Test
    public void addUserAccountTest() {
        userService.addUserAccount(1000, 1001);
        Assert.assertTrue(userService.getUser(1000).getAccounts().contains(1001L));
    }

    @Test
    public void getUserTransactionsTest() {
        Long accountID = commitDummyTransactions();
        Assert.assertEquals(userService.getUserTransactions(1000).get(accountID).size(), 3);
    }

    @Test
    public void getTotalBalanceOfUserTest() {
        commitDummyTransactions();
        Assert.assertEquals(userService.getTotalBalanceOfUser(1000), 400);

    }

    private Long commitDummyTransactions() {
        Long accountID = accountService.createAccount(1000, 100);
        transactionService.processTransaction(new Transaction(accountID, 100));
        transactionService.processTransaction(new Transaction(accountID, 200));
        return accountID;
    }
}
