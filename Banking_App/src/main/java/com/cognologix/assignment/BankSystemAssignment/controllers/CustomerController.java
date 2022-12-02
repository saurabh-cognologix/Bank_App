package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.payloads.ApiResponse;
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
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    // POST - create customer
    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto){
      //  System.out.println(customerDto.getAccountDto());
        CustomerDto customerDto1 = this.customerServices.saveCustomer(customerDto);
        return new ResponseEntity<>(customerDto1, HttpStatus.CREATED);
    }

    // PUT - update customer
    @PutMapping("/updateCustomer/{customerId}")
   public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable("customerId")Integer customerId ){

        return customerServices.getCustomerById(customerId)
                .map(savedCustomer -> {
                    savedCustomer.setCustomerName(customerDto.getCustomerName());
                    savedCustomer.setCustomerEmail(customerDto.getCustomerEmail());
                    savedCustomer.setCustomerPanCardNumber(customerDto.getCustomerPanCardNumber());
                    savedCustomer.setCustomerAadharCardNumber(customerDto.getCustomerAadharCardNumber());
                    savedCustomer.setCustomerDateOfBirth(customerDto.getCustomerDateOfBirth());
                    savedCustomer.setCustomerMobileNumber(customerDto.getCustomerMobileNumber());

                    CustomerDto customerDto1=customerServices.updateCustomerDetails(savedCustomer);
                    return new ResponseEntity<>(customerDto1, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
   }


    // DELETE - delete customer
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Integer customerId ){
        this.customerServices.deleteCustomer(customerId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }


    // GET - get user
    @GetMapping("/getCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        return ResponseEntity.ok(this.customerServices.getCustomerDetails());
    }

    /*
    *  Get customer by Id
    * */
    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Optional<CustomerDto>> getCustomerById(@PathVariable("customerId") Integer customerId){
        return ResponseEntity.ok(this.customerServices.getCustomerById(customerId));
    }

}
