package com.abc.bookstore.client.product.service;

import com.abc.bookstore.client.product.dao.ProductDao;
import com.abc.bookstore.commons.beans.Order;
import com.abc.bookstore.commons.beans.OrderItem;
import com.abc.bookstore.commons.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findProductByCategory(String category) {
        return productDao.findProductByCategory(category);
    }

    @Override
    public Product findProductById(String id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> MenuSearchSerlvet(String name) {
        return productDao.MenuSearchSerlvet(name);
    }

    @Override
    public void updateProductPnum(OrderItem orderItem) {
        productDao.updateProductPnum(orderItem);
    }

    @Override
    public void updatePnum(OrderItem orderItem) {
        productDao.updatePnum(orderItem);
    }

    @Override
    public List<Product> findHotProduct() {
        return productDao.findHotProduct();
    }
}
