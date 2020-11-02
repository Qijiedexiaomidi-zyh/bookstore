package com.abc.bookstore.client.order.service;

import com.abc.bookstore.client.order.dao.OrderDao;
import com.abc.bookstore.client.product.service.ProductService;
import com.abc.bookstore.commons.beans.Order;
import com.abc.bookstore.commons.beans.OrderItem;
import com.abc.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public void createOrder(Order order, Map<Product, Integer> cart, HttpSession session) {
        for (Product p : cart.keySet()){

            //逐一插入订单项数据：orderitem
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(p);
            orderItem.setBuynum(cart.get(p));

            orderItemService.save(orderItem);
            //int i=10/0;
            //修改库存值：products
            productService.updateProductPnum(orderItem);
        }
        //插入数据订单表：orders
        orderDao.createOrder(order);
    }

    @Override
    public void updatePayState(String order_id) {
        orderDao.updatePayState(order_id);
    }

    @Override
    public List<Order> findOrderByUser(Integer id) {
        return orderDao.findOrderByUser(id);
    }

    @Override
    public void delOrderById(String id, Integer flag, HttpSession session) {
        orderDao.delOrderById(id);
        if (flag == null){
            List<OrderItem> orderItems = orderItemService.findOrderItemById(id);
            for (OrderItem orderItem : orderItems) {
                productService.updatePnum(orderItem);
            }
        }
        //int i = 10/0;
        orderItemService.delOrderItemById(id);
    }
}
