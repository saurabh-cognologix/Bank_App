package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.AccountConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.AccountRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServicesImplementationTest {

    @MockBean
    private AccountRepo accountRepo;

    @Autowired
    private AccountServicesImplementation accountServices;


    private AccountDto accountDto;

    @Autowired
    private AccountConvertor accountConvertor;

    @BeforeEach
    void setUp(){
        accountDto = AccountDto.builder()
                .accountNumber(123456)
                .accountTypes("Saving")
                .accountHolderName("Sonu")
                .accountBalance(1000.00)
                .build();
    }

    @Test
    void createAccount() {
        // given - precondition or setup
        Account account = this.accountConvertor.dtoToAccount(accountDto);
        System.out.println(account.getAccountBalance());
        given(accountRepo.findById(account.getAccountNumber())).willReturn(Optional.of(account));
        given(accountRepo.save(account)).willReturn(account);

        // when - action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            accountServices.createAccount(accountDto);
        });

        // then
        verify(accountRepo, never()).save(any(Account.class));
    }

    @Test
    void getAccountDetails() {
        // given - precondition or setup

        AccountDto accountDto1 = AccountDto.builder()
                .accountNumber(123456)
                .accountTypes("Saving")
                .accountHolderName("Sonu")
                .accountBalance(1000.00)
                .build();

        Account account = this.accountConvertor.dtoToAccount(accountDto);
        Account account1 = this.accountConvertor.dtoToAccount(accountDto1);

        given(accountRepo.findAll()).willReturn(List.of(account,account1));

        // when -  action or the behaviour that we are going test
        List<AccountDto> employeeList = accountServices.getAllAccountDetails();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    void getAccountDetailsByAccountNumber() {
        Account account = this.accountConvertor.dtoToAccount(accountDto);

        //given
        given(accountRepo.findById(123456)).willReturn(Optional.of(account));
        // when
        Optional<AccountDto> savedUser = accountServices.getAccountDetailsByAccountNumber(account.getAccountNumber());
        // then
        org.assertj.core.api.Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    void deleteAccount() {
        // given - precondition or setup

        Integer accountId = 123456;
        willDoNothing().given(accountRepo).deleteById(accountId);
       // when -  action or the behaviour that we are going test
        accountServices.deleteSingleAccount(accountId);
        // then - verify the output
        verify(accountRepo, times(1)).deleteById(accountId);
    }
}