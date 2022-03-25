package com.service.searchplay.userTest;

import com.service.searchplay.repository.user.JpatestRepository;
import com.service.searchplay.user.jpatest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;

@SpringBootTest
class utest {

    @Autowired
    JpatestRepository jpatestRepository;

    @Test
    public void getAll(){
        System.out.println("MySQL용 테스트 케이스");
        List<jpatest> get = jpatestRepository.findAll();
        System.out.println("받은 자료"+get.get(0));

    }



}
