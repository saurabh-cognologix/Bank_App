package com.cognologix.assignment.BankSystemAssignment.responses.accountResponses;

import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;

public class DeleteAccountResponse extends BaseResponse {
    public DeleteAccountResponse(Boolean success,String message) {
        super(success);
        this.setMessage(message);
    }
}
