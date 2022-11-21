package com.example.bank.repositories;

import com.example.bank.entities.Account;
import com.example.bank.entities.BankTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {
    List<BankTransaction> findBankTransactionsByAccount(Account account);
}
