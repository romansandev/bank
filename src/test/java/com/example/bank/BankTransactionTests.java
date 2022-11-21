package com.example.bank;

import com.example.bank.configuration.H2JpaConfig;
import com.example.bank.entities.Account;
import com.example.bank.entities.BankTransaction;
import com.example.bank.entities.User;
import com.example.bank.exceptions.BrokeAccountException;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.BankTransactionRepository;
import com.example.bank.repositories.UserRepository;
import com.example.bank.services.BankTransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class, H2JpaConfig.class})
@Sql(scripts = {"classpath:userdata.sql"})
public class BankTransactionTests {

    @Autowired
    BankTransactionRepository bankTransactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankTransactionService bankTransactionService;

    @After
    public void afterTests(){
        bankTransactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }



    @Before
    public void beforeTests(){
        User user = userRepository.findById(1L).get();
        Account account = new Account((long) 2987.26, LocalDateTime.now(), user);
        accountRepository.save(account);
    }

    @Test
    public void insertBankWithdrawalCorrect() throws BrokeAccountException {
        User user = userRepository.findById(1L).get();
        Account account = accountRepository.findAccountsByUser(user).get(0);
        Long funds = account.getMoney();
        BankTransaction bankTransaction = new BankTransaction((long) 285.02, LocalDateTime.now(), true, account);

        assertThat(bankTransactionRepository.findAll()).isEmpty();

        bankTransactionService.insert(bankTransaction);

        account = accountRepository.findAccountsByUser(user).get(0);
        assertThat(bankTransactionRepository.findAll()).isNotEmpty();
        assertThat(funds > account.getMoney()).isTrue();

    }

    @Test
    public void insertBankWithdrawalException() throws BrokeAccountException {
        User user = userRepository.findById(1L).get();
        Account account = accountRepository.findAccountsByUser(user).get(0);
        BankTransaction bankTransaction = new BankTransaction((long) 20285.02, LocalDateTime.now(), true, account);

        assertThat(bankTransactionRepository.findAll()).isEmpty();

        assertThatExceptionOfType(BrokeAccountException.class).isThrownBy(() ->
                bankTransactionService.insert(bankTransaction)
        );

    }

    @Test
    public void insertBankDeposit() throws BrokeAccountException {
        User user = userRepository.findById(1L).get();
        Account account = accountRepository.findAccountsByUser(user).get(0);
        Long funds = account.getMoney();
        BankTransaction bankTransaction = new BankTransaction((long) 285.02, LocalDateTime.now(), false, account);

        assertThat(bankTransactionRepository.findAll()).isEmpty();

        bankTransactionService.insert(bankTransaction);

        account = accountRepository.findAccountsByUser(user).get(0);
        assertThat(bankTransactionRepository.findAll()).isNotEmpty();
        assertThat(funds < account.getMoney()).isTrue();
    }

    @Test
    public void HistoricTransactionsByAccount() throws BrokeAccountException {

        User user = userRepository.findById(1L).get();
        Account account = accountRepository.findAccountsByUser(user).get(0);
        Long funds = account.getMoney();
        BankTransaction bankTransaction1 = new BankTransaction((long) 285.02, LocalDateTime.now(), true, account);
        BankTransaction bankTransaction2 = new BankTransaction((long) 350, LocalDateTime.now(), false, account);
        BankTransaction bankTransaction3 = new BankTransaction((long) 500, LocalDateTime.now(), true, account);

        assertThat(bankTransactionRepository.findBankTransactionsByAccount(account)).isEmpty();

        bankTransactionService.insert(bankTransaction1);
        bankTransactionService.insert(bankTransaction2);
        bankTransactionService.insert(bankTransaction3);

        assertThat(bankTransactionRepository.findBankTransactionsByAccount(account)).hasSize(3);

    }


}
