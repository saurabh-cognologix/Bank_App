package com.cognologix.assignment.BankSystemAssignment.services;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CreateCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CustomerUpdateResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.DeleteSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.GetSingleCustomerResponse;

public interface CustomerServices {
    CreateCustomerResponse createCustomer(CustomerDto customerDto);
    GetSingleCustomerResponse getCustomerById(Integer customerId);
    CustomerUpdateResponse updateCustomerDetails(CustomerDto customerDto);
    DeleteSingleCustomerResponse deleteSingleCustomer(Integer customerId);

}
