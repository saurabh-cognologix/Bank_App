package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepo;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceImplementationTest {

    @MockBean
    private CustomerRepo customerRepo;


    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;

    private CustomerDto customerDto;

   // private Customer customer;


    @BeforeEach
    void setUp() {
        customerDto = CustomerDto.builder()
                 .customerId(101)
                .customerName("Monu")
                .customerEmail("monu@gmail.com")
                .customerAadharCardNumber("1234567890")
                .customerPanCardNumber("0123456789")
                .customerDateOfBirth("27/10/2018")
                .build();
    }

    @Test
    void saveCustomer() {

//        Customer customer = this.customerServiceImplementation.dtoToCustomer(customerDto);
//
//
//        given(customerRepo.findById(customer.getCustomerId()))
//                .willReturn(Optional.of(customer));
//        given(customerRepo.save(customer)).willReturn(customer);
//
//        CustomerDto savedCustomer = customerServiceImplementation.saveCustomer(customerDto);
//        System.out.println("Saved Customer is "+savedCustomer);
//        org.assertj.core.api.Assertions.assertThat(savedCustomer).isNotNull();

    }

    @Test
    void updateCustomerDetails() {
        // given - precondition or setup
        Customer customer = this.customerServiceImplementation.dtoToCustomer(customerDto);
        given(customerRepo.save(customer)).willReturn(customer);
        customerDto.setCustomerName("Raman");
        customerDto.setCustomerEmail("raman@gmail.com");
        customerDto.setCustomerMobileNumber("9792733298");
        customerDto.setCustomerAadharCardNumber("123456789");
        customerDto.setCustomerDateOfBirth("12/12/12");
        customerDto.setCustomerPanCardNumber("123456789");

        // when -  action or the behaviour that we are going test
        CustomerDto updatedCustomer = customerServiceImplementation.updateCustomerDetails(customerDto);

        // then - verify the output
        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerName()).isEqualTo("Raman");
        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerEmail()).isEqualTo("raman@gmail.com");
        org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerMobileNumber()).isEqualTo("9792733298");

    }

    @Test
    void getCustomerDetails() {
        customerServiceImplementation.getCustomerDetails();
        verify(customerRepo).findAll();
    }



    @Test
    void getCustomerById() {
        Customer customer = customerServiceImplementation.dtoToCustomer(customerDto);

        //given
        given(customerRepo.findById(101)).willReturn(Optional.of(customer));
        // when
        CustomerDto savedUser = customerServiceImplementation.getCustomerById(customer.getCustomerId()).get();
        // then
        org.assertj.core.api.Assertions.assertThat(savedUser).isNotNull();
    }



    @Test
    void deleteCustomer() {
        // given - precondition or setup
        Integer customerId = 101;

        willDoNothing().given(customerRepo).deleteById(customerId);

        // when -  action or the behaviour that we are going test
        customerServiceImplementation.deleteCustomer(customerId);

        // then - verify the output
        verify(customerRepo, times(1)).deleteById(customerId);
    }

}