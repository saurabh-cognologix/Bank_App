package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Integer accountNumber;
    private String accountHolderName;
    private String accountTypes;
}
