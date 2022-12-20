package com.cognologix.assignment.BankSystemAssignment.dto;

import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Integer accountNumber;
    private String accountHolderName;
    private String accountTypes;
    private Double accountBalance;
    //private List<Transaction> transactionList;
}
