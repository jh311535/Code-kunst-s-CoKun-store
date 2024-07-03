package kr.co.soldesk.service;

import kr.co.soldesk.beans.BoardBean;
import kr.co.soldesk.dao.BoardDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Component Scan이 들어있음
public class MainService {

    @Autowired
    private BoardDao boardDao;

    public List<BoardBean> getMainList(int board_info_idx){
        RowBounds rowBounds = new RowBounds(0, 5);
        return boardDao.getBoardList(board_info_idx, rowBounds); // 해당 게시판에서 5개씩 끊어서 읽기
    }
}
