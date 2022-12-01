//package com.cognologix.assignment.BankSystemAssignment.globalobject;
//import com.cognologix.assignment.BankSystemAssignment.model.Account;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class BankOperations {
//        List<Account> accountList =new ArrayList<Account>();
//        //save account details
//        public void saveAccountDetails(Account acc){
//            accountList.add(acc);
//        }
//        //get all account details
//        public List<Account>getAllAccountDetails(){
//            return accountList;
//        }
//        // get single Account by id
//        public Account getAccountByNumber(int accountNumber) {
//            Account acc = null;
//            acc = accountList.stream().filter(e -> e.getAccountNumber() == accountNumber).findFirst().get();
//            return acc;
//        }
//        // create account
//        public Account addAccount(Account acc1) {
//            accountList.add(acc1);
//            return acc1;
//        }
//        // Deposit
//        public void getDeposit(Integer accountNumber,Double depositAmount) {
//            System.out.println(depositAmount);
//            List<Account> list = accountList.stream()
//                    .filter(e -> e.getAccountNumber()==accountNumber).collect(Collectors.toList());
//           // System.out.println("List is " + list);
//            if(list.isEmpty()){
//                System.out.println("Please enter correct account number....");
//            }
//            Account depositedAccount = list.get(0);
//           // System.out.println("deposited account is " +depositedAccount);
//
//            Double balanceAfterDeposit = depositedAccount.getTotalAmount() +depositAmount;
//           System.out.println("total balance is " +balanceAfterDeposit);
//
//            list.get(0).setTotalAmount(balanceAfterDeposit);
//
//        }
//        //Withdraw
//        public void getWithdraw(Integer accountNumber,Double withdrawAmount){
//            System.out.println(withdrawAmount);
//            List<Account> accountList1 = accountList.stream()
//                    .filter(e->e.getAccountNumber()==accountNumber)
//                    .collect(Collectors.toList());
//            if(accountList1.isEmpty()){
//                System.out.println("Please enter correct account number ");
//            }
//            Account depositedAccount = accountList1.get(0);
//            Double balanceAfterWithdraw = depositedAccount.getTotalAmount()-withdrawAmount;
//            accountList1.get(0).setTotalAmount(balanceAfterWithdraw);
//
//        }
//        //delete account
//        public void deleteAccount(Integer accountNumber){
//            accountList = accountList.stream().filter(book->book.getAccountNumber()!=accountNumber).collect(Collectors.toList());
//        }
//
//        public  void transferAmount(Integer senderAccountNumber, Integer receiverAccountNumber,Double transferAmount){
//            getWithdraw(senderAccountNumber,transferAmount);
//            getDeposit(receiverAccountNumber,transferAmount);
//        }
//}
//
