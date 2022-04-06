package com.service.searchplay.userTest;

import com.service.searchplay.repository.user.JpatestRepository;
import com.service.searchplay.model.user.jpatest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class utest {

    @Autowired
    JpatestRepository jpatestRepository;

    @Test
    public void getAll(){
        System.out.println("MySQL용 테스트 케이스");
        List<jpatest> get = jpatestRepository.findAll();
        for(int i = 0 ; i < get.size() ; i++) {
            System.out.println((i+1) + ") 받은 자료 " + get.get(i).getNum());
        }

    }



}
