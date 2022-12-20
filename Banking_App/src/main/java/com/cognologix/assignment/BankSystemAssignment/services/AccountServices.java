package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.CreateAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.DeleteAccountResponse;

import java.util.List;
import java.util.Optional;

public interface AccountServices {


    //BankObject bankObject=new BankObject();
    CreateAccountResponse createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccountDetails();
    Optional<AccountDto> getAccountDetailsByAccountNumber(Integer accountNumber);

    DeleteAccountResponse deleteSingleAccount(Integer accountNumber);

}
