package com.abc.bookstore.client.product.controller;

import com.abc.bookstore.client.product.service.ProductService;
import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/client/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 分页查询
     */
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(String category, @RequestParam(defaultValue = "1") Integer pageNum, HttpSession session){

        //获取指定页的大小，pageSize=3
        PageHelper.startPage(pageNum,3);
        //分页的方法
        List<Product> products = productService.findProductByCategory(category);
        //使用PageInfo包装数据
        //navigatePages：导航的页码数
        PageInfo pageInfo = new PageInfo(products);
        session.setAttribute("pageInfo",pageInfo);
        session.setAttribute("products",products);
        session.setAttribute("category",category);

        return "/client/product_list.jsp";
    }

    /**
     * 显示书籍的具体信息
     */
    @RequestMapping("/findProductById")
    public String findProductById(String id,HttpSession session){
        Product product = productService.findProductById(id);
        session.setAttribute("product",product);
        return "/client/info.jsp";
    }

    /**
     *菜单栏的查询
     */
    @RequestMapping("/MenuSearchSerlvet")
    public String MenuSearchSerlvet(String name, @RequestParam(defaultValue = "1") Integer pageNum, HttpSession session){

        //获取指定页的大小，pageSize=3
        PageHelper.startPage(pageNum,3);
        //分页的方法
        List<Product> products2 = productService.MenuSearchSerlvet(name);
        //使用PageInfo包装数据
        //navigatePages：导航的页码数
        PageInfo pageInfo2 = new PageInfo(products2);
        session.setAttribute("pageInfo2",pageInfo2);
        session.setAttribute("name",name);
        session.setAttribute("products2",products2);
        return "/client/product_search_list.jsp";
    }

}
