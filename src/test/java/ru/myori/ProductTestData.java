package ru.myori;


import ru.myori.model.Product;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

public class ProductTestData {

    //public static final BeanMatcher<Product> MATCHER = BeanMatcher.of(Product.class);

    public static final int PRODUCT_ID=START_SEQ+2;
    public static final Product PRODUCT=new Product(111,"Помада1",500);

}
