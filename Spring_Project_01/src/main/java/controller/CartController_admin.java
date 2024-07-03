package controller;

import dto.CartDTO_admin;
import dto.PageBean_admin;
import filter.CartFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CartService_admin;
import service.BookService_admin;

import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/admin/cart")
public class CartController_admin {

    @Autowired
    private CartService_admin cartService;

    @Autowired
    private BookService_admin bookService;

    @GetMapping("/manage")
    public String manageCarts(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "search", required = false) String search,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "pageChange", required = false) String pageChange,
                              Model model,
                              HttpSession session) {
        CartFilterDTO_admin filter = (CartFilterDTO_admin) session.getAttribute("cartFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new CartFilterDTO_admin();
            session.setAttribute("cartFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<CartDTO_admin> cartList = cartService.getCartsByFilterAndSort(filter);
        int totalCarts = cartService.getCartCountByFilter(filter);  // 총 장바구니 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalCarts, page, pageSize, paginationCnt);

        // 책 이름과 책 이미지도 넣기
        for (CartDTO_admin cart : cartList) {
        	int temp_book_id = cart.getBook_id();
            String bookName = bookService.getBookNameById(temp_book_id);
            String bookPic = bookService.getBookPicById(temp_book_id);
        	cart.setBook_name(bookName);
        	cart.setBook_pic(bookPic);
        }
        
        model.addAttribute("cartList", cartList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/cart/manage";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailCart(@PathVariable("id") int id) {
        CartDTO_admin cart = cartService.getCartById(id);

        // 책 이름과 사진 URL
        String bookName = bookService.getBookNameById(cart.getBook_id());
        String bookPic = bookService.getBookPicById(cart.getBook_id());

        ModelAndView mav = new ModelAndView("admin/cart/detail");
        mav.addObject("cart", cart);
        mav.addObject("bookName", bookName);
        mav.addObject("bookPic", bookPic);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteCart(@RequestParam("id") int id) {
        cartService.deleteCart(id);
        return "redirect:/admin/cart/manage";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyCartForm(@PathVariable("id") int id) {
        CartDTO_admin cart = cartService.getCartById(id);

        // 책 이름과 사진 URL
        String bookName = bookService.getBookNameById(cart.getBook_id());
        String bookPic = bookService.getBookPicById(cart.getBook_id());
        
        ModelAndView mav = new ModelAndView("admin/cart/modify");
        mav.addObject("cart", cart);
        mav.addObject("bookName", bookName);
        mav.addObject("bookPic", bookPic);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyCart(@ModelAttribute CartDTO_admin cart) {
        cartService.updateCart(cart);
        //return "redirect:/admin/cart/manage?page=1";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/cart/detail/" + cart.getCart_id();
    }

    @PostMapping("/search")
    public String searchCarts(
            @RequestParam(required = false) Integer cart_id,
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) Integer book_id,
            @RequestParam(required = false) Integer cart_quantity_min,
            @RequestParam(required = false) Integer cart_quantity_max,
            @RequestParam String returnJSP,
            @ModelAttribute CartFilterDTO_admin filter,
            HttpSession session) {

        filter.setCart_id(cart_id);
        filter.setUser_idx(user_idx);
        filter.setBook_id(book_id);
        filter.setCart_quantity_min(cart_quantity_min);
        filter.setCart_quantity_max(cart_quantity_max);

        session.setAttribute("cartFilter", filter);

        return "redirect:/admin/cart/manage?page=1&search=true";
    }

    @GetMapping("/sort")
    public String sortCarts(@RequestParam("sortField") String sortField,
                            @RequestParam String returnJSP, HttpSession session) {
        CartFilterDTO_admin filter = (CartFilterDTO_admin) session.getAttribute("cartFilter");

        if (filter != null) {
            switch (sortField) {
                case "cartId":
                    filter.setCartIdOrder(toggleOrder(filter.getCartIdOrder()));
                    break;
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "bookId":
                    filter.setBookIdOrder(toggleOrder(filter.getBookIdOrder()));
                    break;
                case "cartQuantity":
                    filter.setCartQuantityOrder(toggleOrder(filter.getCartQuantityOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("cartFilter", filter);
        }

        return "redirect:/admin/cart/manage?page=1&sort=true";
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
}
