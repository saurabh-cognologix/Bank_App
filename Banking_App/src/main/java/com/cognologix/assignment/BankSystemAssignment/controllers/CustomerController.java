package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CreateCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.CustomerUpdateResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.DeleteSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.responses.customerResponses.GetSingleCustomerResponse;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    //POST - CREATE A NEW CUSTOMER
    @PostMapping("/createCustomer")
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CustomerDto customerDto){
        CreateCustomerResponse createCustomerResponse = this.customerServices.createCustomer(customerDto);
        HttpStatus httpStatus = createCustomerResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(createCustomerResponse, httpStatus);
    }

    //PUT - UPDATE CUSTOMER
    @PutMapping("/updateCustomer/{customerId}")
   public ResponseEntity<CustomerUpdateResponse> updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable("customerId")Integer customerId ){
        CustomerUpdateResponse customerUpdateResponse = this.customerServices.updateCustomerDetails(customerDto);
        HttpStatus httpStatus = customerUpdateResponse.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        System.out.println(customerUpdateResponse.getSuccess());
        return new ResponseEntity<>(customerUpdateResponse,httpStatus);
   }


    //DELETE - DELETE CUSTOMER BY CUSTOMER-ID
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<DeleteSingleCustomerResponse> deleteCustomer(@PathVariable("customerId") Integer customerId ){
        DeleteSingleCustomerResponse deleteSingleCustomerResponse = this.customerServices.deleteSingleCustomer(customerId);
        HttpStatus httpStatus = deleteSingleCustomerResponse.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(deleteSingleCustomerResponse,httpStatus);
    }


    //GET - GET CUSTOMER BY ID
    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<GetSingleCustomerResponse> getCustomerById(@PathVariable("customerId") Integer customerId){
        GetSingleCustomerResponse getSingleCustomerResponse = this.customerServices.getCustomerById(customerId);
        HttpStatus httpStatus = getSingleCustomerResponse.getSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(getSingleCustomerResponse,httpStatus);
    }

}
