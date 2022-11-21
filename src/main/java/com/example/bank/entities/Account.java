package com.example.bank.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private Long money;
    private LocalDateTime creationTime;

    @ManyToOne(optional = false)
    private User user;

    public Account() {
    }

    public Account(Long money, LocalDateTime creationTime, User user) {
        this.money = money;
        this.creationTime = creationTime;
        this.user = user;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                ", creationTime=" + creationTime +
                ", user=" + user +
                '}';
    }
}
