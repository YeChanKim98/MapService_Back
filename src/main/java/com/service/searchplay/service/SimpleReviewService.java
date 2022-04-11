package com.service.searchplay.service;

import com.service.searchplay.model.review.SimpleReview;
import com.service.searchplay.repository.review.SimpleReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SimpleReviewService {

    private final SimpleReviewRepository simpleReviewRepository;

    @Autowired
    public SimpleReviewService(SimpleReviewRepository simpleReviewRepository) {this.simpleReviewRepository = simpleReviewRepository;}


    public SimpleReview write(SimpleReview review) {
        // 이미 작성했으면 불가능
        System.out.println("서비스에서 작성을 시도중..");
        SimpleReview rs = simpleReviewRepository.write(review);
        System.out.println("서비스에서 작성을 완료함");
        return rs;
    }
    
    public boolean delete(int place_id, int review_id, String user_id){
        if(simpleReviewRepository.delete(place_id, review_id, user_id) > 0) return true;
        else return false;
    }

    public List<SimpleReview> findByPlaceId(int place_id){
        return simpleReviewRepository.findByPlaceId(place_id);
    }

    public boolean update(SimpleReview review){
        if(simpleReviewRepository.update(review)>0) return true;
        else return false;
    }

    public List<SimpleReview> findByUserId(String user_id){
        return simpleReviewRepository.findByUserId(user_id);
    }

}
