package com.cognologix.assignment.BankSystemAssignment.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer_table")
public class Customer {
    @Id
    private Integer customerId;

    @Column(name="name")
    @NotEmpty
    @Size(min=4, message = "Customer Name must be of min 4 character")
    private String customerName;

    @NotEmpty
    @Size(min=10,max = 12)
    @Column(name="mobile_number")
    private String customerMobileNumber;

    @Column(name="email")
    @Email(message="Email address is not valid !!")
    private String customerEmail;

    @NotEmpty
    @Column(name="panCard_number")
    private String customerPanCardNumber;

    @NotEmpty
    @Column(name="aadhar_card_number")
    private String customerAadharCardNumber;

    @NotEmpty
    @Column(name="date_of_birth")
    private String customerDateOfBirth;

    @OneToMany(targetEntity = Account.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ca_fk", referencedColumnName = "customerId")
    private List<Account> account;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();

}
