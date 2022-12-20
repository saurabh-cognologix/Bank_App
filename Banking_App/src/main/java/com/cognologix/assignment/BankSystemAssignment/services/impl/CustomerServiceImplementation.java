package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.CustomerConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepository;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.exception.CustomerAlreadyExistException;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Account;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CreateCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CustomerUpdateResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.DeleteSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.GetSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConvertor customerConvertor;

    @Override
    public CreateCustomerResponse createCustomer(CustomerDto customerDto) {
        Customer customer = this.customerConvertor.dtoToCustomer(customerDto);
//        Customer newCustomer = this.customerRepository.findById(customer.getCustomerId())
//                .orElseThrow(()->new CustomerAlreadyExistException("Customer Is Already Present With Given Id "+customer.getCustomerId()));
        Optional<Customer> savedCustomer = customerRepository.findById(customer.getCustomerId());
        if(savedCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer Is Already Present With Given Id "+customer.getCustomerId());

        }
        customerRepository.save(customer);
        CustomerDto newCustomerDto = this.customerConvertor.customerToDto(customer);
        CreateCustomerResponse customerResponse = new CreateCustomerResponse(true,
                    "Customer Registered Successfully",newCustomerDto);
        return customerResponse;
    }

    @Override
    public CustomerUpdateResponse updateCustomerDetails(CustomerDto customerDto) {
            Customer customer = this.customerConvertor.dtoToCustomer(customerDto);
            Customer updatedCustomer = this.customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customer.getCustomerId()));
            this.customerRepository.save(customer);
            CustomerDto updatedCustomerDto = this.customerConvertor.customerToDto(updatedCustomer);
            CustomerUpdateResponse customerUpdateResponse = new CustomerUpdateResponse(true,
                "Updated successfully", updatedCustomerDto);
            return customerUpdateResponse;
    }

    @Override
    public GetSingleCustomerResponse getCustomerById(Integer customerId) {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        CustomerDto customerDto = this.customerConvertor.customerToDto(customer);
        GetSingleCustomerResponse getSingleCustomerResponse = new GetSingleCustomerResponse(true,"Customer Found With Account Id"+customerId,customerDto);
        return getSingleCustomerResponse;
    }


    @Override
    public DeleteSingleCustomerResponse deleteSingleCustomer(Integer customerId) {
        this.customerRepository.deleteById(customerId);
        DeleteSingleCustomerResponse deleteSingleCustomerResponse = new DeleteSingleCustomerResponse(true,"Customer Deleted SuccessFully");
        return deleteSingleCustomerResponse;
    }
}
