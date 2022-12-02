package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.payloads.ApiResponse;
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
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto){
        AccountDto accountDto1 = this.accountServices.saveAccount(accountDto);
        return new ResponseEntity<>(accountDto1,HttpStatus.OK);


    }

    //get all accounts
    @GetMapping("/get")
    public ResponseEntity<List<AccountDto>> getAccountDetails() {
        return ResponseEntity.ok(this.accountServices.getAccountDetails());
    }

    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<AccountDto> findAccountDetailsById( @PathVariable("accountNumber") Integer accountNumber){
        return ResponseEntity.ok(this.accountServices.getAccountDetailsByAccountNumber(accountNumber));
    }

    /*
    *  Delete account Number
    * */
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<ApiResponse> deleteAccountDetailsById(@PathVariable("accountNumber") Integer accountNumber){
        this.accountServices.deleteAccount(accountNumber);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }
}

