package com.abc.bookstore.admin.products.dao;

import com.abc.bookstore.commons.beans.Product;
import com.abc.bookstore.commons.beans.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductsDao {

    List<Product> listProduct();

    List<Product> findProductByManyCondition(@Param("product") Product product, @Param("maxprice") Double maxprice, @Param("minprice") Double minprice);

    void addProduct(Product product);

    Product findProductById(String id);

    void editProduct(Product product);

    void deleteProduct(String id);

    List<ProductVo> findSalenum(@Param("year") String year, @Param("month") String month);
}
