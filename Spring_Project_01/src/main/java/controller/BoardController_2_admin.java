package controller;

import dto.QnADTO_admin;
import dto.AdminInfoDTO_admin;
import dto.PageBean_admin;
import filter.QnAFilterDTO_admin;
import service.BoardService_admin;
import service.QnAService_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/board/2")
public class BoardController_2_admin { // QnA 게시판

    @Autowired
    private BoardService_admin boardService;

    @Autowired
    private QnAService_admin qnaService;

    @GetMapping("/manage")
    public String manageBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pageChange", required = false) String pageChange,
                               Model model,
                               HttpSession session) {
        QnAFilterDTO_admin filter = (QnAFilterDTO_admin) session.getAttribute("boardFilter2");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new QnAFilterDTO_admin();
            session.setAttribute("boardFilter2", filter);
        }

        filter.setBoard_info_idx(2); // QnA 게시판으로 설정

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<QnADTO_admin> boardList = qnaService.getQnAsByFilterAndSort(filter);
        int totalBoards = qnaService.getQnACountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalBoards, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (QnADTO_admin board : boardList) {
            try {
                board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date())));
                board.setHasAnswer(qnaService.isAnswered(board.getBoard_id()));
                if (board.getQna_comment_date() != null) {
                    board.setFormattedQnaCommentDate(outputFormat.format(inputFormat.parse(board.getQna_comment_date())));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/board/2/manage";
    }

    @PostMapping("/search")
    public String searchBoards(
            @RequestParam(required = false) Integer board_id,
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) String board_content,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer minViews,
            @RequestParam(required = false) Integer maxViews,
            @RequestParam(required = false) String board_title,
            @RequestParam(required = false) Integer qna_idx,
            @RequestParam(required = false) Integer admin_idx,
            @RequestParam(required = false) String qna_comment,
            @RequestParam(required = false) String qna_startDate,
            @RequestParam(required = false) String qna_endDate,
            @RequestParam String returnJSP,
            @ModelAttribute QnAFilterDTO_admin filter,
            HttpSession session) {

        filter.setBoard_id(board_id);
        filter.setUser_idx(user_idx);
        filter.setBoard_content(board_content);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        filter.setMinViews(minViews);
        filter.setMaxViews(maxViews);
        filter.setBoard_title(board_title);
        filter.setQna_idx(qna_idx);
        filter.setAdmin_idx(admin_idx);
        filter.setQna_comment(qna_comment);
        filter.setQna_startDate(qna_startDate);
        filter.setQna_endDate(qna_endDate);
        filter.setBoard_info_idx(2); // QnA 게시판으로 설정

        session.setAttribute("boardFilter2", filter);

        return "redirect:/admin/board/2/manage?page=1&search=true";
    }

    @GetMapping("/sort")
    public String sortBoards(@RequestParam("sortField") String sortField,
                             @RequestParam String returnJSP, HttpSession session) {
        QnAFilterDTO_admin filter = (QnAFilterDTO_admin) session.getAttribute("boardFilter2");

        if (filter != null) {
            switch (sortField) {
                case "boardId":
                    filter.setBoardIdOrder(toggleOrder(filter.getBoardIdOrder()));
                    break;
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "content":
                    filter.setContentOrder(toggleOrder(filter.getContentOrder()));
                    break;
                case "date":
                    filter.setDateOrder(toggleOrder(filter.getDateOrder()));
                    break;
                case "views":
                    filter.setViewsOrder(toggleOrder(filter.getViewsOrder()));
                    break;
                case "title":
                    filter.setTitleOrder(toggleOrder(filter.getTitleOrder()));
                    break;
                case "boardInfoIdx":
                    filter.setBoardInfoIdxOrder(toggleOrder(filter.getBoardInfoIdxOrder()));
                    break;
                case "picOrder":
                    filter.setPicOrder(toggleOrder(filter.getPicOrder()));
                    break;
                case "qnaId":
                    filter.setQnaIdOrder(toggleOrder(filter.getQnaIdOrder()));
                    break;
                case "adminIdx":
                    filter.setAdminIdxOrder(toggleOrder(filter.getAdminIdxOrder()));
                    break;
                case "qnaComment":
                    filter.setQnaCommentOrder(toggleOrder(filter.getQnaCommentOrder()));
                    break;
                case "qnaDate":
                    filter.setQnaDateOrder(toggleOrder(filter.getQnaDateOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("boardFilter2", filter);
        }

        return "redirect:/admin/board/2/manage?page=1&sort=true";
    }

    private String toggleOrder(String currentOrder) {
        if (currentOrder == null) {
            return "asc";
        } else if ("asc".equals(currentOrder)) {
            return "desc";
        } else {
            return null;
        }
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailBoard(@PathVariable("id") int id) {
        QnADTO_admin board = qnaService.getQnAById(id);

        // 답변 작성 여부
        boolean hasAnswer = qnaService.isAnswered(board.getBoard_id());
        board.setHasAnswer(hasAnswer);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date())));
            if (board.getQna_comment_date() != null) {
                board.setFormattedQnaCommentDate(outputFormat.format(inputFormat.parse(board.getQna_comment_date())));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/board/2/detail");
        mav.addObject("board", board);
        return mav;
    }

    @GetMapping("/answer/add/{board_id}")
    public ModelAndView addAnswerForm(@PathVariable("board_id") int board_id, HttpSession session) {
        QnADTO_admin board = qnaService.getQnAById(board_id);
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        board.setAdmin_idx(admin.getAdmin_idx());
        ModelAndView mav = new ModelAndView("admin/board/2/answer_add");
        mav.addObject("qna", board);
        return mav;
    }

    @PostMapping("/answer/add")
    public String addAnswer(@ModelAttribute QnADTO_admin qna, HttpSession session) {
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        qna.setAdmin_idx(admin.getAdmin_idx());
        qnaService.addQnAComment(qna);
        return "redirect:/admin/board/2/detail/" + qna.getBoard_id();
    }

    @GetMapping("/answer/modify/{board_id}")
    public ModelAndView modifyAnswerForm(@PathVariable("board_id") int board_id) {
        QnADTO_admin qna = qnaService.getQnAById(board_id);
        ModelAndView mav = new ModelAndView("admin/board/2/answer_modify");
        mav.addObject("qna", qna);
        return mav;
    }

    @PostMapping("/answer/modify")
    public String modifyAnswer(@ModelAttribute QnADTO_admin qna) {
        qnaService.updateQnAComment(qna);
        return "redirect:/admin/board/2/detail/" + qna.getBoard_id();
    }

    @PostMapping("/answer/delete")
    public String deleteAnswer(@RequestParam("board_id") int board_id) {
        qnaService.deleteQnAComment(board_id);
        return "redirect:/admin/board/2/detail/" + board_id;
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyBoardForm(@PathVariable("id") int id) {
        QnADTO_admin board = qnaService.getQnAById(id);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("admin/board/2/modify");
        mav.addObject("board", board);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyBoard(@ModelAttribute QnADTO_admin board,
                              @RequestParam(value = "delete_pic", defaultValue = "false") Boolean deletePic) {
        board.setBoard_info_idx(2); // QnA 게시판으로 설정
        boardService.updateQnABoard(board, deletePic);
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/board/2/detail/" + board.getBoard_id();
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam("id") int id) {
        boardService.deleteBoard(id, 2);
        return "redirect:/admin/board/2/manage";
    }
}
