package com.cognologix.assignment.BankSystemAssignment.dao;

import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    public Transaction findByAccountNumber(Integer accountNumber);

}
