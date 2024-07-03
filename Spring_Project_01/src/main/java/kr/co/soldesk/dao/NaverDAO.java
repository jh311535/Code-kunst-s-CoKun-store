package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.NaverDTO;
import kr.co.soldesk.mapper.NaverMapper;


@Repository
public class NaverDAO {
	
	@Autowired
	private NaverMapper naverMapper;
	
	public List<NaverDTO> getNaverList(String category){
		List<NaverDTO> naverList = naverMapper.getNaverList(category);
		return naverList;
	}

}
