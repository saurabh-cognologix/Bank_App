package com.cognologix.assignment.BankSystemAssignment.controllers;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.payloads.ApiResponse;
import com.cognologix.assignment.BankSystemAssignment.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    // POST - create customer
    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto){
        System.out.println(customerDto.getAccountDto());
        CustomerDto customerDto1 = this.customerServices.saveCustomer(customerDto);
        return new ResponseEntity<>(customerDto1, HttpStatus.CREATED);

//        accountServices.createAccount(account);
//        return new ResponseEntity<>(account,HttpStatus.CREATED);

    }

    // PUT - update customer
    @PutMapping("/updateCustomer/{customerId}")
   public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto,@PathVariable("customerId")Integer customerId ){

       CustomerDto updatedCustomer = this.customerServices.updateCustomerDetails(customerDto,customerId);
       return ResponseEntity.ok(updatedCustomer);
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
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Integer customerId){
        return ResponseEntity.ok(this.customerServices.getCustomerById(customerId));
    }

}
