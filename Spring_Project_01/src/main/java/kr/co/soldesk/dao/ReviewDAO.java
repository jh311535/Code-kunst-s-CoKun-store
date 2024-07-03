package kr.co.soldesk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.mapper.ReviewMapper;

@Repository
public class ReviewDAO {
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Autowired
	private UserBean loginUserBean;
	@Autowired
	private BookDTO reviewBookBean;
	
	public void addReview(ReviewBean reviewBean) {

		reviewMapper.addReview(reviewBean);
	}
	
	//���亸�̰� �ϴ¸޼ҵ�
    public List<ReviewBean> getReview(int book_id, RowBounds rowBounds) {
        return reviewMapper.getReview(book_id, rowBounds);
    }
    
    //���������� ��� �޼ҵ�
    public int countReviewsForBook(int bookId) {
        return reviewMapper.countReviewsForBook(bookId);
    }
    
    public void deleteReview(int review_id) {
    	reviewMapper.deleteReview(review_id);
    }
    
    public void modifyReview(ReviewBean reviewBean) {
    	reviewMapper.modifyReview(reviewBean);
    }
    
    public ReviewBean getReviewContent(int review_id) {
    	return reviewMapper.getReviewContent(review_id);
    }
    
    public List<ReviewBean> getFollowReview(int user_idx, RowBounds rowBounds){
    	return reviewMapper.getFollowReview(user_idx, rowBounds);
    }
    
    public List<ReviewBean> getAllReview(RowBounds rowBounds){
    	return reviewMapper.getAllReview(rowBounds);
    }
}
