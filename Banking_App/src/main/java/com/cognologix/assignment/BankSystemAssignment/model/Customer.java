package com.cognologix.assignment.BankSystemAssignment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "customer_table")
@Entity
public class Customer {

    @Id

    private Integer customerId;

    @Column(name="name")
    @NotEmpty
    @Size(min=4, message = "Customer Name must be of min 4 character")
    private String customerName;

    @NotEmpty
    @Size(min = 10,max=12, message = "Mobile Number must be of minimum 10 number and maximum 12 number")
    @Column(name="mobile_number")
    private String customerMobileNumber;

    @Column(name="email")
    @Email(message="Email address is not valid !!")
    private String customerEmail;

    @NotEmpty
    @Column(name="panCard_number",length = 10)
    private String customerPanCardNumber;

    @NotEmpty
    @Column(name="aadhar_card_number")
    @Size(min = 11,max = 12,message = "Aadhar Card Number is of 12 digit")
    private String customerAadharCardNumber;

    @NotEmpty
    @Column(name="date_of_birth")
    private String customerDateOfBirth;

//    @OneToMany(targetEntity = Account.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "ca_fk", referencedColumnName = "customerId")
//    private List<Account> account;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();

}
