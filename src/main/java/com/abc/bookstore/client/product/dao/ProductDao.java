package com.abc.bookstore.client.product.dao;

import com.abc.bookstore.commons.beans.Order;
import com.abc.bookstore.commons.beans.OrderItem;
import com.abc.bookstore.commons.beans.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findProductByCategory(String category);

    Product findProductById(String id);

    List<Product> MenuSearchSerlvet(String name);

    void updateProductPnum(OrderItem orderItem);

    void updatePnum(OrderItem orderItem);

    List<Product> findHotProduct();
}
