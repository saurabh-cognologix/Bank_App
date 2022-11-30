package com.cognologix.assignment.BankSystemAssignment.dao;

import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
