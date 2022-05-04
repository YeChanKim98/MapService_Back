

-- 심플리뷰 생성문
create table simple_review(
    review_id int primary key auto_increment,
    place_id int not null,
    user_id varchar(25) not null,
    recmnd varchar(5) not null,
    content varchar(55) not null,
    review_date timestamp default current_timestamp
);

create table reserve(
    rsrv_seq int primary key auto_increment,
    rsrv_pla int not null,
    req_user varchar(25) not null,
    req_tel varchar(15) not null,
    rsrv_cnt int not null,
    rsrv_time datetime not null,
    req_time timestamp default current_timestamp,
    rsrv_pms varchar(6) not null default 'wait'
    );


create table users(
    seq int primary key auto_increment,
    id varchar not null,
    password varchar not null,
    name varchar not null,
    nickname varchar not null,
    email varchar not null,
    phone varchar not null,
    age varchar not null
);

create table review(
    review_id int primary key auto_increment,
    user_id varchar(25) not null,
    place_id varchar(25) not null,
    score int not null,
    content varchar(255) not null,
    review_date timestamp default current_timestamp
);

create table
-- 심플리뷰 테스트 데이터
insert into simple_review(place_id, user_id, recmnd, content) values(123456, '1234', 'good','YEA');