package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;

import java.util.List;

public interface CustomerServices {
    CustomerDto saveCustomer(CustomerDto customerDto);
    List<CustomerDto> getCustomerDetails();

    CustomerDto getCustomerById(Integer customerId);
    CustomerDto updateCustomerDetails(CustomerDto customerDto, Integer customerId);
    void deleteCustomer(Integer customerId);


}
