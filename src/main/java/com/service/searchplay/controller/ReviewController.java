package com.service.searchplay.controller;

import com.service.searchplay.model.review.Review;
import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.service.ReviewService;
import com.service.searchplay.service.SimpleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController // 다른 서버랑 정보를 Json형태로 주고 받기위한 어노테이션
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:2221","http://localhost:8080",
        "http://127.0.0.1:3000","http://127.0.0.1:2221","http://127.0.0.1:8080"
        ,"http://172.16.1.120:3000","http://172.16.1.120:2221","http://172.16.1.120:8080",
        "http://1.209.148.228:8080","http://1.209.148.228:8080","http://1.209.148.228:8080"} , allowedHeaders = "*", allowCredentials = "true") // 결과값을 넘기기 위함
// CORS : 다른 호스트와 정보를 서로 주고받기위한 정책

@RequestMapping(value = "/review", method = {RequestMethod.GET, RequestMethod.POST})
public class ReviewController {

    private final SimpleReviewService simpleReviewService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(SimpleReviewService simpleReviewService, ReviewService reviewService) {
        this.reviewService = reviewService;
        this.simpleReviewService = simpleReviewService;
    }

    // 작성
    @PostMapping("/simple/write")
    public SimpleReview simpleWrite(@RequestBody SimpleReview review){
        System.out.println("\n[Controller] 한줄 리뷰 작성 요청 : [ "+review.getContent()+" / "+review.getRecmnd()+" / "+review.getUser_id()+" / "+review.getPlace_id()+" ]");
        SimpleReview res;
        try{
            res = simpleReviewService.write(review);
        }catch(Exception e) {
            System.out.println("[SimpleReviewController] Write Fail : "+e.getCause());
            return null; // 작성 실패
        }
        System.out.println("[Controller] 작성 성공 객체 반환");
        return res; // 작성 성공
    }

    // 삭제 : 현재 접속한 id와 리뷰넘버가 같으면 삭제 실행
    @PostMapping("/simple/delete/{place_id}/{review_id}")
    public String simpleDelete(@PathVariable int place_id, @PathVariable int review_id, @RequestBody String user_id){ // 세션 아이디 획득
        simpleReviewService.delete(place_id, review_id, user_id);
//        return res; // 성공 실패 여부
        return "redirect:/"; // 테스트용
    }
    
    // 수정
    @PostMapping("/simple/update/{place_id}/{review_id}")
    public boolean simpleUpdate(@PathVariable int place_id, @PathVariable int review_id, SimpleReview review, @RequestBody String user_id){
        review.setPlace_id(place_id);
        review.setReview_id(review_id);
        review.setUser_id(user_id);
        boolean res = simpleReviewService.update(review);
        return res;
    }

    // 장소ID로 검색
    @PostMapping("/simple/findByPlaceId/{place_id}")
    public List<SimpleReview> simpleFindByPlaceId(@PathVariable int place_id){
        List<SimpleReview> placeReviews = simpleReviewService.findByPlaceId(place_id);
        return placeReviews;
    }

    // 유저ID로 검색
    @PostMapping( "/simple/findByUserId/{user_id}")
    public List<SimpleReview> simpleFindByUserId(@PathVariable String user_id){
        List<SimpleReview> userReviews = simpleReviewService.findByUserId(user_id);
        return userReviews;
    }

    /////////////////////////////////////////////////////[상세리뷰]/////////////////////////////////////////////////////////

    // 작성
    @PostMapping("/write")
    public Review write(@RequestBody Review review){
        System.out.println("\n[Controller] 리뷰 작성 요청 : [ "+review.getContent()+" / "+review.getContent()+" / "+review.getUser_id()+" / "+review.getPlace_id()+" ]");
        Review res;
        try{
            res = reviewService.write(review);
        }catch(Exception e) {
            System.out.println("[SimpleReviewController] Write Fail : "+e.getCause());
            return null; // 작성 실패
        }
        System.out.println("[Controller] 작성 성공 객체 반환");
        return res; // 작성 성공
    }

    // 삭제 : 현재 접속한 id와 리뷰넘버가 같으면 삭제 실행
    @PostMapping("/delete/{place_id}/{review_id}")
    public String delete(@PathVariable int place_id, @PathVariable int review_id, @RequestBody String user_id){ // 세션 아이디 획득
        reviewService.delete(place_id, review_id, user_id);
//        return res; // 성공 실패 여부
        return "redirect:/"; // 테스트용
    }

    // 수정
    @PostMapping("/update/{place_id}/{review_id}")
    public boolean update(@PathVariable int place_id, @PathVariable int review_id, Review review, @RequestBody String user_id){
        review.setPlace_id(place_id);
        review.setReview_id(review_id);
        review.setUser_id(user_id);
        boolean res = reviewService.update(review);
        return res;
    }

    // 장소ID로 검색
    @PostMapping("/findByPlaceId/{place_id}")
    public List<Review> findByPlaceId(@PathVariable int place_id){
        List<Review> placeReviews = reviewService.findByPlaceId(place_id);
        return placeReviews;
    }

    // 유저ID로 검색
    @PostMapping( "/findByUserId/{user_id}")
    public List<Review> findByUserId(@PathVariable String user_id){
        List<Review> userReviews = reviewService.findByUserId(user_id);
        return userReviews;
    }
    
}
