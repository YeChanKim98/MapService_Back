package com.service.searchplay.configuration;

import com.service.searchplay.repository.review.JpaSimpleReviewRepository;
import com.service.searchplay.repository.review.SimpleReviewRepository;
import com.service.searchplay.repository.user.JpatestRepository;
import com.service.searchplay.repository.user.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration // 설정파일에는 해당 어노테이션이 있어야한다
public class SpringConfig {

    @PersistenceContext
    private EntityManager em; // JAP를 다루는 객체인데, 자동으로 만들어짐

    @Autowired
    public SpringConfig(EntityManager em){this.em = em;}

    // 연결 테스트용
    @Bean
    public JpatestRepository jpatestRepository(){
        return new testRepository(em);
    }

    // 심플리뷰 빈
    @Bean
    public SimpleReviewRepository simpleReviewRepository(){
        return new JpaSimpleReviewRepository(em);
    }

}
