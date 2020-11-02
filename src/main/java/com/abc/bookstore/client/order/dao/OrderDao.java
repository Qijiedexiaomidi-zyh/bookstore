package com.abc.bookstore.client.order.dao;

import com.abc.bookstore.commons.beans.Order;

import java.util.List;

public interface OrderDao {

    void createOrder(Order order);

    void updatePayState(String order_id);

    List<Order> findOrderByUser(Integer id);

    void delOrderById(String id);

}
