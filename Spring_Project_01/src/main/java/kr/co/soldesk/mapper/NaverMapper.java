package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.soldesk.beans.NaverDTO;

@Mapper
public interface NaverMapper {
    @Select("SELECT curation_id, "
    		+ "curation_category, "
    		+ "product_name, "
    		+ "product_pic, "
    		+ "product_url " 
    		+ "FROM curation "
    		+ "WHERE curation_category = #{category}")
    List<NaverDTO> getNaverList(@Param("category") String category);
}