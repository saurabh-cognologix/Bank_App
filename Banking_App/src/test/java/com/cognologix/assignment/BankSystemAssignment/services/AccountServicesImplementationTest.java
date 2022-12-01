//package com.cognologix.assignment.BankSystemAssignment.services;
//
//import com.cognologix.assignment.BankSystemAssignment.model.Account;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountServicesImplementationTest {
//    @Autowired
//    private Account account;
//    List<Account> accountList = new ArrayList<>();
//    @Test
//    void saveAccount() {
//        Account account = new Account();
//        account.setAccountNumber(18);
//        account.setTypeOfAccount("Saving");
//        accountList.add(account);
//        Assertions.assertNotNull(accountList);
//    }
//
//    @Test
//    void getAccountDetails() {
//        Account account = new Account();
//        account.setAccountNumber(18);
//        account.setTypeOfAccount("Saving");
//        boolean acc= accountList.add(account);
//        assertThat(acc).isTrue();
//    }
//
//    @Test
//    void getAccountByNumber() {
//        Account account = new Account();
//        account.setAccountNumber(1);
//        account.setTypeOfAccount("Saving");
//        accountList.add(account);
//        Account accountNumber=accountList.get(0);
//        assertEquals(1,accountNumber.getAccountNumber());
//    }
//
//    @Test
//    void addAccountDetails() {
//        Account account = new Account();
//        account.setAccountNumber(1);
//        account.setTypeOfAccount("Saving");
//        accountList.add(account);
//        boolean acc1=accountList.add(account);
//        assertThat(acc1).isTrue();
//    }
//
//    @Test
//    void getDepositAmount() {
//        Account account = new Account();
//        account.setAccountNumber(1);
//        account.setTypeOfAccount("Saving");
//        account.setTotalAmount(780.00);
//        account.setDepositAmount(10.00);
//        account.setWithdrawAmount(0.00);
//        Double amount=account.getTotalAmount()+account.getDepositAmount();
//        assertEquals(790,amount);
//    }
//
//    @Test
//    void getWithDrawAmount() {
//        Account account = new Account();
//        account.setAccountNumber(1);
//        account.setTypeOfAccount("Saving");
//        account.setTotalAmount(780.00);
//        account.setDepositAmount(0.00);
//        account.setWithdrawAmount(10.00);
//        Double amount=account.getTotalAmount()-account.getWithdrawAmount();
//        assertEquals(770,amount);
//    }
//
//    @Test
//    void deleteAccount() {
//        Account account = new Account();
//        account.setAccountNumber(1);
//        account.setTypeOfAccount("Saving");
//        accountList.add(account);
//        boolean acc1=accountList.remove(account.getAccountNumber());
//        assertThat(acc1).isFalse();
//    }
//}