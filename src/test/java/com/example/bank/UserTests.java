package com.example.bank;


import com.example.bank.configuration.H2JpaConfig;
import com.example.bank.entities.User;
import com.example.bank.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.After;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankApplication.class, H2JpaConfig.class})
@Sql(scripts = {"classpath:userdata.sql"})
class UserTests {

    @Autowired
    UserRepository userRepo;

    @After
    public void afterTests(){
        userRepo.deleteAll();
    }

    @Test
    void InsertUsers() {
        User user = new User("Jean-Christophe", "abc");
        userRepo.save(user);
        assertThat(userRepo.findUsersByUserName("Jean-Christophe").toString()).contains(user.toString());
    }

    @Test
    void DeleteUsers(){
        Optional<User> optuser = userRepo.findUsersByUserName("Roberto").stream().findFirst();

        assertThat(optuser.isPresent()).isTrue();
        User user = optuser.get();

        assertThat(userRepo.findUsersByUserName("Roberto").toString()).contains(user.toString());
        userRepo.deleteById(1L);
        assertThat(userRepo.findUsersByUserName("Roberto")).isEmpty();
    }

}
