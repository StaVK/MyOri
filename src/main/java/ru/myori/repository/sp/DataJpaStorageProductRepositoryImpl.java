package ru.myori.repository.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.StorageProduct;

import java.util.List;

@Repository
public class DataJpaStorageProductRepositoryImpl implements StorageProductRepository {

    @Autowired
    private CrudStorageProductRepository crudStorageProductRepository;

    @Override
    public List<StorageProduct> getAll(int storageId) {
        return crudStorageProductRepository.getAllByStorage(storageId);
    }

    @Override
    public StorageProduct save(StorageProduct storageProduct) {
        return crudStorageProductRepository.save(storageProduct);
    }

    @Override
    public StorageProduct getByArticle(int article, int storageId) {
        return crudStorageProductRepository.getByArticleAndStorage(article, storageId);
    }

    @Override
    public int update(StorageProduct storageProduct) {
        return crudStorageProductRepository.update(storageProduct.getSpId(),storageProduct.getVolume());
    }
}
