package com.example.bank.services;

import com.example.bank.entities.Account;
import com.example.bank.entities.BankTransaction;
import com.example.bank.entities.User;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankTransactionService bankTransactionService;


    @Override
    public void deleteByUser(User user) {

        List<Account> accountList = accountRepository.findAccountsByUser(user);
        accountList.forEach(account -> {
            bankTransactionService.deleteByAccount(account);
            accountRepository.deleteById(account.getId());
        });
    }
}
