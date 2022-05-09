create table reserve(
    rsrv_seq int primary key not null auto_increment,
    rsrv_pla int not null,
    req_user varchar(25) not null,
    req_tel varchar(15) not null,
    rsrv_cnt int not null,
    rsrv_time datetime not null,
    req_time timestamp default current_timestamp,
    rsrv_pms varchar(6) not null default 'wait'
);