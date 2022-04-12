package com.service.searchplay.repository.reserve;

import com.service.searchplay.model.resv.Reserve;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaReserveRepository implements ReserveRepository {

    @PersistenceContext
    private final EntityManager em;
    public JpaReserveRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Reserve reserve(Reserve reserve){
        em.persist(reserve);
        return reserve;
    }

    @Override
    public List<Reserve> findByPlaceId(int rsrv_pla) {
        return em.createQuery("select rsv from reserve rsv where rsv.rsrv_pla=:rsrv_pla", Reserve.class)
                .setParameter("rsrv_pla",rsrv_pla)
                .getResultList();
    }

    @Override
    public List<Reserve> findByUserId(String req_user) {
        return em.createQuery("select rsv from reserve rsv where rsv.req_user=:req_user", Reserve.class)
                .setParameter("req_user",req_user)
                .getResultList();
    }

    @Override
    public String pmsUpdate(Reserve reserve) {


}
