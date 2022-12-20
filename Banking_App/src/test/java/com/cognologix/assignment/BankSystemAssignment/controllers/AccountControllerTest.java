package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.CreateAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.DeleteAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.services.AccountServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountServices accountServices;

    //To call the rest API
    @Autowired
    private MockMvc mockMvc;

    //For reading and writing the JSON value
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountController accountController;

    private AccountDto accountDto;

    @BeforeEach
    void setUp(){
        accountDto = AccountDto
                .builder()
                .accountNumber(123)
                .accountHolderName("Exix")
                .accountTypes("Saving")
                .accountBalance(500.00)
                .build();
    }

    @Test
    void createAccount() throws Exception {
        //given - setup or precondition
        CreateAccountResponse createAccountResponse = new CreateAccountResponse("Account Opened Successfully",true,accountDto);
        given(accountServices.createAccount(any(AccountDto.class))).willReturn(createAccountResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/account/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountResponse)));

        //then - verify
        response.andDo(print())
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.accountNumber", is(accountDto.getAccountNumber())))
//                .andExpect(jsonPath("$.accountHolderName", is(accountDto.getAccountHolderName())))
//                .andExpect(jsonPath("$.accountTypes", is(accountDto.getAccountTypes())));
    }


    //Positive Scenario
    //JUNIT test to get all account Details
    @Test
    void getAccountDetails() throws Exception {
        List<AccountDto> listOfAccount = new ArrayList<>();

        listOfAccount.add(AccountDto.builder().accountNumber(200).accountHolderName("Sonu").accountTypes("Saving").accountBalance(100.00).build());
        listOfAccount.add(AccountDto.builder().accountNumber(201).accountHolderName("Monu").accountTypes("Saving").accountBalance(200.00).build());

        given(accountServices.getAllAccountDetails()).willReturn(listOfAccount);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/account/get"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfAccount.size())));
    }

    //Negative Scenario
    //JUNIT test to get all account Details
    @Test
    void getAccountDetailsInNegativeScenario() throws Exception {
        List<AccountDto> listOfAccount = new ArrayList<>();

        //listOfAccount.add(AccountDto.builder().accountNumber(200).accountHolderName("Sonu").accountTypes("Saving").accountBalance(100.00).build());
       // listOfAccount.add(AccountDto.builder().accountNumber(201).accountHolderName("Monu").accountTypes("Saving").accountBalance(200.00).build());

        given(accountServices.getAllAccountDetails()).willReturn(listOfAccount);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/account/get"));

        // then - verify the output
        response
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfAccount.size())));
    }


    //Positive Scenario
    @Test
    void findAccountDetailsById() throws Exception {
        // given - precondition or setup
        Integer accountNumber = 123;
        given(accountServices.getAccountDetailsByAccountNumber(accountNumber)).willReturn(Optional.of(accountDto));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/account/get/{accountNumber}", accountNumber));

        // then - verify the output
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber", is(accountDto.getAccountNumber())))
                .andExpect(jsonPath("$.accountHolderName", is(accountDto.getAccountHolderName())))
                .andExpect(jsonPath("$.accountTypes", is(accountDto.getAccountTypes())));
    }

    //Negative Scenario
    @Test
    void findAccountDetailsByInvalidId() throws Exception {
        // given - precondition or setup
        Integer accountNumber = 123;
        given(accountServices.getAccountDetailsByAccountNumber(accountNumber)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/account/get/{accountNumber}", accountNumber));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    //Positive Scenario
    @Test
    void deleteAccountDetailsById() throws Exception {
        DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse(true,"Account Deleted SuccessFully");
        // given - precondition or setup
        Integer accountNumber= 123;
        given(accountServices.deleteSingleAccount(accountNumber)).willReturn(deleteAccountResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/account/delete/{accountNumber}",accountNumber));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

    //Negative Scenario
    @Test
    void deleteAccountDetailsByInvalidId() throws Exception {
        // given - precondition or setup
        Integer accountNumber= 123;
        DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse(false,"Account Deleted SuccessFully");
        given(accountServices.deleteSingleAccount(accountNumber)).willReturn(deleteAccountResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/account/delete/{accountNumber}",accountNumber));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
}