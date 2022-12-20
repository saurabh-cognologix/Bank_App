package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.AccountConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.AccountRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.CreateAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.DeleteAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServicesImplementation implements AccountServices {

    private AccountRepo accountRepo;

    public AccountServicesImplementation(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Autowired
    private AccountConvertor accountConvertor;

    @Override
    public CreateAccountResponse createAccount(AccountDto accountDto) {

        Account account = this.accountConvertor.dtoToAccount(accountDto);
        Optional<Account> savedAccount = accountRepo.findById(account.getAccountNumber());
        if(savedAccount.isPresent()){
            throw new ResourceNotFoundException("Account","AccountNumber",account.getAccountNumber());
        }
        this.accountRepo.save(account);
        CreateAccountResponse createAccountResponse = new CreateAccountResponse("Account Opened Successfully",true,accountDto);
        return createAccountResponse;

    }

    @Override
    public List<AccountDto> getAllAccountDetails() {
        List<Account> accountList = this.accountRepo.findAll();
        List<AccountDto> accountDtoList = accountList.stream()
                .map(account -> this.accountConvertor.accountToDto(account))
                .collect(Collectors.toList());
        return accountDtoList;
    }

    @Override
    public Optional<AccountDto> getAccountDetailsByAccountNumber(Integer accountNumber) {
        Account account = this.accountRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",accountNumber));
        //return this.accountConvertor.accountToDto(account);
        AccountDto accountDto = this.accountConvertor.accountToDto(account);
        return Optional.of(accountDto);
    }

    @Override
    public DeleteAccountResponse deleteSingleAccount(Integer accountNumber) {
        this.accountRepo.deleteById(accountNumber);
        DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse(true,"Account Deleted SuccessFully");
        return deleteAccountResponse;
    }
}
