package com.service.searchplay;

import com.service.searchplay.repository.review.JpaSimpleReviewRepository;
import com.service.searchplay.repository.review.SimpleReviewRepository;
import com.service.searchplay.repository.user.JpatestRepository;
import com.service.searchplay.repository.user.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

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
