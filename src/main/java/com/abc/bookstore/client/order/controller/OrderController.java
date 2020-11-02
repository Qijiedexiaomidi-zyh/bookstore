package com.abc.bookstore.client.order.controller;

import com.abc.bookstore.client.order.service.OrderItemService;
import com.abc.bookstore.client.order.service.OrderService;
import com.abc.bookstore.client.product.service.ProductService;
import com.abc.bookstore.commons.beans.Order;
import com.abc.bookstore.commons.beans.OrderItem;
import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.User;
import com.abc.bookstore.utils.AlipayConfig;
import com.abc.bookstore.utils.IdUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    /**
     * 创建订单
     */
    @RequestMapping("/createOrder")
    public String createOrder(Order order, HttpSession session){

        order.setId(IdUtils.getUUID());
        order.setUser((User) session.getAttribute("login_user"));

        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        orderService.createOrder(order,cart,session);

        //生成订单之后，清除购物车的内容
        session.removeAttribute("cart");
        session.setAttribute("order", order);
        return "/client/confirm.jsp";
    }

    /**
     * 支付功能
     */
    @RequestMapping("/pay")
    public void pay(String id, String money, HttpServletResponse response) throws AlipayApiException, IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = id;
        //付款金额，必填
        String total_amount = money;
        //订单名称，必填
        String subject = id;
        //商品描述，可空
        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出
        //out.println(result);
        response.setContentType("text/html");
        response.getWriter().println(result);
    }

    /**
     * 支付成功
     */
    @RequestMapping("/paysuccess")
    public String paysuccess(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String order_id = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            //修改数据库的订单状态
            orderService.updatePayState(order_id);
            return "redirect:/client/paysuccess.jsp";
            //out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        }else {
            //out.println("验签失败");
            return "redirect:/client/payError.jsp";
        }
    }

    /**
     * 订单查询
     */
    @RequestMapping("/findOrderByUser")
    public String findOrderByUser(HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        List<Order> orders = orderService.findOrderByUser(login_user.getId());
        session.setAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }

    /**
     * 订单具体信息的查询
     */
    @RequestMapping("/findOrderItemById")
    public String findOrderItemById(String id,HttpSession session){
        List<OrderItem> orderItems = orderItemService.findOrderItemById(id);
        session.setAttribute("orderItems",orderItems);
        return "/client/orderInfo.jsp";
    }

    /**
     * 删除订单信息
     */
    @RequestMapping("/delOrderById")
    public String delOrderById(String id,Integer flag,HttpSession session){
        orderService.delOrderById(id,flag,session);
        return "redirect:/client/order/findOrderByUser";
    }
}
