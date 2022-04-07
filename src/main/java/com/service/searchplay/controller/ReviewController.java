package com.service.searchplay.controller;

import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.service.SimpleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {

    private final SimpleReviewService simpleReviewService;

    @Autowired
    public ReviewController(SimpleReviewService simpleReviewService) {
        this.simpleReviewService = simpleReviewService;
    }

    // 작성
    @PostMapping("/review/simple/write")
    public String write(SimpleReview review){
        System.out.println("리뷰 작성 요청 : "+review);
        try{ simpleReviewService.write(review); }
        catch(Exception e) {
            System.out.println("[SimpleReviewController] Write Fail : "+e.getCause());
            return "redirect:/"; // 작성 실패
        }
        return "redirect:/"; // 작성 성공
    }

    // 삭제 : 현재 접속한 id와 리뷰넘버가 같으면 삭제 실행
    @PostMapping("/review/simple/delete/{place_id}/{review_id}")
    public String delete(@PathVariable int place_id, @PathVariable int review_id, HttpServletRequest request) {
        String user_id = request.getParameter("id");
        simpleReviewService.delete(place_id, review_id, user_id);
//        return res; // 성공 실패 여부
        return "redirect:/"; // 테스트용
    }
    
    // 수정
    @PostMapping("/review/simple/update/{place_id}/{review_id}")
    public boolean update(@PathVariable int place_id, @PathVariable int review_id, SimpleReview review, HttpServletRequest request){
        review.setPlace_id(place_id);
        review.setReview_id(review_id);
        review.setUser_id(request.getParameter("id"));
        boolean res = simpleReviewService.update(review);
        return res;
    }

    // 장소ID로 검색
    @RequestMapping("findByPlaceId")
    public String findByPlaceId(){
        return "";
    }

    // 유저ID로 검색
    @RequestMapping("findByUserId")
    public String findByUserId(){
        return "";
    }
}
