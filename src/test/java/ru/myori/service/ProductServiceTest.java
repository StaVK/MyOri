package ru.myori.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.myori.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.myori.Profiles.DATAJPA;
import static ru.myori.testData.ProductTestData.*;

@ActiveProfiles(DATAJPA)
public class ProductServiceTest extends AbstractServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void get() {
        Product product = productService.get(PRODUCT1_ID);
        MATCHER.assertEquals(PRODUCT1, product);
    }

    @Test
    public void delete() {
        productService.delete(PRODUCT1_ID, 1);
        List<Product> productsList = Arrays.asList(PRODUCT2, PRODUCT3, PRODUCT4, PRODUCT5, PRODUCT6, PRODUCT7, PRODUCT8);
        MATCHER.assertListEquals(productsList, productService.getAll());
    }

    @Test
    public void getAll() {
        List<Product> productsList = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3, PRODUCT4, PRODUCT5, PRODUCT6, PRODUCT7, PRODUCT8);
        MATCHER.assertListEquals(productsList,productService.getAll());
    }

    @Test
    public void update() {
        Product updated = new Product(PRODUCT1);
        updated.setDescription("UpdatedDescription");
        updated.setArticle(PRODUCT1.getArticle()+1);
        productService.update(updated);
        MATCHER.assertEquals(updated, productService.get(PRODUCT1_ID));
    }

    @Test
    public void create() {
        Product newProduct=new Product(124,"New product", 5.2);
        Product created=productService.create(newProduct);
        newProduct.setProdId(created.getProdId());
        List<Product> productList=new ArrayList<>(PRODUCT_LIST);
        productList.add(newProduct);
        MATCHER.assertListEquals(productList,productService.getAll());
    }

    @Test
    public void getByArticle() {
        Product actual = productService.getByArticle(PRODUCT1.getArticle());
        MATCHER.assertEquals(PRODUCT1, actual);
    }
}