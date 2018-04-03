package ru.myori.web.storage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ajax/storage")
public class AjaxStorageController extends AbstractStorageController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Storage> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/products/{storageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StorageProduct> getProducts(@PathVariable("storageId") int storageId) {
        return super.getProducts(storageId);
    }

    @GetMapping("/{storageId}")
    public Storage get(@PathVariable("storageId") int storageId){
        return super.get(storageId);
    }

    @PostMapping
    public void createOrUpdate(Storage storage){
        if (storage.isNew()) {
            super.create(storage);
        } else {
            super.update(storage.getStorageId(), storage);
        }
    }
}
