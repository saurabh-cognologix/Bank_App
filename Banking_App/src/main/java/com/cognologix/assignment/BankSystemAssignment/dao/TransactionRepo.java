package com.cognologix.assignment.BankSystemAssignment.dao;

import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
