package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.AccountConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.AccountRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImplementation implements AccountServices {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountConvertor accountConvertor;

    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = this.accountConvertor.dtoToAccount(accountDto);
        Account savingAccount = this.accountRepo.save(account);
        return this.accountConvertor.accountToDto(savingAccount);

//        Account accountDetails = this.accountDtoToModelConverter.dtoToModel(accountDto);
//        Account acc = this.accountRepo.save(accountDetails);
//        return this.accountModelToDtoConverter.modelToDto(acc);
    }

    @Override
    public List<AccountDto> getAccountDetails() {
        List<Account> accountList = this.accountRepo.findAll();
        List<AccountDto> accountDtoList = accountList.stream()
                .map(account -> this.accountConvertor.accountToDto(account))
                .collect(Collectors.toList());
        return accountDtoList;
    }

    @Override
    public AccountDto getAccountDetailsByAccountNumber(Integer accountNumber) {
        Account account = this.accountRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",accountNumber));
        return this.accountConvertor.accountToDto(account);
    }

    @Override
    public void deleteAccount(Integer accountNumber) {
        Account account = this.accountRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",accountNumber));
        this.accountRepo.delete(account);

    }
}
