package com.service.searchplay.repository.review;

import com.service.searchplay.model.review.SimpleReview;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaSimpleReviewRepository implements SimpleReviewRepository{

    @PersistenceContext
    private final EntityManager em;
    public JpaSimpleReviewRepository(EntityManager em) {
        this.em = em;
    }

    // 리뷰 작성
    @Override
    public Boolean write(SimpleReview review) {
        try {
            em.persist(review);
            System.out.println("[Repository] 작성 완료");
            return true;
        }catch(Exception e){
            System.out.println("[Repository] 작성 실패 : "+e);
            return false;
        }
    }

    // 리뷰 삭제
    @Override
    public int delete(int place_id, int review_id, String user_id) {
        return em.createQuery("delete from SimpleReview sr where sr.place_id=:place_id and sr.review_id=:review_id and sr.user_id=:user_id ")
                .setParameter("place_id",place_id)
                .setParameter("review_id",review_id)
                .setParameter("user_id",user_id)
                .executeUpdate();
    }

    // 현재 장소에 달린 리뷰 찾기
    @Override
    public List<SimpleReview> findByPlaceId(int place_id) {
        return em.createQuery("select sr from SimpleReview sr where sr.place_id=:place_id", SimpleReview.class)
                .setParameter("place_id",place_id)
                .getResultList();
    }

    // 리뷰 정정
    @Override
    public int update(SimpleReview review) {
        // UPDATE (테이블) SET (칼럼) = '변경할값' WHERE (조건)
        return em.createQuery("update SimpleReview sr set sr.content=:content, sr.recmnd=:recmnd, sr.review_date=current_timestamp where sr.place_id=:place_id and sr.user_id=:user_id")
                .setParameter("content",review.getContent())
                .setParameter("recmnd",review.getRecmnd())
                .setParameter("place_id",review.getPlace_id())
                .setParameter("user_id",review.getUser_id())
                .executeUpdate();
    }

    // 특정 사용자가 작성한 리뷰 찾기
    @Override
    public List<SimpleReview> findByUserId(String user_id) {
        return em.createQuery("select sr from SimpleReview sr where sr.user_id=:user_id", SimpleReview.class)
                .setParameter("user_id",user_id)
                .getResultList();
    }

    // 리뷰id 재정렬
    @Override
    public void rearrangement() { }
}
