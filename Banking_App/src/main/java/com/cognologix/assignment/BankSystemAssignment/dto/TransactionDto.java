package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer accountNumber;
    private Double totalAmount;
    private Double depositAmount;
    private Double withdrawAmount;
}
