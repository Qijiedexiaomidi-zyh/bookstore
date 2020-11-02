package com.abc.bookstore.client.order.service;

import com.abc.bookstore.client.order.dao.OrderItemDao;
import com.abc.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void save(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return orderItemDao.findOrderItemById(id);
    }

    @Override
    public OrderItem delOrderItemById(String id) {
        orderItemDao.delOrderItemById(id);
        return null;
    }
}
