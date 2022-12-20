package com.cognologix.assignment.BankSystemAssignment.controllers;


import com.cognologix.assignment.BankSystemAssignment.convertor.CustomerConvertor;
import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CreateCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CustomerUpdateResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.DeleteSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.GetSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @WebMvcTest annotation to test Spring MVC Controllers
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerServices customerServices;

    //We use MockMvc to call Rest Api
    @Autowired
    private MockMvc mockMvc;

    //For reading and writing JSON
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
                 .customerEmail("sonu@gmial.com")
                .customerPanCardNumber("KFCH12345")
                .customerAadharCardNumber("123456789123")
                .customerDateOfBirth("11/11/11")
                .build();


    }

    @Test
    void createCustomer() throws Exception {
        CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse(true,
                "Customer Registered Successfully",customerDto
        );

        //given - setup or precondition
        //Here we stubbed the createCustomer method
        given(customerServices.createCustomer(any(CustomerDto.class))).willReturn(createCustomerResponse);

        //when -  action or the behaviour that we are going test
        //call to the rest api
        //post is static method of MockMvcRequestBuilder class
        ResultActions response = mockMvc.perform(post("/customer/createCustomer")
                //pass content type as a JSON
                .contentType(MediaType.APPLICATION_JSON)
                //to convert object into JSON
                .content(objectMapper.writeValueAsString(createCustomerResponse.getCustomerDto())));

        //then - verify
        response.andDo(print()) //to print the response of Rest API
                //jsonPath expect two argument i.e. expression and second one is matcher type
                // jsonPath -> it is method of MockMvcResultMatchers Class
                // is -> it is a method of CoreMatchers Class
                // $.success -> actual value
                // createCustomerResponse.getSuccess() -> expected value
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(createCustomerResponse.getSuccess())))
                .andExpect(jsonPath("$.message", is(createCustomerResponse.getMessage())));
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

        CustomerUpdateResponse customerSavedResponse = new CustomerUpdateResponse(true,"User",savedCustomerDto);
        GetSingleCustomerResponse getSingleCustomerResponse = new GetSingleCustomerResponse(false,
                "Customer is found",customerDto);
        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .customerName("Monu")
                .customerMobileNumber("9876543210")
                .customerPanCardNumber("KFCH54321")
                .customerAadharCardNumber("1234567")
                .customerDateOfBirth("11/11/11")
                .build();

        CustomerUpdateResponse customerUpdatedResponse = new CustomerUpdateResponse(true,"User",updatedCustomerDto);

        given(customerServices.getCustomerById(customerId)).willReturn(getSingleCustomerResponse);
//        given(customerServices.updateCustomerDetails(any(CustomerDto.class)))
//               .willAnswer((invocation)-> invocation.getArgument(0));
        given(customerServices.updateCustomerDetails(any(CustomerDto.class))).willReturn(customerSavedResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/customer/updateCustomer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerUpdatedResponse.getCustomerDto())));

        //then - verify
        response.andExpect(status().isOk())
                .andDo(print()); // to print the response of REST API
