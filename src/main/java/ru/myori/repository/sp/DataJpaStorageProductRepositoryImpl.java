package ru.myori.repository.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.myori.model.StorageProduct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public StorageProduct getByArticleAndPrice(int article, int storageId, float price) {
        return crudStorageProductRepository.getByArticleAndPrice(article, storageId, price);
    }

    @Override
    public int update(StorageProduct storageProduct) {
        return crudStorageProductRepository.update(storageProduct.getSpId(), storageProduct.getVolume());
    }

    @Override
    public boolean delete(int spId) {
        return crudStorageProductRepository.delete(spId) != 0;
    }

    @Override
    public StorageProduct getFirstByArticle(int article, int storageId) {
        return crudStorageProductRepository.getByArticleAndStorageId(article, storageId);
    }

    @Override
    public Set<StorageProduct> getAllByArticle(int article, int storageId) {
        return new HashSet<>(crudStorageProductRepository.findAllByProduct_articleAndStorage_StorageId(article, storageId));
    }


    @Override
    public List<StorageProduct> getAllByArticleAndUser(int article, int userId) {
        return crudStorageProductRepository.findAllByProduct_articleAndStorage_UserId(article, userId);
    }


    @Override
    public Long getFreeVolume(int article, int userId) {
        return crudStorageProductRepository.getFreeVolume(article, userId);
    }

    @Override
    public List<StorageProduct> getAllWithFreeVolume(int article, int userId) {
        return crudStorageProductRepository.getAllWithFreeVolume(article, userId);
    }
}
