package ru.myori.testData;


import ru.myori.matcher.BeanMatcher;
import ru.myori.model.Product;

import java.util.Arrays;
import java.util.List;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

public class ProductTestData {

    public static final BeanMatcher<Product> MATCHER = BeanMatcher.of(Product.class);

    public static final int PRODUCT1_ID=START_SEQ+2;
    public static final Product PRODUCT1=new Product(PRODUCT1_ID,111,"Помада1",500);

    public static final int PRODUCT2_ID=START_SEQ+3;
    public static final Product PRODUCT2=new Product(PRODUCT2_ID,222,"Помада2",1000);

    public static final int PRODUCT3_ID=START_SEQ+4;
    public static final Product PRODUCT3=new Product(PRODUCT3_ID,333,"Туалетная вода",500);

    public static final int PRODUCT4_ID=START_SEQ+5;
    public static final Product PRODUCT4=new Product(PRODUCT4_ID,444,"Тушь",500);

    public static final int PRODUCT5_ID=START_SEQ+6;
    public static final Product PRODUCT5=new Product(PRODUCT5_ID,555,"Пена для бритья",1000);

    public static final int PRODUCT6_ID=START_SEQ+7;
    public static final Product PRODUCT6=new Product(PRODUCT6_ID,666,"Лак для ногтей",510);

    public static final int PRODUCT7_ID=START_SEQ+8;
    public static final Product PRODUCT7=new Product(PRODUCT7_ID,777,"Туалетная вода 2",510);

    public static final int PRODUCT8_ID=START_SEQ+9;
    public static final Product PRODUCT8=new Product(PRODUCT8_ID,888,"Дезодорант",1500);

    public static final List<Product> PRODUCT_LIST =Arrays.asList(PRODUCT1,PRODUCT2,PRODUCT3,PRODUCT4,PRODUCT5,PRODUCT6,PRODUCT7,PRODUCT8);
}
