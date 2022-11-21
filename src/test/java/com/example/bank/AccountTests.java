package com.example.bank;

import com.example.bank.configuration.H2JpaConfig;
import com.example.bank.entities.Account;
import com.example.bank.entities.User;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class, H2JpaConfig.class})
@Sql(scripts = {"classpath:userdata.sql"})
public class AccountTests {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @After
    public void afterTests(){
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void insertAccount(){

        User user = userRepository.findById(1L).get();
        Account account = new Account((long) 2987.26, LocalDateTime.now(), user);

        assertThat(accountRepository.findAll()).isEmpty();

        accountRepository.save(account);

        assertThat(accountRepository.findAll()).isNotEmpty();

    }

}
