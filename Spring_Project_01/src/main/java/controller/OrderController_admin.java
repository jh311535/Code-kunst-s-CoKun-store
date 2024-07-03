package controller;

import dto.OrderDTO_admin;
import dto.PageBean_admin;
import filter.OrderFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.OrderService_admin;
import service.BookService_admin;

import javax.servlet.http.HttpSession;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/order")
public class OrderController_admin {

    @Autowired
    private OrderService_admin orderService;

    @Autowired
    private BookService_admin bookService;

    @GetMapping("/manage")
    public String manageOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pageChange", required = false) String pageChange,
                               Model model,
                               HttpSession session) {
        OrderFilterDTO_admin filter = (OrderFilterDTO_admin) session.getAttribute("orderFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new OrderFilterDTO_admin();
            session.setAttribute("orderFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<OrderDTO_admin> orderList = orderService.getOrdersByFilterAndSort(filter);
        int totalOrders = orderService.getOrderCountByFilter(filter);  // 총 주문 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalOrders, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (OrderDTO_admin order : orderList) {
            try {
                order.setFormattedDate(outputFormat.format(inputFormat.parse(order.getOrder_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/order/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pageChange", required = false) String pageChange,
                                   Model model,
                                   HttpSession session) {
        OrderFilterDTO_admin filter = (OrderFilterDTO_admin) session.getAttribute("orderFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new OrderFilterDTO_admin();
            session.setAttribute("orderFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<OrderDTO_admin> orderList = orderService.getOrdersByFilterAndSort(filter);
        int totalOrders = orderService.getOrderCountByFilter(filter);  // 총 주문 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalOrders, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (OrderDTO_admin order : orderList) {
            try {
                order.setFormattedDate(outputFormat.format(inputFormat.parse(order.getOrder_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/order/foreign_key";
    }

    @PostMapping("/search")
    public String searchOrders(
            @RequestParam(required = false) Integer order_id,
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) Integer book_id,
            @RequestParam(required = false) Long order_number,
            @RequestParam(required = false) Integer order_quantity_min,
            @RequestParam(required = false) Integer order_quantity_max,
            @RequestParam(required = false) Integer order_price_min,
            @RequestParam(required = false) Integer order_price_max,
            @RequestParam(required = false) String orderer_name,
            @RequestParam(required = false) String addressnum,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String detailaddress,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String request,
            @RequestParam(required = false) String request_text,
            @RequestParam(required = false) String delivery_state,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam String returnJSP,
            @ModelAttribute OrderFilterDTO_admin filter,
            HttpSession session) {

        filter.setOrder_id(order_id);
        filter.setUser_idx(user_idx);
        filter.setBook_id(book_id);
        filter.setOrder_number(order_number);
        filter.setOrder_quantity_min(order_quantity_min);
        filter.setOrder_quantity_max(order_quantity_max);
        filter.setOrder_price_min(order_price_min);
        filter.setOrder_price_max(order_price_max);
        filter.setOrderer_name(orderer_name);
        filter.setAddressnum(addressnum);
        filter.setAddress(address);
        filter.setDetailaddress(detailaddress);
        filter.setPhone(phone);
        filter.setEmail(email);
        filter.setRequest(request);
        filter.setRequest_text(request_text);
        filter.setDelivery_state(delivery_state);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);

        session.setAttribute("orderFilter", filter);

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/order/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/order/foreign_key?page=1&search=true";
        } else {
            return "admin/order/manage";
        }
    }

    @GetMapping("/sort")
    public String sortOrders(@RequestParam("sortField") String sortField,
                             @RequestParam String returnJSP, HttpSession session) {
        OrderFilterDTO_admin filter = (OrderFilterDTO_admin) session.getAttribute("orderFilter");

        if (filter != null) {
            switch (sortField) {
                case "orderId":
                    filter.setOrderIdOrder(toggleOrder(filter.getOrderIdOrder()));
                    break;
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "bookId":
                    filter.setBookIdOrder(toggleOrder(filter.getBookIdOrder()));
                    break;
                case "orderNumber":
                    filter.setOrderNumberOrder(toggleOrder(filter.getOrderNumberOrder()));
                    break;
                case "orderQuantity":
                    filter.setOrderQuantityOrder(toggleOrder(filter.getOrderQuantityOrder()));
                    break;
                case "orderPrice":
                    filter.setOrderPriceOrder(toggleOrder(filter.getOrderPriceOrder()));
                    break;
                case "orderDate":
                    filter.setOrderDateOrder(toggleOrder(filter.getOrderDateOrder()));
                    break;
                case "deliveryState":
                    filter.setDeliveryStateOrder(toggleOrder(filter.getDeliveryStateOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("orderFilter", filter);
        }

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/order/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/order/foreign_key?page=1&sort=true";
        } else {
            return "admin/order/manage";
        }
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
    public ModelAndView detailOrder(@PathVariable("id") int id) {
        OrderDTO_admin order = orderService.getOrderById(id);

        // 책 이름과 사진 URL
        String bookName = bookService.getBookNameById(order.getBook_id());
        String bookPic = bookService.getBookPicById(order.getBook_id());

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            order.setFormattedDate(outputFormat.format(inputFormat.parse(order.getOrder_date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/order/detail");
        mav.addObject("order", order);
        mav.addObject("bookName", bookName);
        mav.addObject("bookPic", bookPic);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("id") int id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/order/manage";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyOrderForm(@PathVariable("id") int id) {
        OrderDTO_admin order = orderService.getOrderById(id);
        ModelAndView mav = new ModelAndView("admin/order/modify");
        mav.addObject("order", order);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyOrder(@ModelAttribute OrderDTO_admin order) {
        orderService.updateOrder(order);
        //return "redirect:/admin/order/manage?page=1";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/order/detail/" + order.getOrder_id();
    }
}
