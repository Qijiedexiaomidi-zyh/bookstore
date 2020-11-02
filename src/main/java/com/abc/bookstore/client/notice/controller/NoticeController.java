package com.abc.bookstore.client.notice.controller;

import com.abc.bookstore.client.notice.service.NoticeService;
import com.abc.bookstore.client.product.service.ProductService;
import com.abc.bookstore.commons.beans.Notice;
import com.abc.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ProductService productService;

    /**
     * 首页的公告显示以及热卖商品的显示
     */
    @RequestMapping("/showindex")
    public String showindex(Model model){

        Notice notice = noticeService.findNoticeByTime();

        List<Product> products = productService.findHotProduct();

        model.addAttribute("notice",notice);
        model.addAttribute("products",products);
        return "/client/index.jsp";
    }
}
