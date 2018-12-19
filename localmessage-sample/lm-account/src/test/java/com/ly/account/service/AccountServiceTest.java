package com.ly.account.service;

import com.ly.account.entity.Account;
import com.ly.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;
    @Test
    public void myTest(){
        Optional<Account> o = accountRepository.findById(11L);

        System.out.println(o);
    }
}