package com.abc.bookstore.commons.beans;

import lombok.Data;

@Data
public class OrderItem {

    private Product product;
    private Order order;
    private int buynum;
}
