

-- 심플리뷰 생성문
create table simple_review(
    review_id int primary key not null auto_increment,
    place_id int not null,
    user_id varchar(25) not null,
    recmnd varchar(5) not null,
    content varchar(55) not null,
    review_date timestamp default current_timestamp
);

-- 심플리뷰 테스트 데이터
insert into simple_review(place_id, user_id, recmnd, content) values(123456, '1234', 'good','YEA');