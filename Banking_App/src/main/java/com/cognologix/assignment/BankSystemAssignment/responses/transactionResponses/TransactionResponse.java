package com.cognologix.assignment.BankSystemAssignment.responses.transactionResponses;

import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.responses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse extends BaseResponse {

    //private TransactionDto transactionDto;

    public TransactionResponse(Boolean success,String message) {
        super(success);
        this.setMessage(message);
    }
}
