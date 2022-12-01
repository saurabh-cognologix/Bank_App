package com.cognologix.assignment.BankSystemAssignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;



    private Integer accountNumber;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="deposit_amount")
    private Double depositAmount;

    @Column(name="withdraw_amount")
    private Double withdrawAmount;



    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();
}
