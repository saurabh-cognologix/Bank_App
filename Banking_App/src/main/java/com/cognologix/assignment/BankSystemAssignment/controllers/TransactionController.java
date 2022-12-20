package com.cognologix.assignment.BankSystemAssignment.controllers;


import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.responses.transactionResponses.TransactionResponse;
import com.cognologix.assignment.BankSystemAssignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/transaction") //Base Url
public class TransactionController {
    @Autowired //after spring 4.3 onward we can skip Autowired Annotation
    private TransactionService transactionService;

    // POST - create customer
//    @PostMapping("/create")
//    public ResponseEntity<TransactionDto> saveTran(@RequestBody TransactionDto transactionDto){
//        TransactionDto transactionDto1 = this.transactionService.createTransaction(transactionDto);
//        return new ResponseEntity<>(transactionDto1, HttpStatus.OK);
//    }

    // GET Handler : get all transaction details
    @GetMapping("/get")
    public ResponseEntity<List<TransactionDto>> getTransactionDetails() {
        List<TransactionDto> list=this.transactionService.getTransactionDetails();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }



    @PutMapping(value = "/deposit")
    public ResponseEntity<TransactionResponse> depositAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
        System.out.println("Deposit Account Number " +accountNumber);
        System.out.println("Amount is "+amount);
        TransactionResponse transactionResponse = transactionService.depositAmount( accountNumber,amount);
        HttpStatus httpStatus = transactionResponse.getSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(transactionResponse, httpStatus);
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<TransactionResponse> withdrawAmount(@PathParam("accountNumber") Integer accountNumber, @PathParam("amount") Double amount ) {
        TransactionResponse transactionResponse = transactionService.getWithDrawAmount( accountNumber,amount);
        HttpStatus httpStatus = transactionResponse.getSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(transactionResponse, httpStatus);
    }

    @PutMapping(value = "/transferAmount")
    public ResponseEntity<TransactionResponse> transferAmount(@PathParam("senderAccountNumber") Integer senderAccountNumber,@PathParam("receiverAccountNumber") Integer receiverAccountNumber,@PathParam("amount") Double amount){
        System.out.println("Sender Account Number is "+senderAccountNumber);
        System.out.println("Receiver Account Number is "+receiverAccountNumber);
        System.out.println("Amount is "+amount);
        TransactionResponse transactionResponse = transactionService.amountTransfer(senderAccountNumber,receiverAccountNumber,amount);
        HttpStatus httpStatus = transactionResponse.getSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(transactionResponse,httpStatus);
    }


    // Get Transaction By ID
    @GetMapping("/getTransaction/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("transactionId") Integer transactionId){
        // return ResponseEntity.ok(this.customerServices.getCustomerById(customerId));
        return this.transactionService.getTransactionById(transactionId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
