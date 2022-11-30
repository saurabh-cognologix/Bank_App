package com.cognologix.assignment.BankSystemAssignment.controllers;


import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerServices customerServices;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerController customerController;

    private CustomerDto customerDto;

    @BeforeEach
    void setUp(){
         customerDto = CustomerDto.builder()
                .customerId(101)
                .customerName("Sonu")
                .customerMobileNumber("1234567890")
                .customerPanCardNumber("KFCH12345")
                .customerAadharCardNumber("1234567")
                .customerDateOfBirth("11/11/11")
                .build();
    }

    @Test
    void saveCustomer() throws Exception {
        given(customerServices.saveCustomer(any(CustomerDto.class)))
                .willAnswer((e)-> e.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/customer/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)));



        //then - verify
        response.andDo(print())
                .andExpect(jsonPath("$.customerId", is(customerDto.getCustomerId())))
                .andExpect(jsonPath("$.customerName", is(customerDto.getCustomerName())))
                .andExpect(jsonPath("$.customerEmail", is(customerDto.getCustomerEmail())));
    }

    @Test
    void updateCustomer() throws Exception{
        Integer customerId = 101;
        CustomerDto savedCustomerDto = CustomerDto.builder()
                .customerName("Sonu")
                .customerMobileNumber("1234567890")
                .customerPanCardNumber("KFCH12345")
                .customerAadharCardNumber("1234567")
                .customerDateOfBirth("11/11/11")
                .build();
        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .customerName("Monu")
                .customerMobileNumber("9876543210")
                .customerPanCardNumber("KFCH54321")
                .customerAadharCardNumber("1234567")
                .customerDateOfBirth("11/11/11")
                .build();

        given(customerServices.getCustomerById(customerId)).willReturn(Optional.of(savedCustomerDto));
        given(customerServices.updateCustomerDetails(any(CustomerDto.class)))
               .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/customer/updateCustomer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomerDto)));

        //then - verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.customerName", is(updatedCustomerDto.getCustomerName())))
                .andExpect(jsonPath("$.customerEmail", is(updatedCustomerDto.getCustomerEmail())));
    }

    @Test
    void deleteCustomer() throws Exception {
        // given - precondition or setup
        Integer customerId= 101;
        willDoNothing().given(customerServices).deleteCustomer(customerId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/customer/deleteCustomer/{customerId}",customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllCustomer() throws Exception {
        List<CustomerDto> listOfAccount = new ArrayList<>();

        listOfAccount.add(CustomerDto.builder().customerId(200).customerName("Sonu").customerEmail("sonu@gmail.com").customerMobileNumber("0123456789").customerAadharCardNumber("12345").customerPanCardNumber("123456").customerDateOfBirth("12/12/12").build());
        listOfAccount.add(CustomerDto.builder().customerId(201).customerName("Monu").customerEmail("monu@gmail.com").customerMobileNumber("0123456789").customerAadharCardNumber("54321").customerPanCardNumber("654321").customerDateOfBirth("21/21/21").build());

        given(customerServices.getCustomerDetails()).willReturn(listOfAccount);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfAccount.size())));
    }

    @Test
    void getCustomerById() throws Exception {
        // given - precondition or setup
        Integer customerId = 501;
        given(customerServices.getCustomerById(customerId)).willReturn(Optional.ofNullable(customerDto));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer/{customerId}", customerId));

        // then - verify the output
        response
                .andDo(print())
                .andExpect(jsonPath("$.customerId", is(customerDto.getCustomerId())))
                .andExpect(jsonPath("$.customerName", is(customerDto.getCustomerName())))
                .andExpect(jsonPath("$.customerEmail", is(customerDto.getCustomerEmail())));
    }
}