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


    public SimpleReview write(SimpleReview review){
        String[] chkNull = review.chkNull(); // controller로 이동
        if(chkNull.length > 0){
            return null;
        }
        return simpleReviewRepository.write(review);
    }
    
    public int delete(int place_id,String user_id){
        return simpleReviewRepository.delete(place_id,user_id);
    }

    public List<SimpleReview> findByPlaceId(int place_id){
        return simpleReviewRepository.findByPlaceId(place_id);
    }

    public int update(SimpleReview review){
        return simpleReviewRepository.update(review);
    }

    public List<SimpleReview> findByUserId(String user_id){
        return simpleReviewRepository.findByUserId(user_id);
    }

}
