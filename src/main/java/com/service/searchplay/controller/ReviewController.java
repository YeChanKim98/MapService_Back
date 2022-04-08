package com.service.searchplay.controller;

import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.service.SimpleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Controller
public class ReviewController {

    private final SimpleReviewService simpleReviewService;

    @Autowired
    public ReviewController(SimpleReviewService simpleReviewService) {
        this.simpleReviewService = simpleReviewService;
    }

    // 작성
    @CrossOrigin(origins = { "http://localhost:3000" }, allowedHeaders = "*", allowCredentials = "true") // 결과값을 넘기기 위함
    @PostMapping("/review/simple/write")
    public SimpleReview write(@RequestBody SimpleReview review){
        System.out.println("리뷰 작성 요청 : "+review.getContent()+" / "+review.getRecmnd()+" / "+review.getUser_id()+" / "+review.getPlace_id());
        try{ simpleReviewService.write(review); }
        catch(Exception e) {
            System.out.println("[SimpleReviewController] Write Fail : "+e.getCause());
            return null; // 작성 실패
        }
        return review; // 작성 성공
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
    @RequestMapping("/review/simple/findByPlaceId/{place_id}")
    public String findByPlaceId(@PathVariable int place_id){
        List<SimpleReview> res = simpleReviewService.findByPlaceId(place_id);
        // return res;
        return "redirect:/";
    }

    // 유저ID로 검색
    @RequestMapping("review/simple/findByUserId/{user_id}")
    public String findByUserId(@PathVariable String user_id){
        List<SimpleReview> res = simpleReviewService.findByUserId(user_id);
        // return res;
        return "redirect:/";
    }
}
