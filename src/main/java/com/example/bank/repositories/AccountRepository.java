package com.example.bank.repositories;

import com.example.bank.entities.Account;
import com.example.bank.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountsByUser(User user);
}
