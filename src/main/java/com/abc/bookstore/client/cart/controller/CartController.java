package com.abc.bookstore.client.cart.controller;

import com.abc.bookstore.client.product.service.ProductService;
import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    /**
     * 添加购物车，放到session域
     */
    @RequestMapping("/addCart")
    public String addCart(String id, HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        if (login_user==null){
            session.setAttribute("msg3","请先登录，再加入购物车");
            return "/client/login.jsp";
        }else {
            Product product = productService.findProductById(id);
            Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            if (cart==null){
                //创建购物车
                cart = new HashMap<>();
            }
            Integer count = cart.put(product,1);
            if (count != null){
                cart.put(product,count+1);
                //在addCart方法中判断是否达到了最大库存值
                if ((count+1) > product.getPnum()){
                    cart.put(product,count);
                }
            }
            session.setAttribute("cart",cart);
            session.setAttribute("count",count);
            session.setAttribute("product",product);
            System.out.println("product: "+product);
            return "redirect:/client/cart.jsp";
        }
    }

    /**
     * 修改购物车的数据
     */
    @RequestMapping("/changeCart")
    public String changeCart(String id,Integer count, HttpSession session){
        Product product = productService.findProductById(id);
        //从session中获取原始数据
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if (count == 0){
            //删除该商品
            cart.remove(product);
        }else {
            cart.put(product,count);
        }
        return "redirect:/client/cart.jsp";
    }
}
