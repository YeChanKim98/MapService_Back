package com.service.searchplay;

import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.repository.review.SimpleReviewRepository;
import com.service.searchplay.repository.user.JpatestRepository;
import com.service.searchplay.service.SimpleReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@SpringBootTest
@Transactional
public class ReviewTest {

    @Autowired
    SimpleReviewRepository simpleReviewRepository;

    @Autowired
    SimpleReviewService simpleReviewService;

    @Test
    @DisplayName("Basic CRUD")
    @Commit // DB를 사용하는 테스트 메서드 위에 -> 테스트 메서드는 기본적으로 DB가 반영이 안됨
    public void simpleReviewCRUD(){
        System.out.println("한줄리뷰 삽입, 읽기, 수정, 삭제 테스트");
        SimpleReview sr = new SimpleReview();
        sr.setUser_id("Tester");
        sr.setPlace_id(123456);
        sr.setRecmnd("Good");
        sr.setContent("Nice");


        SimpleReview resWrite  = simpleReviewService.write(sr); // C : 삽입
        System.out.println("리뷰 작성 결과 : "+resWrite.getReview_id()+" / "+resWrite.getContent()+" / ");

        List<SimpleReview> resByUID = simpleReviewService.findByUserId("Tester"); // R1 : 계정으로 찾기
        System.out.println("Tester 계정이 작성한 리뷰");
        for(int i = 0 ; i < resByUID.size() ; i++){
            SimpleReview tmp = resByUID.get(i);
            System.out.println((i+1)+"번째 결과 : "+tmp.getReview_id()+" / "+tmp.getPlace_id()+" / "+tmp.getReview_date()+" / "+tmp.getContent()+" / "+tmp.getUser_id());
        }

        List<SimpleReview> resByPID = simpleReviewService.findByPlaceId(123456); // R2 : 장소로 찾기
        System.out.println("\n123456에 작성된 리뷰");
        for(int i = 0 ; i < resByPID.size() ; i++){
            SimpleReview tmp = resByPID.get(i);
            System.out.println((i+1)+"번째 결과 : "+tmp.getReview_id()+" / "+tmp.getPlace_id()+" / "+tmp.getReview_date()+" / "+tmp.getContent()+" / "+tmp.getUser_id());
        }

        sr.setRecmnd("Bad");
        sr.setContent("Sheeeeeet");
        boolean resUpdate = simpleReviewService.update(sr); // U : 리뷰 정정
        System.out.println("\n리뷰 정정 결과 : "+resUpdate);

//        int resDelete = simpleReviewService.delete(sr.getPlace_id(), sr.getUser_id()); // D : 리뷰 삭제
//        System.out.println("리뷰 삭제 결과 : "+resDelete);
    }


}
