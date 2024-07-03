package kr.co.soldesk.mapper;

import kr.co.soldesk.beans.BoardBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BoardMapper {

	// @SelectKey: board_id 값을 생성하기 위해 사용됩니다. content_seq.nextval을 실행하여 board_id 값을
	// 가져옴
	@SelectKey(statement = "select board_seq.nextval from dual", keyProperty = "board_id", before = true, resultType = int.class)

	// 글 작성
	@Insert("insert into board(BOARD_ID, USER_IDX, BOARD_CONTENT, BOARD_DATE, BOARD_TITLE, BOARD_PIC, BOARD_INFO_IDX) values (#{board_id}, #{user_idx}, #{board_content}, sysdate, #{board_title}, #{board_pic, jdbcType=VARCHAR}, #{board_info_idx})")
	void addBoardInfo(BoardBean writeBoardBean);

	// 게시판 이름 가져오기
	// @Select: 주어진 board_info_idx에 대한 게시판 이름을 가져오는 메서드
	@Select("select board_info_name " + "from board_info_table " + "where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);

	// 글 목록
	@Select("select a1.board_id, a1.board_title, a2.user_id, to_char(a1.board_date, 'YYYY-MM-DD') as board_date, a1.views, a2.user_nickname "
			+ "from BOARD a1, USER_INFO a2 " + "where a1.user_idx = a2.user_idx "
			+ "and a1.board_info_idx = #{board_info_idx} " + "order by a1.board_id desc")
	List<BoardBean> getBoardList(int board_info_idx, RowBounds rowBounds);

	// 상세정보 조회
	@Select("select a2.user_nickname, a2.user_id, a2.user_idx, to_char(a1.board_date, 'YYYY-MM-DD') as board_date, a1.board_id, a1.board_title, a1.board_content, a1.board_pic "
			+ "from BOARD a1, USER_INFO a2 " + "where a1.user_idx = a2.user_idx and a1.board_id = #{board_id}")
	BoardBean getBoardInfo(int board_id);

	// 수정하기
	@Update("update board set board_title = #{board_title}, board_content = #{board_content}, board_pic = #{board_pic, jdbcType=VARCHAR} "
			+ "where board_id = #{board_id}")
	void modifyBoardInfo(BoardBean modifyBoardBean);

	// 삭제하기
	@Delete("delete from BOARD where board_id = #{board_id}")
	void deleteBoardInfo(int board_id);

	// 해당 게시판의 전체글의 수 가져오기
	@Select("select count(*) from BOARD where BOARD_INFO_IDX = #{board_info_idx}")
	int getBoardCnt(int board_info_idx);

	// 조회수 증가 메서드
	@Update("UPDATE board SET views = views + 1 WHERE board_id = #{board_id}")
	void updateHits(int board_id);
}
