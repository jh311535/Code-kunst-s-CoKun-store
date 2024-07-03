/*
이 코드는 Spring Framework를 사용하여 게시판 애플리케이션의 컨트롤러를 구현한 것으로, 게시글의 목록 조회, 상세 조회, 작성, 수정, 삭제 등의 기능을 제공합니다. 각 메서드는 HTTP 요청을 처리하고 적절한 모델 데이터를 뷰에 전달하여 사용자 인터페이스를 구성합니다.
*/
package kr.co.soldesk.controller;

import kr.co.soldesk.beans.BoardBean;
import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.beans.CommentBean;
import kr.co.soldesk.beans.PageBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.mapper.CommentMapper;
import kr.co.soldesk.service.BoardService;
import kr.co.soldesk.service.CommentService;
import kr.co.soldesk.service.MainService;
import kr.co.soldesk.service.TopMenuService;
import kr.co.soldesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

	// BoardService를 주입받아 사용함
	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@Autowired
	private MainService mainService;

	@Autowired
	private CommentService commentService;
	// TopMenuService를 주입받아 사용함
	@Autowired
	private TopMenuService topMenuService;

	/*
	 * @Autowired private CommentService commentService;
	 */

	// 현재 로그인한 사용자 정보를 주입받아 사용함
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/home")
	public String main(Model model, HttpServletRequest request) {

		// 각 게시판의 최신 글 목록을 저장할 리스트를 생성
		ArrayList<List<BoardBean>> list = new ArrayList<List<BoardBean>>();

		// 2개의 게시판에 대해 반복
		for (int i = 1; i <= 2; i++) {
			// 각각 게시판의 최신글 5개 가져옴
			List<BoardBean> list1 = mainService.getMainList(i);
			// 리스트에 추가
			list.add(list1);
		}

		// 모델에 최신 글 목록 리스트를 추가
		model.addAttribute("list", list);

		// 게시판의 번호와 이름을 가져옴
		List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
		// 모델에 게시판 목록을 추가
		model.addAttribute("board_list", board_list);

		// 뷰 반환
		return "board/home";
	}

	// 게시판 메인 페이지 - 모델에 게시판 정보, 게시글 목록, 페이지 정보를 추가하고 board/main 뷰를 반환함
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

		// 게시판 인덱스를 모델에 추가
		model.addAttribute("board_info_idx", board_info_idx);

		// 게시판 이름을 가져와 모델에 추가
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);

		// 게시글 목록을 가져와 모델에 추가
		List<BoardBean> boardList = boardService.getBoardList(board_info_idx, page);

		// 각 게시판 글의 작성자 정보를 가져와서 리스트에 추가
		for (BoardBean board : boardList) {
			UserBean user = userService.getUserByIdx(board.getUser_idx());
			board.setUserBean(user);
		}

		model.addAttribute("boardList", boardList);

		// 페이지 정보를 가져와 모델에 추가
		PageBean pageBean = boardService.getBoardCnt(board_info_idx, page);
		model.addAttribute("pageBean", pageBean);

		// 현재 페이지 번호를 모델에 추가
		model.addAttribute("page", page); // 어느 페이지 글번호인지 찾아가야함

		// 뷰 반환
		return "board/main";
	}

	@GetMapping("/read1")
    public String read(@RequestParam("board_info_idx") int board_info_idx,
                       @RequestParam("board_id") int board_id,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "commentPage", defaultValue = "1") int commentPage,
                       HttpSession session, Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        model.addAttribute("board_info_idx", board_info_idx);
        model.addAttribute("board_id", board_id);
        model.addAttribute("loginUserBean", loginUserBean);

        boardService.updateHits(board_id);

        BoardBean readBoardBean = boardService.getBoardInfo(board_id);
        model.addAttribute("readBoardBean", readBoardBean);

        if (readBoardBean.getUserBean() != null) {
            model.addAttribute("user_id", readBoardBean.getUserBean().getUser_id());
        } else {
            model.addAttribute("user_id", "");
        }

        boolean isOwner = (loginUserBean.getUser_idx() == readBoardBean.getUser_idx());
        model.addAttribute("isOwner", isOwner);

        model.addAttribute("page", page);

        // 댓글 목록과 페이지 정보를 모델에 추가
        int commentsPerPage = 3;
        List<CommentBean> commentList = commentService.getCommentList(board_id, commentPage, commentsPerPage);
        model.addAttribute("commentList", commentList);

        PageBean commentPageBean = commentService.getCommentPageBean(board_id, commentPage, commentsPerPage);
        model.addAttribute("commentPageBean", commentPageBean);

        return "board/read1";
    }

	// BoardController.java
	@GetMapping("/read2")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
	                   @RequestParam("board_id") int board_id,
	                   @RequestParam(value = "page", defaultValue = "1") int page,
	                   HttpSession session, Model model) {
		
		
	    UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
	    model.addAttribute("board_info_idx", board_info_idx);
	    model.addAttribute("board_id", board_id);
	    model.addAttribute("loginUserBean", loginUserBean);

	    boardService.updateHits(board_id);

	    BoardBean readBoardBean = boardService.getBoardInfo(board_id);
	    model.addAttribute("readBoardBean", readBoardBean);

	    if (readBoardBean.getUserBean() != null) {
	        model.addAttribute("user_id", readBoardBean.getUserBean().getUser_id());
	    } else {
	        model.addAttribute("user_id", "");
	    }

	    boolean isOwner = (loginUserBean.getUser_idx() == readBoardBean.getUser_idx());
	    model.addAttribute("isOwner", isOwner);

	    model.addAttribute("page", page);

	    // 관리자 답변 가져오기
	    CommentBean adminComment = commentService.getAdminComment(board_id);
	    model.addAttribute("adminComment", adminComment);

	    return "board/read2";
	}

	@PostMapping("/comment/save")
    @ResponseBody
    public ResponseEntity<?> addComment(@RequestParam("user_idx") int user_idx,
                                        @RequestParam("comment_content") String comment_content,
                                        @RequestParam("board_id") int board_id,
                                        @RequestParam("board_info_idx") int board_info_idx) {
        try {
            CommentBean commentBean = new CommentBean();
            commentBean.setUser_idx(user_idx);
            commentBean.setComment_content(comment_content);
            commentBean.setBoard_id(board_id);

            commentService.addComment(commentBean);

            int commentsPerPage = 3;
            List<CommentBean> commentList = commentService.getCommentList(board_id, 1, commentsPerPage);
            
            PageBean commentPageBean = commentService.getCommentPageBean(board_id, 1, commentsPerPage);

            return new ResponseEntity<>(new CommentResponse(commentList, commentPageBean), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class CommentResponse {
        private List<CommentBean> commentList;
        private PageBean pageBean;

        public CommentResponse(List<CommentBean> commentList, PageBean pageBean) {
            this.commentList = commentList;
            this.pageBean = pageBean;
        }

        public List<CommentBean> getCommentList() {
            return commentList;
        }

        public PageBean getPageBean() {
            return pageBean;
        }
    }


	// 게시글 작성 페이지 - writeContentBean을 모델에 추가하고 board/write 뷰를 반환함
	@GetMapping("/write")
	public String write(@ModelAttribute("writeBoardBean") BoardBean writeBoardBean,
			@RequestParam("board_info_idx") int board_info_idx, HttpSession session) {

		// 게시판 인덱스를 작성할 게시글에 설정
		UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
		System.out.println("idx : " + loginUserBean.getUser_idx());

		if (loginUserBean == null) {
			return "user/not_login";
		} else {
			writeBoardBean.setUser_idx(loginUserBean.getUser_idx());
			writeBoardBean.setBoard_info_idx(board_info_idx);
			return "board/write";
		}

	}

	// 게시글 작성 처리 - 유효성 검사를 수행하고 게시글을 추가한 후, 성공 페이지를 반환함
	@PostMapping("/writer_pro")
	public String writer_pro(@Valid @ModelAttribute("writeBoardBean") BoardBean writeBoardBean, BindingResult result,
			HttpSession session) {

		// 유효성 검사에서 오류가 발생하면 작성 페이지로 이동
		if (result.hasErrors()) {
			return "board/write";
		}

		UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

		if (loginUserBean == null) {
			return "user/not_login";
		} else {
			writeBoardBean.setUser_idx(loginUserBean.getUser_idx());
			boardService.addBoardInfo(writeBoardBean);
			return "board/write_success";
		}

	}
	
	@PostMapping("/comment/delete")
	@ResponseBody
	public ResponseEntity<String> deleteComment(@RequestParam("comment_id") int comment_id) {
	    try {
	        commentService.deleteComment(comment_id);
	        return new ResponseEntity<>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	// 작성 권한이 없을 경우의 페이지를 반환
	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}

	// 게시글 수정 페이지 - 모델에 게시판 정보, 게시글 정보, 수정할 게시글의 기존 정보를 추가하고 board/modify 뷰 반환
	// 1. 게시글 가져오기 2. 수정
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx, @RequestParam("board_id") int board_id,
			@ModelAttribute("modifyBoardBean") BoardBean modifyBoardBean, @RequestParam("page") int page, Model model) {

		// 게시글 인덱스, 현재 페이지 번호를 모델에 추가
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("board_id", board_id);
		model.addAttribute("page", page);

		// 수정할 게시글 정보를 가져와 모델에 추가
		BoardBean tempBoardBean = boardService.getBoardInfo(board_id);

		// 수정할 게시글 정보를 수정할 게시글 빈에 설정
		modifyBoardBean.setBoard_date(tempBoardBean.getBoard_date());
		modifyBoardBean.setBoard_title(tempBoardBean.getBoard_title());
		modifyBoardBean.setBoard_content(tempBoardBean.getBoard_content());
		modifyBoardBean.setUser_nickname(tempBoardBean.getUser_nickname());
		modifyBoardBean.setBoard_pic(tempBoardBean.getBoard_pic());
		modifyBoardBean.setBoard_id(board_id);
		modifyBoardBean.setBoard_info_idx(board_info_idx);

		// 뷰 반환
		return "board/modify";
	}

	// 게시글 수정 처리 - 유효성 검사를 수행하고 게시글을 수정한 후 성공 페이지를 반환함
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyBoardBean") BoardBean modifyBoardBean,
			@RequestParam("page") int page, BindingResult result, Model model) {

		// 유효성 검사에서 오류가 발생하면 수정 페이지로 이동
		if (result.hasErrors()) {
			model.addAttribute("page", page);
			return "board/modify";
		}

		// 게시글 정보를 수정
		boardService.modifyBoardInfo(modifyBoardBean);

		// 현재 페이지 번호를 모델에 추가
		model.addAttribute("page", page);

		// 뷰 반환
		return "board/modify_success";

	}

	// 게시글 삭제 처리 - 게시글을 삭제한 후 삭제 확인 페이지를 반환함
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx, @RequestParam("board_id") int board_id,
			Model model) {

		// 게시글 정보를 삭제
		boardService.deleteBoardInfo(board_id);

		// 게시판 인덱스를 모델에 추가
		model.addAttribute("board_info_idx", board_info_idx);

		// 뷰 반환
		return "board/delete";
	}

}
