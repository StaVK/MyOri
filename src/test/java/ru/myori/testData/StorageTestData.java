package ru.myori.testData;

import ru.myori.matcher.BeanMatcher;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;

import java.util.*;


import static ru.myori.model.AbstractBaseEntity.START_SEQ;
import static ru.myori.testData.ProductTestData.PRODUCT1;
import static ru.myori.testData.UserTestData.USER;

public class StorageTestData {

//    public static final BeanMatcher<Storage> MATCHER = BeanMatcher.of(Storage.class);

    public static final BeanMatcher<Storage> MATCHER = BeanMatcher.of(Storage.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getStorageId(), actual.getStorageId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getUser().getId(), actual.getUser().getId())
                    )
    );


    public static final int STORAGE_ID = START_SEQ+14;
    public static final Storage STORAGE=new Storage(STORAGE_ID,"Главный склад",USER,new HashSet<>());

}
