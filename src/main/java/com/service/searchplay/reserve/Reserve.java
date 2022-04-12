package com.service.searchplay.reserve;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Transactional
@Getter
@Setter
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rsrv_seq; // 자동, PK
    @NotNull
    private int rsrv_pla; // not null
    @NotNull
    private String req_user; //not null
    @NotNull
    private String req_tel; // not null
    @NotNull
    private int rsrv_cnt; //not null
    @NotNull
    private Timestamp rsrv_time; // not null
    private Timestamp req_time; // Default Current_Timestamp
    @NotNull
    private String rsrv_pms; // wait 기본, submit 수락, refuse 거절, visite (예약후)방문

    // 시간 자동 생성
//    @PrePersist
//    public void createDate(){
//        this.date = LocalDateTime.now();
//    }



}
