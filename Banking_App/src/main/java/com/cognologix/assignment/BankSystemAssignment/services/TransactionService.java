package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);

    List<TransactionDto> getTransactionDetails();
    void getDepositAmount(Integer accountNumber, Double depositAmount);
    void getWithDrawAmount(Integer accountNumber, Double withdrawAmount);
    void amountTransfer(Integer senderAccountNumber,Integer receiverAccountNumber, Double transferAmount);
}
