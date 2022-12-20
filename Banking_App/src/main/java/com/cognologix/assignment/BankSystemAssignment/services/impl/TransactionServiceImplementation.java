package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.TransactionConvertor;

import com.cognologix.assignment.BankSystemAssignment.dao.AccountRepo;
import com.cognologix.assignment.BankSystemAssignment.dao.TransactionRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.exception.InsufficientBalanceException;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import com.cognologix.assignment.BankSystemAssignment.responses.transactionResponses.TransactionResponse;
import com.cognologix.assignment.BankSystemAssignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImplementation implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionConvertor transactionConvertor;

//    @Override
//    public TransactionDto createTransaction(TransactionDto transactionDto) {
//        Transaction transaction = this.transactionConvertor.dtoToTransaction(transactionDto);
//        Transaction transaction1 = this.transactionRepo.save(transaction);
//        return  this.transactionConvertor.transactionToDto(transaction1);
//    }

    @Override
    public List<TransactionDto> getTransactionDetails() {
        List<Transaction> list=this.transactionRepo.findAll();
        List<TransactionDto> transDtos =list.stream().map(e-> this.transactionConvertor.transactionToDto(e))
                .collect(Collectors.toList());
        return transDtos;
    }

    @Override
    public Optional<TransactionDto> getTransactionById(Integer transactionId) {
        Transaction transaction = this.transactionRepo.findById(transactionId)
                .orElseThrow(()->new ResourceNotFoundException("Transaction","Id",transactionId));
        TransactionDto transactionDto = this.transactionConvertor.transactionToDto(transaction);
        return Optional.ofNullable(transactionDto);
    }

    //Deposit Amount in One Account
    @Override
    public TransactionResponse depositAmount(Integer accountNumber, Double depositAmount) {
        Transaction transaction = new Transaction();

        //checking account is exit or not in account table
        Account account = this.accountRepo.findById(accountNumber)
                        .orElseThrow(()->new ResourceNotFoundException("Account","id",accountNumber));

        transaction.setTransferAmount(depositAmount);
        //transaction.setWithdrawAmount(0.0);
        transaction.setReceiverAccountNumber(accountNumber);
        transaction.setSenderAccountNumber(null);

        //finding total balance
        Double totalBalance = account.getAccountBalance();
        System.out.println("Total balance is "+totalBalance);

        Double balanceAfterDeposit = totalBalance + depositAmount;

        System.out.println("total balance is " +balanceAfterDeposit);

        transaction.setTotalAmount(balanceAfterDeposit);

        //setting total balance in account model
        account.setAccountBalance(balanceAfterDeposit);

        this.transactionRepo.save(transaction);
        this.accountRepo.save(account);

        TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Deposit Successfully");
        return transactionResponse;

    }

    //Withdraw Money From One Account
    @Override
    public TransactionResponse getWithDrawAmount(Integer accountNumber, Double withdrawAmount) {

        Transaction transaction = new Transaction();

        //checking account is exit or not in account table
        Account account = this.accountRepo.findById(accountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","id",accountNumber));


        Double totalBalance = account.getAccountBalance();

        if(totalBalance>=withdrawAmount){
            transaction.setTransferAmount(withdrawAmount);
            //transaction.setDepositAmount(0.0);
            transaction.setSenderAccountNumber(accountNumber);
            Double balanceAfterWithdrawl = totalBalance-withdrawAmount;
            System.out.println("Balance After withdrawal is " +balanceAfterWithdrawl);

            transaction.setTotalAmount(balanceAfterWithdrawl);
            account.setAccountBalance(balanceAfterWithdrawl);

            this.transactionRepo.save(transaction);
            this.accountRepo.save(account);
            TransactionResponse transactionResponse = new TransactionResponse(true,"Amount Withdraw Successfully");
            return transactionResponse;
        }else {
            throw new InsufficientBalanceException("Your Account Balance is Low");
        }

    }


    // Transfer amount from one account to another

    @Override
    public TransactionResponse amountTransfer(Integer senderAccountNumber, Integer receiverAccountNumber, Double transferAmount) {
        //sender
        Transaction transaction = new Transaction();

        //checking account is exit or not in account table
        Account senderAccount = this.accountRepo.findById(senderAccountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","id",senderAccountNumber));

        //checking receiverAccountNumber is exit or not in account table
        Account receiverAccount = this.accountRepo.findById(receiverAccountNumber)
                .orElseThrow(()->new ResourceNotFoundException("Account","id",receiverAccountNumber));

        //finding total balance
        Double totalBalance = senderAccount.getAccountBalance();

        if(totalBalance>=transferAmount){
            Double balanceAfterWithdrawl = totalBalance-transferAmount;
            System.out.println("Balance After withdrawal is " +balanceAfterWithdrawl);
            transaction.setSenderAccountNumber(senderAccountNumber);
            transaction.setTransferAmount(transferAmount);
            transaction.setTotalAmount(balanceAfterWithdrawl);
            this.transactionRepo.save(transaction);
            this.accountRepo.save(senderAccount);
        }else {
            throw new InsufficientBalanceException("Your Account Balance is Low");
        }
        //Receiver

        Double totalBalanceOfReceiver = receiverAccount.getAccountBalance();


             transaction.setTransferAmount(transferAmount);
            // transaction.setDepositAmount(0.0);
             transaction.setReceiverAccountNumber(receiverAccountNumber);

            // transaction.setSenderAccountNumber();
            Double balanceAfterWithdrawl = totalBalanceOfReceiver+transferAmount;
            System.out.println("Balance After withdrawal is " +balanceAfterWithdrawl);

            transaction.setTotalAmount(balanceAfterWithdrawl);
            receiverAccount.setAccountBalance(balanceAfterWithdrawl);

            this.transactionRepo.save(transaction);
            this.accountRepo.save(receiverAccount);
            TransactionResponse transactionResponse = new TransactionResponse(true,"Amount transfer successfully....");
            return transactionResponse;
    }
}
