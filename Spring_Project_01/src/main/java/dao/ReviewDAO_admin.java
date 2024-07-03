package dao;

import dto.ReviewDTO_admin;
import filter.ReviewFilterDTO_admin;
import mapper.ReviewMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAO_admin {

    @Autowired
    private ReviewMapper_admin reviewMapper;

    public ReviewDTO_admin getReviewById(int review_id) {
        return reviewMapper.getReviewById(review_id);
    }

    public List<ReviewDTO_admin> getAllReviews() {
        return reviewMapper.getAllReviews();
    }

    public void updateReview(ReviewDTO_admin review) {
        reviewMapper.updateReview(review);
    }

    public void deleteReview(int review_id) {
        reviewMapper.deleteReview(review_id);
    }

    public List<ReviewDTO_admin> getReviewsByFilterAndSort(ReviewFilterDTO_admin filter) {
        return reviewMapper.getReviewsByFilterAndSort(filter);
    }

    public int getReviewCountByFilter(ReviewFilterDTO_admin filter) {
        return reviewMapper.getReviewCountByFilter(filter);
    }
}
