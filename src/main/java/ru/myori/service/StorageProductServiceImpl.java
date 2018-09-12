package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myori.model.Product;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;
import ru.myori.repository.product.ProductRepository;
import ru.myori.repository.sp.StorageProductRepository;
import ru.myori.repository.storage.StorageRepository;

import java.util.List;

@Service
public class StorageProductServiceImpl implements StorageProductService {

    private final StorageProductRepository storageProductRepository;

    private final ProductRepository productRepository;

    private final StorageRepository storageRepository;

    @Autowired
    public StorageProductServiceImpl(StorageProductRepository storageProductRepository, ProductRepository productRepository, StorageRepository storageRepository) {
        this.storageProductRepository = storageProductRepository;
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    public List<StorageProduct> getAll(int storageId) {
        return storageProductRepository.getAll(storageId);
    }

    @Override
    public StorageProduct save(StorageProduct storageProduct) {
        return storageProductRepository.save(storageProduct);
    }

    @Override
    public StorageProduct getByArticleAndPrice(int article, int storageId, float price) {
        return storageProductRepository.getByArticleAndPrice(article, storageId, price);
    }

    @Override
    public void createOrUpdate(int article, int storageId, int volume, float price, int userId) {
        StorageProduct storageProduct = getByArticleAndPrice(article, storageId, price);
        if (storageProduct != null) {
            storageProduct.setVolume(storageProduct.getVolume()+volume);
            storageProductRepository.update(storageProduct);
        } else {
            Product product = productRepository.getByArticle(article);
            Storage storage = storageRepository.get(storageId, userId);
            storageProduct = new StorageProduct(product, volume, price,storage);
            storageProductRepository.save(storageProduct);
        }
    }

    @Override
    public StorageProduct getFirstByArticle(int article, int storageId) {
        return storageProductRepository.getFirstByArticle(article, storageId);
    }
}