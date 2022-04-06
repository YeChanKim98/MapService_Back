package com.service.searchplay.repository.user;

import com.service.searchplay.model.user.jpatest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


// DAO
public class testRepository implements JpatestRepository {

    @PersistenceContext
    private final EntityManager em;
    public testRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<jpatest> findAll() {
        return em.createQuery("select j from jpatest j", jpatest.class).getResultList();
    }
}
