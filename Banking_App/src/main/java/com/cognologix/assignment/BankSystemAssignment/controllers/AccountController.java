package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.responses.ApiResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.CreateAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.accountResponses.DeleteAccountResponse;
import com.cognologix.assignment.BankSystemAssignment.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServices accountServices;

    @PostMapping("/createAccount")
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody AccountDto accountDto){
        CreateAccountResponse createAccountResponse = this.accountServices.createAccount(accountDto);
        HttpStatus httpStatus = createAccountResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(createAccountResponse,httpStatus);
    }

    //get all accounts
    @GetMapping("/get")
    public ResponseEntity<List<AccountDto>> getAccountDetails() {
        return ResponseEntity.ok(this.accountServices.getAllAccountDetails());
    }

    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<AccountDto> findAccountDetailsById( @PathVariable("accountNumber") Integer accountNumber){
       //return ResponseEntity.ok(this.accountServices.getAccountDetailsByAccountNumber(accountNumber));
        return this.accountServices.getAccountDetailsByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    /*
    *  Delete account Number
    * */
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<DeleteAccountResponse> deleteAccountDetailsById(@PathVariable("accountNumber") Integer accountNumber){
        DeleteAccountResponse deleteAccountResponse = this.accountServices.deleteSingleAccount(accountNumber);
        HttpStatus httpStatus = deleteAccountResponse.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(deleteAccountResponse,httpStatus);
    }
}

