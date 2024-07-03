package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.SidebarDTO;
import kr.co.soldesk.mapper.SidebarMapper;

@Repository
public class SideBarDAO {
	
	@Autowired
	private SidebarMapper sidebarMapper;
	
	public List<SidebarDTO> getSidebarList(){
		List<SidebarDTO> sidebarList=sidebarMapper.getSidebarList();
		return sidebarList;
	}

}
