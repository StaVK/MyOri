package ru.myori.testData;

import ru.myori.matcher.BeanMatcher;
import ru.myori.model.StorageProduct;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;
import static ru.myori.testData.ProductTestData.PRODUCT1;
import static ru.myori.testData.StorageTestData.STORAGE;

public class StorageProductTestData {
    public static final BeanMatcher<StorageProduct> MATCHER = BeanMatcher.of(StorageProduct.class);

    public static final int STORAGE_PRODUCT_ID = START_SEQ+15;
    public static final StorageProduct STORAGE_PRODUCT=new StorageProduct(STORAGE_PRODUCT_ID,PRODUCT1,5,1.1f, STORAGE);
    public static final Set<StorageProduct> STORAGE_PRODUCT_SET=new HashSet<StorageProduct>(Collections.singleton(STORAGE_PRODUCT));
}
