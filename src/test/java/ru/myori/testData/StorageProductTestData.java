package ru.myori.testData;

import ru.myori.matcher.BeanMatcher;
import ru.myori.model.StorageProduct;

import java.util.*;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;
import static ru.myori.testData.ProductTestData.PRODUCT1;
import static ru.myori.testData.StorageTestData.STORAGE;

public class StorageProductTestData {

    public static final BeanMatcher<StorageProduct> MATCHER = BeanMatcher.of(StorageProduct.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getSpId(), actual.getSpId())
                            && Objects.equals(expected.getProduct().getProdId(), actual.getProduct().getProdId())
                            && Objects.equals(expected.getVolume(), actual.getVolume())
                            && Objects.equals(expected.getPrice(), actual.getPrice())
                            && Objects.equals(expected.getStorage().getStorageId(), actual.getStorage().getStorageId())
                    )
    );

    public static final int STORAGE_PRODUCT_ID = START_SEQ+15;
    public static final StorageProduct STORAGE_PRODUCT=new StorageProduct(STORAGE_PRODUCT_ID,PRODUCT1,5,1.1f, STORAGE);
    public static final List<StorageProduct> STORAGE_PRODUCT_LIST=Arrays.asList(STORAGE_PRODUCT);
}
