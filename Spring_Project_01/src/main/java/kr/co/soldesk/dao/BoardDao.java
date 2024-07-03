/*
이 코드는 Spring Framework를 사용하여 게시판 관련 데이터 접근 기능을 제공하는 DAO(Data Access Object) 클래스입니다. BoardMapper를 주입받아 MyBatis를 통해 데이터베이스와 상호작용합니다. 각 메서드는 특정 기능을 수행하며, 데이터베이스에서 필요한 정보를 가져오거나 수정, 삭제 등의 작업을 처리합니다.
 */
package kr.co.soldesk.dao;

import kr.co.soldesk.beans.BoardBean;
import kr.co.soldesk.mapper.BoardMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import javax.swing.text.AbstractDocument;
import java.util.List;

// @Repository: 데이터 접근을 위한 DAO 클래스임을 나타내며, 스프링이 이 클래스를 빈으로 관리하도록 함
@Repository
public class BoardDao {

    @Autowired
    private BoardMapper boardMapper;

    // 게시글 정보를 추가하는 메서드
    public void addBoardInfo(BoardBean writeBoardBean) {

        // 게시글을 추가함
        boardMapper.addBoardInfo(writeBoardBean);
    }

   

    // 게시판 이름을 가져오는 메서드
    public String getBoardInfoName(int board_info_idx) {
        return boardMapper.getBoardInfoName(board_info_idx);
    }

    // 특정 게시판의 게시글 목록을 가져오는 메서드
    public List<BoardBean> getBoardList(int board_info_idx, RowBounds rowBounds) {
        return boardMapper.getBoardList(board_info_idx, rowBounds);
    }

    // 특정 게시글의 상세 정보를 가져오는 메서드
    public BoardBean getBoardInfo(int board_id){
        return boardMapper.getBoardInfo(board_id);
    }

    // 게시글 정보를 수정하는 메서드
    public void modifyBoardInfo(BoardBean modifyBoardBean) {
        boardMapper.modifyBoardInfo(modifyBoardBean);
    }

    // 특정 게시글을 삭제하는 메서드
    public void deleteBoardInfo(int board_id) {
        boardMapper.deleteBoardInfo(board_id);
    }

    // 게시판의 게시글 수를 가져오는 메서드
    public int getBoardCnt(int board_info_idx) {
        return boardMapper.getBoardCnt(board_info_idx);
    }

    // 조회수 증가
    public void updateHits(int board_id) {
       boardMapper.updateHits(board_id);
    }

    
}
