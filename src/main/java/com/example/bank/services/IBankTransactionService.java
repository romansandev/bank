package com.example.bank.services;

import com.example.bank.entities.Account;
import com.example.bank.entities.BankTransaction;
import com.example.bank.exceptions.BrokeAccountException;

public interface IBankTransactionService {

    void deleteByAccount(Account account);

    void insert(BankTransaction bankTransaction) throws BrokeAccountException;

}
