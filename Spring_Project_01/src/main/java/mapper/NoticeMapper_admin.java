package mapper;

import dto.NoticeDTO_admin;
import filter.NoticeFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper_admin {

    @Select("SELECT * FROM notice WHERE notice_id = #{notice_id}")
    NoticeDTO_admin getNoticeById(int notice_id);

    @Select("SELECT notice_pic FROM notice WHERE notice_id = #{notice_id}")
    String getNoticePicById(int notice_id);

    @Select("SELECT * FROM notice")
    List<NoticeDTO_admin> getAllNotices();

    @Insert({
        "<script>",
        "INSERT INTO notice (notice_id, admin_idx, notice_title, notice_content, notice_date, notice_pic, notice_views)",
        "VALUES (notice_seq.NEXTVAL, #{admin_idx}, #{notice_title}, #{notice_content}, SYSDATE,",
        "<if test='notice_pic != null'>#{notice_pic}</if>",
        "<if test='notice_pic == null'>NULL</if>,",
        "#{notice_views})",
        "</script>"
    })
    void insertNotice(NoticeDTO_admin notice);


    @Update({
        "<script>",
        "UPDATE notice",
        "SET admin_idx = #{admin_idx},",
        "notice_title = #{notice_title},",
        "notice_content = #{notice_content},",
        "notice_date = #{notice_date},",
        "<if test='notice_pic != null'>notice_pic = #{notice_pic},</if>",
        "notice_views = #{notice_views}",
        "WHERE notice_id = #{notice_id}",
        "</script>"
    })
    void updateNotice(NoticeDTO_admin notice);

    @Update({
        "<script>",
        "UPDATE notice",
        "SET notice_pic = null ",
        "WHERE notice_id = #{notice_id}",
        "</script>"
    })
    void deleteNoticePic(NoticeDTO_admin notice);
    

    @Delete("DELETE FROM notice WHERE notice_id = #{notice_id}")
    void deleteNotice(int notice_id);

    @Update("UPDATE notice SET notice_views = notice_views + 1 WHERE notice_id = #{notice_id}")
    void incrementViews(int notice_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT n.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM notice n",
        "    WHERE 1=1",
        "    <if test='notice_id != null'>AND n.notice_id = #{notice_id}</if>",
        "    <if test='admin_idx != null'>AND n.admin_idx = #{admin_idx}</if>",
        "    <if test='notice_title != null'>AND n.notice_title LIKE '%' || #{notice_title} || '%'</if>",
        "    <if test='notice_content != null'>AND n.notice_content LIKE '%' || #{notice_content} || '%'</if>",
        "    <if test='startDate != null'>AND n.notice_date &gt;= #{startDate}</if>",
        "    <if test='endDate != null'>AND n.notice_date &lt;= #{endDate}</if>",
        "    <choose>",
        "      <when test='dateOrder != null'>ORDER BY n.notice_date ${dateOrder}</when>",
        "      <when test='noticeIdOrder != null'>ORDER BY n.notice_id ${noticeIdOrder}</when>",
        "      <when test='adminIdxOrder != null'>ORDER BY n.admin_idx ${adminIdxOrder}</when>",
        "      <when test='titleOrder != null'>ORDER BY n.notice_title ${titleOrder}</when>",
        "      <when test='contentOrder != null'>ORDER BY n.notice_content ${contentOrder}</when>",
        "      <when test='picOrder != null'>ORDER BY n.notice_pic ${picOrder}</when>",
        "      <when test='viewsOrder != null'>ORDER BY n.notice_views ${viewsOrder}</when>",
        "      <otherwise>ORDER BY n.notice_id DESC</otherwise>",
        "    </choose>",
        "  ) n",
        "  WHERE ROWNUM &lt;= #{offset} + #{pageSize}",
        ") WHERE rn &gt; #{offset}",
        "</script>"
    })
    List<NoticeDTO_admin> getNoticesByFilterAndSort(NoticeFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM notice n",
        "WHERE 1=1",
        "  <if test='notice_id != null'>AND n.notice_id = #{notice_id}</if>",
        "  <if test='admin_idx != null'>AND n.admin_idx = #{admin_idx}</if>",
        "  <if test='notice_title != null'>AND n.notice_title LIKE '%' || #{notice_title} || '%'</if>",
        "  <if test='notice_content != null'>AND n.notice_content LIKE '%' || #{notice_content} || '%'</if>",
        "  <if test='startDate != null'>AND n.notice_date &gt;= #{startDate}</if>",
        "  <if test='endDate != null'>AND n.notice_date &lt;= #{endDate}</if>",
        "</script>"
    })
    int getNoticeCountByFilter(NoticeFilterDTO_admin filter);
    
    
}
