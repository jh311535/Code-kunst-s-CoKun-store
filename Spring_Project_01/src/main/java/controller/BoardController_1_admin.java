package controller;

import dto.BoardDTO_admin;
import dto.PageBean_admin;
import filter.BoardFilterDTO_admin;
import service.BoardService_admin;
import service.UserInfoService_admin;

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
@RequestMapping("/admin/board/1")
public class BoardController_1_admin { // 자유 게시판

    @Autowired
    private BoardService_admin boardService;

    @Autowired
    private UserInfoService_admin userInfoService;

    @GetMapping("/manage")
    public String manageBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pageChange", required = false) String pageChange,
                               Model model,
                               HttpSession session) {
        BoardFilterDTO_admin filter = (BoardFilterDTO_admin) session.getAttribute("boardFilter1");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new BoardFilterDTO_admin();
            session.setAttribute("boardFilter1", filter);
        }

        filter.setBoard_info_idx(1); // 자유 게시판으로 설정

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<BoardDTO_admin> boardList = boardService.getBoardsByFilterAndSort(filter);
        int totalBoards = boardService.getBoardCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalBoards, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (BoardDTO_admin board : boardList) {
            try {
                board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date().toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/board/1/manage";
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
            @RequestParam String returnJSP,
            @ModelAttribute BoardFilterDTO_admin filter,
            HttpSession session) {

        filter.setBoard_id(board_id);
        filter.setUser_idx(user_idx);
        filter.setBoard_content(board_content);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        filter.setMinViews(minViews);
        filter.setMaxViews(maxViews);
        filter.setBoard_title(board_title);
        filter.setBoard_info_idx(1); // 자유 게시판으로 설정

        session.setAttribute("boardFilter1", filter);

        return "redirect:/admin/board/1/manage?page=1&search=true";
    }

    @GetMapping("/sort")
    public String sortBoards(@RequestParam("sortField") String sortField,
                             @RequestParam String returnJSP, HttpSession session) {
        BoardFilterDTO_admin filter = (BoardFilterDTO_admin) session.getAttribute("boardFilter1");

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
                default:
                    break;
            }
            session.setAttribute("boardFilter1", filter);
        }

        return "redirect:/admin/board/1/manage?page=1&sort=true";
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

    @GetMapping("/foreign_key")
    public String foreignKeyBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pageChange", required = false) String pageChange,
                                   Model model,
                                   HttpSession session) {
        BoardFilterDTO_admin filter = (BoardFilterDTO_admin) session.getAttribute("boardFilter1");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new BoardFilterDTO_admin();
            session.setAttribute("boardFilter1", filter);
        }

        filter.setBoard_info_idx(1); // 자유 게시판으로 설정

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<BoardDTO_admin> boardList = boardService.getBoardsByFilterAndSort(filter);
        int totalBoards = boardService.getBoardCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalBoards, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (BoardDTO_admin board : boardList) {
            try {
                board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date().toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/board/1/foreign_key";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyBoardForm(@PathVariable("id") int id, HttpSession session, Model model) {
        BoardDTO_admin board = boardService.getBoardById(id, 1);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("admin/board/1/modify");
        mav.addObject("board", board);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyBoard(@ModelAttribute BoardDTO_admin board,
                              @RequestParam(value = "delete_pic", defaultValue = "false") Boolean deletePic) {
        board.setBoard_info_idx(1); // 자유 게시판으로 설정
        boardService.updateBoard(board, deletePic);
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/board/1/detail/" + board.getBoard_id();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailBoard(@PathVariable("id") int id) {
        BoardDTO_admin board = boardService.getBoardById(id, 1);

        // 작성자 닉네임
        String userNickname = userInfoService.getUserById(board.getUser_idx()).getUser_nickname();
        board.setUser_nickname(userNickname);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            board.setFormattedDate(outputFormat.format(inputFormat.parse(board.getBoard_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/board/1/detail");
        mav.addObject("board", board);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam("id") int id) {
        boardService.deleteBoard(id, 1);
        return "redirect:/admin/board/1/manage";
    }
}
