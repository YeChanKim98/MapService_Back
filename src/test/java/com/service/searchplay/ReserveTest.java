package com.service.searchplay;

import com.service.searchplay.model.resv.Reserve;
import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.repository.reserve.ReserveRepository;
import com.service.searchplay.service.ReserveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class ReserveTest {
    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    ReserveService reserveService;

    @Test
    @Commit
    public void ReserveT() {
        Reserve rsv = new Reserve();
        rsv.setReq_user("namhun");
        rsv.setRsrv_pla(1);
        rsv.setReq_tel("010-0000-0000");
        rsv.setRsrv_cnt(3);
        rsv.setRsrv_pms("wait");


        List<Reserve> res = reserveService.findByPlaceId(123);
        for(int i = 0; i<res.size();i++) {
            Reserve tmp = res.get(i);
            System.out.println((i + 1) + "번째 결과 : " + tmp.getRsrv_seq() + " / " + tmp.getReq_user() + " / " + tmp.getRsrv_pla() + " / " + tmp.getReq_tel() + " / " + tmp.getRsrv_time()
                  + " / " + tmp.getReq_time() + " / " + tmp.getRsrv_cnt() + " / " + tmp.getRsrv_pms());
        }
        boolean res1 = reserveService.pmsUpdate("submit", 123777);
        System.out.println(res1);


    }
}
