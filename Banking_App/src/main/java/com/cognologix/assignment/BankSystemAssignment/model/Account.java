package com.cognologix.assignment.BankSystemAssignment.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="account_table")
public class Account {

    @Id
    private Integer accountNumber;

    private String accountHolderName;

    private String accountTypes;


    private Double accountBalance;


    @OneToMany(targetEntity = Transaction.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ta_fk", referencedColumnName = "accountNumber")
    private List<Transaction> transactionList;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();

}
