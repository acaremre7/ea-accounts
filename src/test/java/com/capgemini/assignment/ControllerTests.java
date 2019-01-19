package com.capgemini.assignment;

import com.capgemini.assignment.Controller.HomeController;
import com.capgemini.assignment.Model.User;
import com.capgemini.assignment.Service.AccountService;
import com.capgemini.assignment.Service.TransactionService;
import com.capgemini.assignment.Service.UserService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HomeController.class, secure = false)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private AccountService accountService;

    @BeforeClass
    public static void init() {
    }

    @Test
    public void testHome() throws Exception {
        Mockito.when(
                userService.getAllUsers()).thenReturn(new HashMap<Long, User>());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testInitializeData() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/initialize").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testGetUserInformationWithUser() throws Exception {
        Mockito.when(
                userService.getUser(Mockito.anyLong())).thenReturn(new User(1000, "Dummy", "User"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/getUser?customerID=1000").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testGetUserInformationWithoutUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/getUser?customerID=1000").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testOpenAccount() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/openAccount?customerID=1000&initialCredit=100").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }


}
