package com.cognologix.assignment.BankSystemAssignment.services.impl;

import com.cognologix.assignment.BankSystemAssignment.convertor.CustomerConvertor;
import com.cognologix.assignment.BankSystemAssignment.dao.CustomerRepository;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.exception.CustomerAlreadyExistException;
import com.cognologix.assignment.BankSystemAssignment.exception.ResourceNotFoundException;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;

import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CreateCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.GetSingleCustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceImplementationTest {

    @MockBean
    private CustomerRepository customerRepo;

    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    private CustomerConvertor customerConvertor;

    private CustomerDto customerDto;

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
    void createCustomer() {
        Customer customer = this.customerConvertor.dtoToCustomer(customerDto);

//        given(customerRepo.findById(customer.getCustomerId()))
//                .willReturn(Optional.of(customer));
        //given(customerRepo.save(customer)).willReturn(customer);

        given(customerRepo.findById(customer.getCustomerId())).willReturn(Optional.of(customer));
        given(customerRepo.save(customer)).willReturn(customer);

        // when - action or the behaviour that we are going test
        assertThrows(CustomerAlreadyExistException.class, () -> {
            customerServiceImplementation.createCustomer(customerDto);
        });

        // then
        verify(customerRepo, never()).save(any(Customer.class));

    }

    @Test
    void updateCustomerDetails() {
        // given - precondition or setup
        Customer customer = this.customerConvertor.dtoToCustomer(customerDto);
        given(customerRepo.save(customer)).willReturn(customer);

        //        when - action or the behaviour that we are going test
        assertThrows(ResourceNotFoundException.class, () -> {
             customerServiceImplementation.updateCustomerDetails(customerDto);
       });

       // CustomerUpdateResponse customerUpdateResponse = customerServiceImplementation.updateCustomerDetails(customerDto);

        // then - verify the output
       // org.assertj.core.api.Assertions.assertThat(customerUpdateResponse.getMessage()).isEqualTo("Updated successfully");
        //org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerEmail()).isEqualTo("raman@gmail.com");
       // org.assertj.core.api.Assertions.assertThat(updatedCustomer.getCustomerMobileNumber()).isEqualTo("9792733298");
        verify(customerRepo, never()).save(any(Customer.class));

    }

    //Positive Scenario
    //Junit test for customer details
    @Test
    void getCustomerDetails() {
//        // given - precondition or setup
//        CustomerDto customerDto1 = CustomerDto.builder()
//                .customerId(101)
//                .customerName("Monu")
//                .customerEmail("monu@gmail.com")
//                .customerAadharCardNumber("1234567890")
//                .customerPanCardNumber("0123456789")
//                .customerDateOfBirth("27/10/2018")
//                .build();
//
//        Customer customer = this.customerConvertor.dtoToCustomer(customerDto);
//        Customer customer1 = this.customerConvertor.dtoToCustomer(customerDto1);
//
//
//        given(customerRepo.findAll()).willReturn(List.of(customer,customer1));
//
//        // when -  action or the behaviour that we are going test
//        List<CustomerDto> customerList = customerServiceImplementation.getAllCustomerDetails();
//
//        // then - verify the output
//        assertThat(customerList).isNotNull();
//        assertThat(customerList.size()).isEqualTo(2);
    }

    //Negative Scenario
    //Get All Customer Details
    @Test
    void getCustomerDetailsInNegativeScenario() {
//        // given - precondition or setup
//        CustomerDto customerDto1 = CustomerDto.builder()
//                .customerId(101)
//                .customerName("Monu")
//                .customerEmail("monu@gmail.com")
//                .customerAadharCardNumber("1234567890")
//                .customerPanCardNumber("0123456789")
//                .customerDateOfBirth("27/10/2018")
//                .build();
//
//        Customer customer = this.customerConvertor.dtoToCustomer(customerDto);
//        Customer customer1 = this.customerConvertor.dtoToCustomer(customerDto1);
//
//
//        given(customerRepo.findAll()).willReturn(Collections.emptyList());
//
//        // when -  action or the behaviour that we are going test
//        List<CustomerDto> employeeList = customerServiceImplementation.getAllCustomerDetails();
//
//        // then - verify the output
//        assertThat(employeeList).isNotNull();
//        assertThat(employeeList.size()).isEqualTo(0);
    }


    //Positive Scenario
    //Junit Test for get customer by ID
    @Test
    void getCustomerById() {
        Customer customer = customerConvertor.dtoToCustomer(customerDto);

        //given
        given(customerRepo.findById(101)).willReturn(Optional.of(customer));
        // when
        GetSingleCustomerResponse savedUser = customerServiceImplementation.getCustomerById(customer.getCustomerId());
        // then
        org.assertj.core.api.Assertions.assertThat(savedUser).isNotNull();
    }

    //Negative Scenario
    //Junit Test for get Customer BY Id
    @Test
    void getCustomerByIdInNegativeScenario() {
        Customer customer = customerConvertor.dtoToCustomer(customerDto);
        //given
        given(customerRepo.findById(101)).willReturn(Optional.empty());
        // when

        ResourceNotFoundException savedUser = assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImplementation.getCustomerById(customer.getCustomerId());
        });

        // then
        org.assertj.core.api.Assertions.assertThat(savedUser).hasMessage("Customer not found with Id : 101");
    }

    @Test
    void deleteCustomer() {
        // given - precondition or setup
        Integer customerId = 101;
        willDoNothing().given(customerRepo).deleteById(customerId);

        // when -  action or the behaviour that we are going test
        customerServiceImplementation.deleteSingleCustomer(customerId);

        // then - verify the output
        verify(customerRepo, times(1)).deleteById(customerId);
    }

    @Test
    void deleteCustomerInNegativeScenario() {
        // given - precondition or setup
        Integer customerId = 102;
        willDoNothing().given(customerRepo).deleteById(customerId);

        // when -  action or the behaviour that we are going test
        customerServiceImplementation.deleteSingleCustomer(customerId);

        // then - verify the output
        verify(customerRepo, times(1)).deleteById(customerId);
    }

}