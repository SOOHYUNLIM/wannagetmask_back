package com.wannagetmask.repository;

import com.wannagetmask.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAllTest() {
        accountRepository.findAll().forEach(account -> log.info(account.toString()));
    }

    @Test
    public void insertTest() {
        Account account = Account.builder().id("test3").pw("ccc123").domain("naver").isMind(true).build();
        accountRepository.insert(account);
    }
}
