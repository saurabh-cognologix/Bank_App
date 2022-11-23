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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplementationTest {

    @Mock
    private CustomerRepo customerRepo;

    private CustomerServices customerServices;

    private CustomerServiceImplementation customerServiceImplementation;




    @BeforeEach
    void setUp() {
        this.customerServiceImplementation= new CustomerServiceImplementation(this.customerRepo);
    }

    @Test
    void saveCustomer() {
        CustomerDto customerDto = new CustomerDto(1,"mona","97358","m@gmail.com","hrt123","12345","20/09/2000");
        customerServiceImplementation.saveCustomer(customerDto);
        Customer customer = this.customerServiceImplementation.dtoToCustomer(customerDto);
        verify(customerRepo).save(customer);

//        Customer customer1=new Customer();
//        customer1.setCustomerId(101);
//        customer1.setCustomerName("mona");
//        Customer createOrNot = customerRepo.save(customer1);
//        Assertions.assertEquals(101,customer1.getCustomerId());

    }

    @Test
    void updateCustomerDetails() {
    }

    @Test
    void getCustomerDetails() {
        customerServiceImplementation.getCustomerDetails();
        verify(customerRepo).findAll();
    }

    @Test
    void getCustomerById() {
        customerServiceImplementation.getCustomerById(1);
        verify(customerRepo).findById(1);
    }

    @Test
    void testGetCustomerDetails() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void dtoToCustomer() {
    }

    @Test
    void customerToDto() {
    }
}