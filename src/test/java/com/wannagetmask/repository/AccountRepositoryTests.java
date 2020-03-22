package com.wannagetmask.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

//    @Test
//    public void findAllTest() {
//        accountRepository.findAll().forEach(account -> log.info(account.toString()));
//    }
//
//    @Test
//    public void insertTest() {
//    }
}
