package com.service.searchplay.service;

import com.service.searchplay.model.review.Review;
import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.repository.review.ReviewRepository;
import com.service.searchplay.repository.review.SimpleReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    // 빈으로 생성된 레포지토리 객체를 받기위한 생성자 -> 껍데기에 레포지토리 빈이 오토와이어드로 인해 들어감
    public ReviewService(ReviewRepository reviewRepository) {this.reviewRepository = reviewRepository;}


    public Review write(Review review) {
        // 이미 작성했으면 불가능
        System.out.println("[Service] 작성 시도..");
        Review rs = reviewRepository.write(review);
        return rs;
    }
    
    public boolean delete(int place_id, int review_id, String user_id){
        if(reviewRepository.delete(place_id, review_id, user_id) > 0) return true;
        else return false;
    }

    public List<Review> findByPlaceId(int place_id){
        return reviewRepository.findByPlaceId(place_id);
    }

    public boolean update(Review review){
        if(reviewRepository.update(review)>0) return true;
        else return false;
    }

    public List<Review> findByUserId(String user_id){
        return reviewRepository.findByUserId(user_id);
    }

}
