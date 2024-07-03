package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.soldesk.beans.SidebarDTO;


public interface SidebarMapper {
    @Select("SELECT sidebar_id, sidebar_name FROM sidebar")
    List<SidebarDTO> getSidebarList();
}
