package com.example.bank.repositories;

import com.example.bank.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUsersByUserName(String str);
}
