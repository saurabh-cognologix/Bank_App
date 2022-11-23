package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private Integer customerId;
    private String customerName;
    private String customerMobileNumber;
    private String customerEmail;
    private String customerPanCardNumber;
    private String customerAadharCardNumber;
    private String customerDateOfBirth;
    private List<AccountDto> accountDto;
}
