package com.cognologix.assignment.BankSystemAssignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
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
