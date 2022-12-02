package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Integer accountNumber;
//    private Integer senderAccountNumber;
//    private Integer receiverAccountNumber;
    private Double totalAmount;
    private Double transferAmount;
   // private Double withdrawAmount;
}
