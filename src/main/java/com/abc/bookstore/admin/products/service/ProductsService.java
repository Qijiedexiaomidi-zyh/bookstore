package com.abc.bookstore.admin.products.service;

import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.ProductVo;

import java.util.List;

public interface ProductsService {

    List<Product> listProduct();

    List<Product> findProductByManyCondition(Product product, Double maxprice, Double minprice);

    void addProduct(Product product);

    Product findProductById(String id);

    void editProduct(Product product);

    void deleteProduct(String id);

    List<ProductVo> findSalenum(String year, String month);
}
