package service;

import dao.ReviewDAO_admin;
import dto.ReviewDTO_admin;
import filter.ReviewFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService_admin {

    @Autowired
    private ReviewDAO_admin reviewDAO;

    public ReviewDTO_admin getReviewById(int review_id) {
        return reviewDAO.getReviewById(review_id);
    }

    public List<ReviewDTO_admin> getAllReviews() {
        return reviewDAO.getAllReviews();
    }

    public void updateReview(ReviewDTO_admin review) {
        reviewDAO.updateReview(review);
    }

    public void deleteReview(int review_id) {
        reviewDAO.deleteReview(review_id);
    }

    public List<ReviewDTO_admin> getReviewsByFilterAndSort(ReviewFilterDTO_admin filter) {
        return reviewDAO.getReviewsByFilterAndSort(filter);
    }

    public int getReviewCountByFilter(ReviewFilterDTO_admin filter) {
        return reviewDAO.getReviewCountByFilter(filter);
    }
}
