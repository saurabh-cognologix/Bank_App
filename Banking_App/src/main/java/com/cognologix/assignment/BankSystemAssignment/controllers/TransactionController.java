package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // POST - create customer
    @PostMapping("/create")
    public ResponseEntity<TransactionDto> saveTran(@RequestBody TransactionDto transactionDto){
        TransactionDto transactionDto1 = this.transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transactionDto1, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TransactionDto>> getTransactionDetails() {
        List<TransactionDto> list=this.transactionService.getTransactionDetails();
        return new ResponseEntity<>(list,HttpStatus.CREATED);
    }



    @PutMapping(value = "/deposit")
    public ResponseEntity<?> depositAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
        transactionService.getDepositAmount( accountNumber,amount);
        return new ResponseEntity<String>("Amount deposit successfully...", HttpStatus.OK);
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<?> withdrawAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
//        System.out.println("Account Number is " +accountNumber);
//        System.out.println("Deposit Amount is " +amount);
        transactionService.getWithDrawAmount( accountNumber,amount);
        return new ResponseEntity<String>("Amount withdraw successfully...", HttpStatus.OK);
    }

    @PutMapping(value = "/transferAmount")
    public ResponseEntity<?> transferAmount(@PathParam("senderAccountNumber") Integer senderAccountNumber,@PathParam("receiverAccountNumber") Integer receiverAccountNumber,@PathParam("amount") Double amount){
        System.out.println("Sender Account Number is "+senderAccountNumber);
        System.out.println("Receiver Account Number is "+receiverAccountNumber);
        System.out.println("Amount is "+amount);
        transactionService.amountTransfer(senderAccountNumber,receiverAccountNumber,amount);
        return new ResponseEntity<String>("Amount transfer successfully....",HttpStatus.OK);
    }
}
