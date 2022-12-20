package com.cognologix.assignment.BankSystemAssignment.dao;

import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
