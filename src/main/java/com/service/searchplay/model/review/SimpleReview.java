package com.service.searchplay.model.review;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Transactional
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id; // Autoincrement, Primary Key

    @NotNull
    private Integer place_id;

    @NotNull
    private String user_id;

    @NotNull
    private String recmnd;

    @NotNull
    private String content;

    // Default current_timestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp review_date;


    public String[] chkNull(){
        String[] haveNull = new String[4];
        int index = 0;
        if(place_id==null){
            haveNull[index] = "place_id";
            index++;
        }else if(user_id == "") {
            haveNull[index] = "user_id";
            index++;
        }else if(recmnd == ""){
            haveNull[index] = "recmnd";
            index++;
        }else if(content == ""){
            haveNull[index] = "content";
            index++;
        }
        return haveNull;
    }
}