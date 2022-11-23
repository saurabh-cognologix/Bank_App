package com.cognologix.assignment.BankSystemAssignment.dao;

import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
