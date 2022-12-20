package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.responses.transactionResponses.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
   // TransactionDto createTransaction(TransactionDto transactionDto);

    List<TransactionDto> getTransactionDetails();

    Optional<TransactionDto> getTransactionById(Integer transactionId);
    TransactionResponse depositAmount(Integer accountNumber, Double depositAmount);
    TransactionResponse getWithDrawAmount(Integer accountNumber, Double withdrawAmount);
    TransactionResponse amountTransfer(Integer senderAccountNumber,Integer receiverAccountNumber, Double transferAmount);
}
