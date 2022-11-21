package com.example.bank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue
    private Long id;

    private Long money;
    private LocalDateTime transactionTime;
    private boolean isWithdrawal;

    @ManyToOne
    private Account account;

    public BankTransaction() {
    }

    public BankTransaction(Long money, LocalDateTime transactionTime, boolean isWithdrawal, Account account) {
        this.money = money;
        this.transactionTime = transactionTime;
        this.isWithdrawal = isWithdrawal;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public boolean isWithdrawal() {
        return isWithdrawal;
    }

    public void setWithdrawal(boolean withdrawal) {
        isWithdrawal = withdrawal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "id=" + id +
                ", money=" + money +
                ", transactionTime=" + transactionTime +
                ", isWithdrawal=" + isWithdrawal +
                ", account=" + account +
                '}';
    }
}
