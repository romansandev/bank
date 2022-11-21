package com.example.bank.services;

import com.example.bank.entities.Account;
import com.example.bank.entities.BankTransaction;
import com.example.bank.exceptions.BrokeAccountException;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BankTransactionService implements IBankTransactionService{

    @Autowired
    BankTransactionRepository bankTransactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void deleteByAccount(Account account) {
        List<BankTransaction> bankTransactionList = bankTransactionRepository.findBankTransactionsByAccount(account);
        bankTransactionList.forEach(bankTransaction -> bankTransactionRepository.deleteById(bankTransaction.getId()));
    }

    @Override
    @Transactional
    public void insert(BankTransaction bankTransaction) throws BrokeAccountException{
        Account account = bankTransaction.getAccount();
        Long accountFunds = account.getMoney();
        if (bankTransaction.isWithdrawal()){
            if (bankTransaction.getMoney() > accountFunds){
                throw new BrokeAccountException("trying to withdraw " + bankTransaction.getMoney() + " from account with lesser funds (" + accountFunds + ")");
            } else {
                account.setMoney(accountFunds - bankTransaction.getMoney());
                accountRepository.save(account);
            }
        } else {
            account.setMoney(accountFunds + bankTransaction.getMoney());
            accountRepository.save(account);
        }
        bankTransactionRepository.save(bankTransaction);
    }
}
