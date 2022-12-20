package com.cognologix.assignment.BankSystemAssignment.responses.customerResponses;

import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;

public class GetAllCustomerResponse extends BaseResponse {
    private Customer customer;
    public GetAllCustomerResponse(Boolean success, String message, Customer customer) {
        super(success);
        this.setMessage(message);
        this.customer=customer;
    }
}
