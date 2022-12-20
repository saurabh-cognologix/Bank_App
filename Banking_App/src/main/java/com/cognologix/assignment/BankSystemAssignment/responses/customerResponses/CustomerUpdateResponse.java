package com.cognologix.assignment.BankSystemAssignment.responses.customerResponses;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateResponse extends BaseResponse {
    private CustomerDto customerDto;
    public CustomerUpdateResponse(Boolean success, String message, CustomerDto customerDto) {
        super(success);
        this.setMessage(message);
        this.customerDto=customerDto;
    }
}
