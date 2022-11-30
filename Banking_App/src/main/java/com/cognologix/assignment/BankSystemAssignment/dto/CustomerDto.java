package com.cognologix.assignment.BankSystemAssignment.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Integer customerId;

    @NotNull
    @Size(min=4, message = "Customer Name must be of minimum 4 character")
    private String customerName;

    @NotEmpty
    @Size(min = 10,max=12, message = "Mobile Number must be of minimum 10 number and maximum 12 number")
    private String customerMobileNumber;

    @Email
    private String customerEmail;

    @NotNull
    private String customerPanCardNumber;

    @NotNull
    private String customerAadharCardNumber;

    @NotNull
    private String customerDateOfBirth;

    //private List<AccountDto> accountDto;

}
