package com.service.searchplay.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Transactional
public class jpatest {
    @Id
    private int num;

}