//                .andExpect(jsonPath("$.customerName", is(updatedCustomerDto.getCustomerName())))
//                .andExpect(jsonPath("$.customerEmail", is(updatedCustomerDto.getCustomerEmail())));
    }

    @Test
    void updateCustomerNegativeScenario() throws Exception{
        Integer customerId = 101;
        CustomerDto savedCustomerDto = CustomerDto.builder()
                .customerName("Sonu")
                .customerMobileNumber("1234567890")
                .customerPanCardNumber("KFCH12345")
                .customerAadharCardNumber("1234567890")
                .customerDateOfBirth("12/11/11")
                .build();

        CustomerUpdateResponse customerSavedResponse = new CustomerUpdateResponse(false,"User",savedCustomerDto);
        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .customerName("Monu")
                .customerMobileNumber("9876543210")
                .customerPanCardNumber("KFCH54321")
                .customerAadharCardNumber("1234567890")
                .customerDateOfBirth("11/11/11")
                .build();

        CustomerUpdateResponse customerUpdatedResponse = new CustomerUpdateResponse(true,"User",updatedCustomerDto);
        GetSingleCustomerResponse getSingleCustomerResponse = new GetSingleCustomerResponse(false,
                "Customer is found",customerDto);

        //given(customerServices.getCustomerById(customerId)).willReturn(getSingleCustomerResponse);
//        given(customerServices.updateCustomerDetails(any(CustomerDto.class)))
//                .willAnswer((invocation)-> invocation.getArgument(0));

        given(customerServices.updateCustomerDetails(any(CustomerDto.class))).willReturn(customerSavedResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/customer/updateCustomer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerUpdatedResponse.getCustomerDto())));

        //then - verify
        response.andExpect(status().isNotFound())
                .andDo(print());
                // to print the response of REST API

    }

    //Junit test for GET Customer By ID
    //Positive Scenario - valid customer ID
    @Test
    void deleteCustomer() throws Exception {
        DeleteSingleCustomerResponse deleteSingleCustomerResponse = new DeleteSingleCustomerResponse(true,"Customer Deleted SuccessFully");
        // given - precondition or setup
        Integer customerId= 101;
        //willDoNothing().given(customerServices).deleteSingleCustomer(customerId);
        given(customerServices.deleteSingleCustomer(customerId)).willReturn(deleteSingleCustomerResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/customer/deleteCustomer/{customerId}",customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }

    //Junit test for GET Customer By ID
    //Negative Scenario

    @Test
    void deleteCustomerInNegativeScenario() throws Exception {
        DeleteSingleCustomerResponse deleteSingleCustomerResponse = new DeleteSingleCustomerResponse(false,"Customer Deleted SuccessFully");
        // given - precondition or setup
        Integer customerId= 101;
        //willDoNothing().given(customerServices).deleteSingleCustomer(customerId);
        given(customerServices.deleteSingleCustomer(customerId)).willReturn(deleteSingleCustomerResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/customer/deleteCustomer/{customerId}",customerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

//    @Test
//    void getAllCustomer() throws Exception {
//        List<CustomerDto> listOfAccount = new ArrayList<>();
//
//        listOfAccount.add(CustomerDto.builder().customerId(200).customerName("Sonu").customerEmail("sonu@gmail.com").customerMobileNumber("0123456789").customerAadharCardNumber("12345").customerPanCardNumber("123456").customerDateOfBirth("12/12/12").build());
//        listOfAccount.add(CustomerDto.builder().customerId(201).customerName("Monu").customerEmail("monu@gmail.com").customerMobileNumber("0123456789").customerAadharCardNumber("54321").customerPanCardNumber("654321").customerDateOfBirth("21/21/21").build());
//
//        given(customerServices.).willReturn(listOfAccount);
//
//        // when -  action or the behaviour that we are going test
//        ResultActions response = mockMvc.perform(get("/customer/getCustomer"));
//
//        // then - verify the output
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.size()",
//                        is(listOfAccount.size())));
//    }

    //Junit test for GET Customer By ID
    //Positive Scenario - valid customer ID
    @Test
    void getCustomerById() throws Exception {
        // given - precondition or setup
        Integer customerId = 501;
        GetSingleCustomerResponse getSingleCustomerResponse = new GetSingleCustomerResponse(true,
                "Customer is found",customerDto);
        given(customerServices.getCustomerById(customerId)).willReturn(getSingleCustomerResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer/{customerId}", customerId));

        // then - verify the output
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.success", is(getSingleCustomerResponse.getSuccess())));
    }

    //Junit test for GET Customer By ID
    //Negative Scenario - not valid customer ID
    @Test
    void getCustomerByIdNegativeScenario() throws Exception {
        GetSingleCustomerResponse getSingleCustomerResponse = new GetSingleCustomerResponse(false,
                "Customer is found",customerDto);
        // given - precondition or setup
        Integer customerId = 501;
        given(customerServices.getCustomerById(customerId)).willReturn(getSingleCustomerResponse);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/getCustomer/{customerId}", customerId));

        // then - verify the output
        response
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}