package com.capgemini.assignment.Controller;

import com.capgemini.assignment.Model.User;
import com.capgemini.assignment.Service.AccountService;
import com.capgemini.assignment.Service.TransactionService;
import com.capgemini.assignment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public @ResponseBody
    ModelAndView home() {
        Map<String, Object> model = new HashMap<>();
        model.put("userMap", userService.getAllUsers());
        return new ModelAndView("home", model);
    }

    @RequestMapping(path = "/initialize", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView initializeData() {
        userService.createUser(new User(1, "Emre", "Acar"));
        accountService.createAccount(1, 100);
        userService.createUser(new User(2, "John", "Doe"));
        accountService.createAccount(2, 100);
        userService.createUser(new User(3, "Jane", "Doe"));
        Map<String, Object> model = new HashMap<>();
        model.put("userMap", userService.getAllUsers());
        return new ModelAndView("home", model);
    }

    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getUserInformation(@RequestParam("customerID") long customerID) {
        Map<String, Object> model = new HashMap<>();

        User user = userService.getUser(customerID);
        if (user == null) {
            return new ModelAndView("home", model);
        }
        model.put("customerID", user.getCustomerID());
        model.put("name", user.getName());
        model.put("surname", user.getSurname());
        model.put("balance", userService.getTotalBalanceOfUser(customerID));
        model.put("transactions", userService.getUserTransactions(customerID));

        return new ModelAndView("user", model);
    }

    @RequestMapping(path = "/openAccount", method = RequestMethod.POST)
    public @ResponseBody
    void openAccount(@RequestParam("customerID") long customerID,
                     @RequestParam("initialCredit") long initialCredit) {
        accountService.createAccount(customerID, initialCredit);
    }
}
