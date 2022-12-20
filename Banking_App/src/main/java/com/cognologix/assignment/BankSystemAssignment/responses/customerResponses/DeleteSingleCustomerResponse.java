package com.cognologix.assignment.BankSystemAssignment.responses.customerResponses;

import com.cognologix.assignment.BankSystemAssignment.model.Customer;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteSingleCustomerResponse extends BaseResponse {
    public DeleteSingleCustomerResponse(Boolean success, String message) {
        super(success);
        this.setMessage(message);
    }
}
