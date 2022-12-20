package com.cognologix.assignment.BankSystemAssignment.convertor;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvertor {
    @Autowired
    private ModelMapper modelMapper;

    //CustomerDto to Customer Conversion
    public Customer dtoToCustomer(CustomerDto customerDto){
        Customer customer = this.modelMapper.map(customerDto,Customer.class);
        return customer;
    }

    //Customer To CustomerDto Conversion
    public CustomerDto customerToDto(Customer customer) {
        CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }
}
