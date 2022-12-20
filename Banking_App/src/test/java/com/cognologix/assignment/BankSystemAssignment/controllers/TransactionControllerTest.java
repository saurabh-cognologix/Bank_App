package com.cognologix.assignment.BankSystemAssignment.controllers;


import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.exception.InsufficientBalanceException;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.responses.transactionResponses.TransactionResponse;
import com.cognologix.assignment.BankSystemAssignment.services.impl.TransactionServiceImplementation;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionServiceImplementation transactionServiceImplementation;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionController transactionController;

    private TransactionDto transactionDto;

    private TransactionResponse transactionResponse;

    @BeforeEach
    void setUp(){
        transactionDto = TransactionDto.builder()
                .transactionId(101)
                .accountNumber(123)
                .totalAmount(500.00)
                .transferAmount(100.00)
                .build();
    }



    @Test
    void getTransactionDetails() throws Exception {
        //given - precondition or setup
        List<TransactionDto> listOfTransaction = new ArrayList<>();

        listOfTransaction.add(TransactionDto.builder().transactionId(200).accountNumber(200).totalAmount(500.00).transferAmount(100.00).build());
        listOfTransaction.add(TransactionDto.builder().transactionId(300).accountNumber(300).totalAmount(500.00).transferAmount(100.00).build());

        given(transactionServiceImplementation.getTransactionDetails()).willReturn(listOfTransaction);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/transaction/get"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfTransaction.size())));
    }

    // JUNIT testing of depositAmount() in POSITIVE Scenario
    @Test
    void depositAmount() throws Exception {
        //given- precondition or setup
        Integer accountNumber = 500;
        Double depositAmount = 500.00;
        TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Deposit Successfully");
         given(transactionServiceImplementation.depositAmount(accountNumber,depositAmount)).willReturn(transactionResponse);

        // when -  action or the behaviour that we are going
        ResultActions result = mockMvc.perform(
                put("/transaction/deposit?accountNumber=500&amount=500.00")
                        .content(objectMapper.writeValueAsString(transactionResponse))
                       .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the output
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message",is("Amount Deposit Successfully")))
                .andExpect(jsonPath("$.success",is(true)))
                .andReturn();
    }

    //JUNIT testing of depositAmount() in NEGATIVE Scenario
    @Test
    void depositAmountInNegativeScenario() throws Exception {
        //given- precondition or setup
        Integer accountNumber = 500;
        Double depositAmount = 500.00;
        TransactionResponse transactionResponse = new TransactionResponse(false,"Amount Not Deposit Successfully");
        given(transactionServiceImplementation.depositAmount(accountNumber,depositAmount)).willReturn(transactionResponse);

        // when -  action or the behaviour that we are going
        ResultActions result = mockMvc.perform(
                put("/transaction/deposit?accountNumber=500&amount=500.00")
                        .content(objectMapper.writeValueAsString(transactionResponse))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the output
        result.andExpect(status().isBadRequest())
                .andReturn();
    }


    // JUNIT testing of withdrawAmount() in POSITIVE Scenario
    @Test
    void withdrawAmount() throws Exception {
        //given- precondition or setup
        Integer accountNumber = 500;
        Double withdrawAmount = 500.00;
//        transactionResponse = TransactionResponse.builder()
//                .message("Amount Withdraw Successfully")
//                .success(true)
//                .build();
        TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Withdraw Successfully");

        given(transactionServiceImplementation.getWithDrawAmount(accountNumber,withdrawAmount)).willReturn(transactionResponse);

        // when -  action or the behaviour that we are going
        ResultActions result = mockMvc.perform(
                put("/transaction/withdraw?accountNumber=500&amount=500.00")
                        .content(objectMapper.writeValueAsString(transactionResponse))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the output
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message",is("Amount Withdraw Successfully")))
                .andExpect(jsonPath("$.success",is(true)))
                .andReturn();

    }

    // JUNIT testing of withdrawAmount() in NEGATIVE Scenario
    @Test
    void withdrawAmountInNegativeScenario() throws Exception {
        //given- precondition or setup
        Integer accountNumber = 500;
        Double withdrawAmount = 300.00;
//        transactionResponse = TransactionResponse.builder()
//                .message("Amount Withdraw Successfully")
//                .success(true)
//                .build();
        TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Withdraw Successfully");
        //InsufficientBalanceException insufficientBalanceException = new InsufficientBalanceException("Your");
        //given(transactionServiceImplementation.getWithDrawAmount(accountNumber,withdrawAmount)).willThrow(insufficientBalanceException);
       assertThrows(InsufficientBalanceException.class, () -> {
            transactionServiceImplementation.getWithDrawAmount(accountNumber,withdrawAmount);
        });

        // when -  action or the behaviour that we are going
        ResultActions result = mockMvc.perform(
                put("/transaction/withdraw?accountNumber=500&amount=500.00")
                        .content(objectMapper.writeValueAsString(transactionResponse))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the output
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message",is("Amount Withdraw Successfully")))
                .andExpect(jsonPath("$.success",is(true)))
                .andReturn();

    }

    @Test
    void transferAmount() throws Exception {
        //given- precondition or setup
        Integer receiverAccountNumber = 500;
        Integer senderAccountNumber = 501;
        Double transferAmount = 100.00;
//        transactionResponse = TransactionResponse.builder()
//                .message("Amount Withdraw Successfully")
//                .success(true)
//                .build();

        TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Withdraw Successfully");

        given(transactionServiceImplementation.amountTransfer(senderAccountNumber,receiverAccountNumber,transferAmount)).willReturn(transactionResponse);

        // when -  action or the behaviour that we are going
        ResultActions result = mockMvc.perform(
                put("/transaction/transferAmount?senderAccountNumber=501&receiverAccountNumber=500&amount=100")
                        .content(objectMapper.writeValueAsString(transactionResponse))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then - verify the output
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message",is("Amount Withdraw Successfully")))
                .andExpect(jsonPath("$.success",is(true)))
                .andReturn();
    }


    // JUNIT Testing of getTransactionById in Positive Scenario
    @Test
    void getTransactionById() throws Exception {
        Integer transactionId = 101;
        //given
        given(transactionServiceImplementation.getTransactionById(transactionId)).willReturn(Optional.ofNullable(transactionDto));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/transaction/getTransaction/{transactionId}", transactionId));

        // then - verify the output
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.transactionId", is(transactionDto.getTransactionId())))
                .andExpect(jsonPath("$.accountNumber", is(transactionDto.getAccountNumber())))
                .andExpect(jsonPath("$.totalAmount", is(transactionDto.getTotalAmount())));
    }

    // JUNIT Testing of getTransactionById in Negative Scenario
    @Test
    void getTransactionByIdInNegativeScenario() throws Exception {
        Integer transactionId = 101;
        //given
        given(transactionServiceImplementation.getTransactionById(transactionId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/transaction/getTransaction/{transactionId}", transactionId));

        // then - verify the output
        response
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}