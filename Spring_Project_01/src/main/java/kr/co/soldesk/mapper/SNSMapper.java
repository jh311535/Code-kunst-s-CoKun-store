package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.soldesk.beans.ProfileBean;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.SNSBean;

@Mapper
public interface SNSMapper {
    @Select({
    	"SELECT",
    	"  (SELECT user_nickname FROM user_info WHERE user_idx = #{user_idx}) AS userNickname,",
    	"  (SELECT introduce FROM customer WHERE user_idx = #{user_idx}) AS introduce,",
    	"  (SELECT user_pic FROM customer WHERE user_idx = #{user_idx}) AS user_pic,",
    	"  (SELECT COUNT(*) FROM follow WHERE following_id = #{user_idx}) AS followingCount,",
    	"  (SELECT COUNT(*) FROM follow WHERE follower_id = #{user_idx}) AS followersCount,",
    	"  (SELECT COUNT(*) FROM review WHERE user_idx = #{user_idx}) AS reviewsCount",
    	"FROM dual"
    })
    ProfileBean getSNSprofile(@Param("user_idx") int user_idx);
    
    @Insert({
        "INSERT INTO follow (follow_id, follower_id, following_id) VALUES (follow_seq.NEXTVAL, #{follower_id}, #{following_id})"
    })
    void addFollow(SNSBean followSNSBean);
    
    @Delete({
    	"delete from follow where follower_id=#{follower_id} AND following_id=#{following_id}"
    })
    void deleteFollow(@Param("follower_id") int follower_id, @Param("following_id") int following_id);
    
    @Select({
    	"SELECT follow_id FROM follow where follower_id=#{follower_id} AND following_id=#{following_id}"
    })
    Integer checkFollow(@Param("follower_id")int follower_id, @Param("following_id")int following_id);
    
    @Select({
    	"SELECT r.review_id, r.user_idx,r.book_id ,r.rating, r.review_content, r.review_date, "
    	+ "b.book_name AS bookName ,b.book_pic AS book_pic "
    			+"FROM review r " +
                "JOIN book b ON r.book_id = b.book_id " +
                "WHERE r.user_idx=#{user_idx}"+
            "ORDER BY r.review_date DESC"
    })
    List<ReviewBean> getUserReview (@Param("user_idx") int user_idx);
    
    @Update({
    	"UPDATE customer SET introduce=#{introduce}, user_pic=#{user_pic, jdbcType=VARCHAR} WHERE user_idx=#{user_idx}"
    })
    void updateProfile(ProfileBean profileBean);
    
    @Update(
            "UPDATE user_info SET user_nickname=#{userNickname} WHERE user_idx=#{user_idx}"
    		)
    void updateNickname(ProfileBean profileBean);
    
    @Select("SELECT c.user_idx AS user_idx,u.user_name AS user_Name, u.user_nickname AS userNickname, c.user_pic AS user_pic " +
            "FROM follow f " +
            "JOIN customer c ON f.following_id = c.user_idx " +
            "JOIN user_info u ON f.following_id = u.user_idx " +
            "WHERE f.follower_id =  #{follower_id}")
    List<ProfileBean> getFollowings(int follower_id);
    
    @Select("SELECT c.user_idx AS user_idx,u.user_name AS user_Name, u.user_nickname AS userNickname, c.user_pic AS user_pic " +
            "FROM follow f " +
            "JOIN customer c ON f.follower_id = c.user_idx " +
            "JOIN user_info u ON f.follower_id = u.user_idx " +
            "WHERE f.following_id =  #{follower_id}")
    List<ProfileBean> getFollowers(int follower_id);
    
}