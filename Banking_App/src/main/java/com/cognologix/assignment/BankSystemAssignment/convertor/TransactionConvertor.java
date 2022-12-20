package com.cognologix.assignment.BankSystemAssignment.convertor;

import com.cognologix.assignment.BankSystemAssignment.dto.TransactionDto;
import com.cognologix.assignment.BankSystemAssignment.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConvertor {
    @Autowired
    private ModelMapper modelMapper;

   //TransactionDto to Transaction Conversion
    public Transaction dtoToTransaction (TransactionDto transactionDto){
         Transaction transaction = this.modelMapper.map(transactionDto, Transaction.class);
         return transaction;

    }

    //Transaction to TransactionDto Conversion

    public TransactionDto transactionToDto (Transaction transaction){
        TransactionDto transactionDto = this.modelMapper.map(transaction, TransactionDto.class);
        return transactionDto;
    }
}
