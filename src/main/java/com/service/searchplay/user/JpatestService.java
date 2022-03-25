package com.service.searchplay.user;

import com.service.searchplay.repository.user.JpatestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class JpatestService {

    private final JpatestRepository testRepository;

    @Autowired
    public JpatestService(JpatestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // 모든 유저 출력
    public List<jpatest> findAllUser() {
        return testRepository.findAll();
    }


}

