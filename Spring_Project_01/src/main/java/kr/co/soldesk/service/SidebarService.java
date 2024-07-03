package kr.co.soldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.SidebarDTO;
import kr.co.soldesk.dao.SideBarDAO;


@Service
public class SidebarService {
  
	@Autowired
	private SideBarDAO sidebarDao;
	
	public List<SidebarDTO> getSidebarList(){
		List<SidebarDTO> sidebarList=sidebarDao.getSidebarList();
		return sidebarList;
	}
}
