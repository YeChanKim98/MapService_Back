package com.service.searchplay.controller;

import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.service.SimpleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {

    private final SimpleReviewService simpleReviewService;

    @Autowired
    public ReviewController(SimpleReviewService simpleReviewService) {
        this.simpleReviewService = simpleReviewService;
    }

    // 작성
    @PostMapping("/review/simple/review")
    public String write(SimpleReview review){
        if(review.chkNull().length > 0){
            return "";
        }
        simpleReviewService.write(review);
        return "";
    }

    // 삭제
    @RequestMapping("")
    public String delete(){
        return "";
    }
    
    // 수정
    @RequestMapping("")
    public String update(){
        return "";
    }

    // 장소ID로 검색
    @RequestMapping("")
    public String findByPlaceId(){
        return "";
    }

    // 유저ID로 검색
    @RequestMapping("")
    public String findByUserId(){
        return "";
    }
}
