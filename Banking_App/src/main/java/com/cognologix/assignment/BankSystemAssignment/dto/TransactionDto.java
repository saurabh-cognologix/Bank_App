package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TransactionDto {
    private Integer transactionId;
    private Integer accountNumber;
    private Double totalAmount;
    private Double transferAmount;
}
