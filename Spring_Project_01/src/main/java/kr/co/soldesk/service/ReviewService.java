package kr.co.soldesk.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.dao.ReviewDAO;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO reviewDao;
	
	public void addReview(ReviewBean reviewBean) {
		reviewDao.addReview(reviewBean);
	}
	
    public List<ReviewBean> getReviewsForBook(int book_id, int pageNumber) {
        int pageSize = 3;
        int offset = (pageNumber - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);

        return reviewDao.getReview(book_id, rowBounds);
    }
    
    public List<ReviewBean> getFollowReview(int user_idx, int pageNumber){
        int pageSize = 3;
        int offset = (pageNumber - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
    	return reviewDao.getFollowReview(user_idx, rowBounds);
    }
    
    public List<ReviewBean> getAllReview(int pageNumber){
    	int pageSize = 3;
        int offset = (pageNumber - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        
    	return reviewDao.getAllReview(rowBounds);
    }
    
    public int countReviewsForBook(int book_id) {
        return reviewDao.countReviewsForBook(book_id);
    }
    
    public void deleteReview(int review_id) {
    	reviewDao.deleteReview(review_id);
    }
    
    public void modifyReview(ReviewBean reviewBean) {
    	reviewDao.modifyReview(reviewBean);
    }
    
    public ReviewBean getReviewContent(int review_id) {
    	return reviewDao.getReviewContent(review_id);
    }


}
