package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerServices {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerServiceImplementation(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = this.dtoToCustomer(customerDto);
        Customer savedCustomer = this.customerRepo.save(customer);
        return this.customerToDto(savedCustomer);

    }

    @Override
    public CustomerDto updateCustomerDetails(CustomerDto customerDto,Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",customerId));

        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerMobileNumber(customerDto.getCustomerMobileNumber());
        customer.setCustomerDateOfBirth(customerDto.getCustomerDateOfBirth());
        customer.setCustomerPanCardNumber(customerDto.getCustomerPanCardNumber());
        customer.setCustomerAadharCardNumber(customerDto.getCustomerAadharCardNumber());

        Customer updatedCustomer = this.customerRepo.save(customer);
        CustomerDto customerDto1 = this.customerToDto(updatedCustomer);
        return customerDto1;
    }

    @Override
    public List<CustomerDto> getCustomerDetails() {
        List<Customer> customer = this.customerRepo.findAll();
        System.out.println(customer);
        List<CustomerDto> customerDtos = customer.stream().map(customer1 -> this.customerToDto(customer1)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public CustomerDto getCustomerById(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        CustomerDto customerDto = this.customerToDto(customer);
        return customerDto;
    }


    @Override
    public void deleteCustomer(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","Id",customerId));
        this.customerRepo.delete(customer);
    }

    public Customer dtoToCustomer(CustomerDto customerDto){
        Customer customer = this.modelMapper.map(customerDto,Customer.class);
//        customer.setCustomerId(customerDto.getCustomerId());
//        customer.setCustomerName(customerDto.getCustomerName());
//        customer.setCustomerEmail(customerDto.getCustomerEmail());
//        customer.setCustomerMobileNumber(customerDto.getCustomerMobileNumber());
//        customer.setCustomerDateOfBirth(customerDto.getCustomerDateOfBirth());
//        customer.setCustomerPanCardNumber(customerDto.getCustomerPanCardNumber());
//        customer.setCustomerAadharCardNumber(customerDto.getCustomerAadharCardNumber());
        return customer;
    }

    public CustomerDto customerToDto(Customer customer){
        CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
//        customerDto.setCustomerId(customer.getCustomerId());
//        customerDto.setCustomerName(customer.getCustomerName());
//        customerDto.setCustomerEmail(customer.getCustomerEmail());
//        customerDto.setCustomerMobileNumber(customer.getCustomerMobileNumber());
//        customerDto.setCustomerDateOfBirth(customer.getCustomerDateOfBirth());
//        customerDto.setCustomerPanCardNumber(customer.getCustomerPanCardNumber());
//        customerDto.setCustomerAadharCardNumber(customer.getCustomerAadharCardNumber());
        return customerDto;
    }
}
