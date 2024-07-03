package kr.co.soldesk.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.NaverDTO;
import kr.co.soldesk.dao.NaverDAO;


@Service
public class NaverService {
	
	@Autowired
	private NaverDAO naverDAO;
	
	public List<NaverDTO> getNaverList(String category){
		List<NaverDTO> naverList = naverDAO.getNaverList(category);
		return naverList;
	}
	
    public List<NaverDTO> getRandomCuration(List<NaverDTO> list, int count) {
        Collections.shuffle(list);
        return list.subList(0, Math.min(count, list.size()));
    }
}
