package com.service.searchplay.repository.user;

import com.service.searchplay.user.jpatest;
import java.util.List;

public interface JpatestRepository {
    List<jpatest> findAll();
}
