package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.model.Account;

import java.util.List;

public interface AccountServices {


    //BankObject bankObject=new BankObject();
    AccountDto saveAccount(AccountDto accountDto);

    List<AccountDto> getAccountDetails();
    AccountDto getAccountDetailsByAccountNumber(Integer accountNumber);

    void deleteAccount(Integer accountNumber);




}
