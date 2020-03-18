package com.wannagetmask.repository;

import com.wannagetmask.domain.Target;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TargetRepositoryTests {

    @Autowired
    TargetRepository tRepo;

    @Test
    public void findAllTest() {
        System.out.println("================");
        tRepo.insert(Target.builder().url("www.naver.com").domain("네이버").listTag("tag1").soldoutTag("tag2").build());
        System.out.println("=============");
        tRepo.findAll().forEach(target -> System.out.println(target));

    }
}
