package com.cognologix.assignment.BankSystemAssignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_table")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    private Integer accountNumber;

    @Column(name = "sender_account_number")
    private Integer senderAccountNumber;

    @Column(name="receiver_account_number")
    private Integer receiverAccountNumber;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="transfer_amount")
    private Double transferAmount;

//    @Column(name="withdraw_amount")
//    private Double withdrawAmount;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();
}
