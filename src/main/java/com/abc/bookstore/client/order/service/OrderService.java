package com.abc.bookstore.client.order.service;

import com.abc.bookstore.commons.beans.Order;
import com.abc.bookstore.commons.beans.Product;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface OrderService {

    void createOrder(Order order, Map<Product, Integer> cart, HttpSession session);

    void updatePayState(String order_id);

    List<Order> findOrderByUser(Integer id);

    void delOrderById(String id, Integer flag, HttpSession session);

}
