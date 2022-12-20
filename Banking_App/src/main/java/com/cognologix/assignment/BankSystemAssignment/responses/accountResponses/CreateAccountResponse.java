package com.cognologix.assignment.BankSystemAssignment.responses.accountResponses;

import com.cognologix.assignment.BankSystemAssignment.dto.AccountDto;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;

public class CreateAccountResponse extends BaseResponse {
    private AccountDto accountDto;
    public CreateAccountResponse(String message,Boolean success,AccountDto accountDto) {
        super(success);
        this.setMessage(message);
        this.accountDto = accountDto;
    }
}
