package com.service.searchplay.service;

import com.service.searchplay.model.resv.Reserve;
import com.service.searchplay.repository.reserve.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReserveService {

    private final ReserveRepository reserveRepository;

    @Autowired
    public ReserveService(ReserveRepository reserveRepository){
        this.reserveRepository = reserveRepository;
    }

    public Reserve reserveRequest(Reserve reserve) {
        Reserve res = reserveRepository.reserve(reserve);
        return res;
    }

    public List<Reserve> findByUserId(String req_user){

        List<Reserve> rsvuser = reserveRepository.findByUserId(req_user);

        return rsvuser;
    }

    public List<Reserve> findByPlaceId(int rsrv_pla){

        List<Reserve> rsvpla = reserveRepository.findByPlaceId(rsrv_pla);

        return rsvpla;
    }

    public String pmsUpdate(String rsrv_pms, int rsrv_pla){

        String pms_update = reserveRepository.pmsUpdate(rsrv_pms, rsrv_pla);

        return pms_update;
    }
}
