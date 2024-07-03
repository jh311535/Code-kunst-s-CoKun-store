package mapper;

import dto.CurationDTO_admin;
import filter.CurationFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CurationMapper_admin {

    @Select("SELECT * FROM curation WHERE curation_id = #{curation_id}")
    CurationDTO_admin getCurationById(int curation_id);

    @Select("SELECT * FROM curation")
    List<CurationDTO_admin> getAllCurations();

    @Insert({
        "<script>",
        "INSERT INTO curation (curation_id, curation_category, product_name, product_pic, product_url)",
        "VALUES (curation_seq.NEXTVAL, #{curation_category}, #{product_name},",
        "<if test='product_pic != null'>#{product_pic}</if>",
        "<if test='product_pic == null'>NULL</if>,",
        "#{product_url})",
        "</script>"
    })
    void insertCuration(CurationDTO_admin curation);
    
    @Update({
        "<script>",
        "UPDATE curation ",
        "SET curation_category = #{curation_category}, ",
        "    product_name = #{product_name}, ",
        "	 <if test='product_pic != null'>product_pic = #{product_pic}, </if>",
        "    product_url = #{product_url} ",
        "WHERE curation_id = #{curation_id}",
        "</script>"
    })
    void updateCuration(CurationDTO_admin curation);

    @Delete("DELETE FROM curation WHERE curation_id = #{curation_id}")
    void deleteCuration(int curation_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT c.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM curation",
        "    WHERE 1=1",
        "    <if test='curation_id != null'>AND curation_id = #{curation_id}</if>",
        "    <if test='curation_category != null'>AND curation_category LIKE '%' || #{curation_category} || '%'</if>",
        "    <if test='product_name != null'>AND product_name LIKE '%' || #{product_name} || '%'</if>",
        "    <choose>",
        "      <when test='curationIdOrder != null'>ORDER BY curation_id ${curationIdOrder}</when>",
        "      <when test='curationCategoryOrder != null'>ORDER BY curation_category ${curationCategoryOrder}</when>",
        "      <when test='productNameOrder != null'>ORDER BY product_name ${productNameOrder}</when>",
        "      <when test='productUrlOrder != null'>ORDER BY product_url ${productUrlOrder}</when>",
        "      <otherwise>ORDER BY curation_id ASC</otherwise>",
        "    </choose>",
        "  ) c",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<CurationDTO_admin> getCurationsByFilterAndSort(CurationFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM curation",
        "WHERE 1=1",
        "  <if test='curation_id != null'>AND curation_id = #{curation_id}</if>",
        "  <if test='curation_category != null'>AND curation_category LIKE '%' || #{curation_category} || '%'</if>",
        "  <if test='product_name != null'>AND product_name LIKE '%' || #{product_name} || '%'</if>",
        "</script>"
    })
    int getCurationCountByFilter(CurationFilterDTO_admin filter);
}
