package com.abc.bookstore.client.order.dao;

import com.abc.bookstore.commons.beans.OrderItem;

import java.util.List;

public interface OrderItemDao {

    void save(OrderItem orderItem);

    List<OrderItem> findOrderItemById(String id);

    void delOrderItemById(String id);
}
