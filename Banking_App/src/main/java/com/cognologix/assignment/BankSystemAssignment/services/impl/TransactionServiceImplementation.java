package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.TransactionConvertor;

import com.cognologix.assignment.BankSystemAssignment.dao.TransactionRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import com.cognologix.assignment.BankSystemAssignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImplementation implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionConvertor transactionConvertor;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = this.transactionConvertor.dtoToTransaction(transactionDto);
        Transaction transaction1 = this.transactionRepo.save(transaction);
        return  this.transactionConvertor.transactionToDto(transaction1);
    }

    @Override
    public List<TransactionDto> getTransactionDetails() {
        List<Transaction> list=this.transactionRepo.findAll();
        List<TransactionDto> transDtos =list.stream().map(e-> this.transactionConvertor.transactionToDto(e))
                .collect(Collectors.toList());
        return transDtos;
    }

    /*
    * Deposit amount in another account
    * */
    @Override
    public void getDepositAmount(Integer accountNumber, Double depositAmount) {

        Transaction transaction = this.transactionRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",accountNumber));
        transaction.setDepositAmount(depositAmount);
        transaction.setWithdrawAmount(0.0);
        Double totalBalance = transaction.getTotalAmount();
        Double balanceAfterDeposit = totalBalance + depositAmount;
        System.out.println("total balance is " +balanceAfterDeposit);
        transaction.setTotalAmount(balanceAfterDeposit);
        this.transactionRepo.save(transaction);

    }
    /*
    * withdraw amount from one account
    * */
    @Override
    public void getWithDrawAmount(Integer accountNumber, Double withdrawAmount) {
        Transaction transaction = this.transactionRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",accountNumber));
        Double totalBalance = transaction.getTotalAmount();
//        if(totalBalance<withdrawAmount){
//            throw InsufficientBalanceException;
//        }
        transaction.setWithdrawAmount(withdrawAmount);
        transaction.setDepositAmount(0.0);
        Double balanceAfterWithdrawl = totalBalance-withdrawAmount;
        System.out.println("Balance After withdrawl is " +balanceAfterWithdrawl);
        transaction.setTotalAmount(balanceAfterWithdrawl);
        this.transactionRepo.save(transaction);
    }

    /*
    * Transfer amount from one account to another
    * */

    @Override
    public void amountTransfer(Integer senderAccountNumber, Integer receiverAccountNumber, Double transferAmount) {
       getDepositAmount(receiverAccountNumber,transferAmount);
       getWithDrawAmount(senderAccountNumber,transferAmount);
    }
}
