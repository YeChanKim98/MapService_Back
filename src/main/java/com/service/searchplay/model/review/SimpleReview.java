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
    private int place_id;

    @NotNull
    private String user_id;

    @NotNull
    private String recmnd;

    @NotNull
    private String content;

    // Default current_timestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp review_date;

}