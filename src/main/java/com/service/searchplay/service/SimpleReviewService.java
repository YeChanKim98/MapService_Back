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
        return simpleReviewRepository.write(review);
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
