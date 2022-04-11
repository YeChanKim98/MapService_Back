package com.service.searchplay.model.resv;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rsrv_seq;

    @NotNull
    private Integer rsrv_pla;

    @NotNull
    private String req_user;

    @NotNull
    private String req_tel;

    @NotNull
    private Integer rsrv_cnt;

    @NotNull
    private String rsrv_pms;

    @NotNull
    private LocalDateTime rsrv_time;

    @Generated(GenerationTime.INSERT)
    private Timestamp req_time;

}
