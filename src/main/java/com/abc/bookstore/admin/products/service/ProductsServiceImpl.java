package com.abc.bookstore.admin.products.service;

import com.abc.bookstore.admin.products.dao.ProductsDao;
import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService{

    @Autowired
    private ProductsDao productsDao;

    @Override
    public List<Product> listProduct() {
        return productsDao.listProduct();
    }

    @Override
    public List<Product> findProductByManyCondition(Product product, Double maxprice, Double minprice) {
        return productsDao.findProductByManyCondition(product,maxprice,minprice);
    }

    @Override
    public void addProduct(Product product) {
        productsDao.addProduct(product);
    }

    @Override
    public Product findProductById(String id) {
        return productsDao.findProductById(id);
    }

    @Override
    public void editProduct(Product product) {
        productsDao.editProduct(product);
    }

    @Override
    public void deleteProduct(String id) {
        productsDao.deleteProduct(id);
    }

    @Override
    public List<ProductVo> findSalenum(String year, String month) {
        return productsDao.findSalenum(year,month);
    }
}
