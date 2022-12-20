package com.cognologix.assignment.BankSystemAssignment.responses.customerResponses;

import com.cognologix.assignment.BankSystemAssignment.dto.CustomerDto;
import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class GetSingleCustomerResponse extends BaseResponse {
    private CustomerDto customerDto;
    public GetSingleCustomerResponse(Boolean success, String message, CustomerDto customerDto) {
        super(success);
        this.setMessage(message);
        this.customerDto=customerDto;
    }
}
