package com.abc.bookstore.client.order.service;

import com.abc.bookstore.commons.beans.OrderItem;

import java.util.List;

public interface OrderItemService {

    void save(OrderItem orderItem);

    List<OrderItem> findOrderItemById(String id);

    OrderItem delOrderItemById(String id);
}
