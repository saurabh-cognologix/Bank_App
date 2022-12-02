package com.cognologix.assignment.BankSystemAssignment.controllers;


import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.payloads.ApiResponse;
import com.cognologix.assignment.BankSystemAssignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    // GET Handler : get all transaction details
    @GetMapping("/get")
    public ResponseEntity<List<TransactionDto>> getTransactionDetails() {
        List<TransactionDto> list=this.transactionService.getTransactionDetails();
        return new ResponseEntity<>(list,HttpStatus.CREATED);
    }



    @PutMapping(value = "/deposit")
    public ResponseEntity<ApiResponse> depositAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
        System.out.println("Deposit Account Number " +accountNumber);
        System.out.println("Amount is "+amount);
        transactionService.getDepositAmount( accountNumber,amount);
        return new ResponseEntity<>(new ApiResponse("Amount Deposited Successfully",true), HttpStatus.OK);
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<ApiResponse> withdrawAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
        transactionService.getWithDrawAmount( accountNumber,amount);
        return new ResponseEntity<>(new ApiResponse("Amount withdraw successfully...",true), HttpStatus.OK);
    }

    @PutMapping(value = "/transferAmount")
    public ResponseEntity<ApiResponse> transferAmount(@PathParam("senderAccountNumber") Integer senderAccountNumber,@PathParam("receiverAccountNumber") Integer receiverAccountNumber,@PathParam("amount") Double amount){
        System.out.println("Sender Account Number is "+senderAccountNumber);
        System.out.println("Receiver Account Number is "+receiverAccountNumber);
        System.out.println("Amount is "+amount);
        transactionService.amountTransfer(senderAccountNumber,receiverAccountNumber,amount);
        return new ResponseEntity<>(new ApiResponse("Amount transfer successfully....",true),HttpStatus.OK);
    }
}
