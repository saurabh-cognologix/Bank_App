package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.AccountConvertor;
import com.cognologix.assignment.BankSystemAssignment.convertor.TransactionConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.AccountRepo;
import com.cognologix.assignment.BankSystemAssignment.dao.TransactionRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TransactionServiceImplementationTest {

    @MockBean
    private TransactionRepo transactionRepo;

    @MockBean
    private AccountRepo accountRepo;

    @Autowired
    private TransactionServiceImplementation transactionServiceImplementation;

    private TransactionDto transactionDto;
    private AccountDto accountDto;

    @Autowired
    private TransactionConvertor transactionConvertor;

    @BeforeEach
    void setUp(){
        transactionDto = TransactionDto.builder()
                .accountNumber(123456)
                .totalAmount(500.00)
                .transferAmount(100.00)
                .build();

        accountDto = AccountDto.builder()
                .accountNumber(123456)
                .accountTypes("Saving")
                .accountHolderName("Sonu")
                .accountBalance(1000.00)
                .build();
    }



    @Test
    void getTransactionDetails() {
        //given -precondition or setup
         TransactionDto transactionDto1 = TransactionDto.builder()
                .accountNumber(123456)
                .totalAmount(500.00)
                .transferAmount(100.00)
                .build();

         Transaction transaction = this.transactionConvertor.dtoToTransaction(transactionDto);
         Transaction transaction1 = this.transactionConvertor.dtoToTransaction(transactionDto1);

        given(transactionRepo.findAll()).willReturn(List.of(transaction,transaction1));

        // when -  action or the behaviour that we are going test
        List<TransactionDto> transactionList = transactionServiceImplementation.getTransactionDetails();

        // then - verify the output
        assertThat(transactionList).isNotNull();
        assertThat(transactionList.size()).isEqualTo(2);

    }

    @Test
    void getDepositAmount() {
       // Account account = this.accountConvertor.dtoToAccount(accountDto);
        //given(accountRepo.findById(123456)).willReturn(Optional.of(account));
    }

    @Test
    void getWithDrawAmount() {
    }

    @Test
    void amountTransfer() {
    }
}