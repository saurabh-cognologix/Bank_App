package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServices {
    CustomerDto saveCustomer(CustomerDto customerDto);
    List<CustomerDto> getCustomerDetails();

    Optional<CustomerDto> getCustomerById(Integer customerId);
    CustomerDto updateCustomerDetails(CustomerDto customerDto);
    void deleteCustomer(Integer customerId);


}
