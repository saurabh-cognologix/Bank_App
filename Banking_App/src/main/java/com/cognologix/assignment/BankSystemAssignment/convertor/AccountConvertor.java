package com.cognologix.assignment.BankSystemAssignment.convertor;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConvertor {
    @Autowired
    private ModelMapper modelMapper;

    /*
    * Dto To Account Convertor
    * */
    public Account dtoToAccount(AccountDto accountDto){
        Account account = this.modelMapper.map(accountDto, Account.class);
        return account;
    }

    /*
    *  Account To Dto Convertor
    * */

    public AccountDto accountToDto(Account account){
        AccountDto accountDto = this.modelMapper.map(account, AccountDto.class);
        return accountDto;
    }
}
