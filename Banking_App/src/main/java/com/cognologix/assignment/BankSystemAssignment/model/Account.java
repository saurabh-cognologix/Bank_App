package com.cognologix.assignment.BankSystemAssignment.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

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



    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date();

}
