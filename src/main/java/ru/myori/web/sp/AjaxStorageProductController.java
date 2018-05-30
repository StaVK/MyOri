package ru.myori.web.sp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.StorageProduct;

import java.util.List;

@RestController
@RequestMapping("/ajax/sp")
public class AjaxStorageProductController extends AbstractStorageProductController {


    @GetMapping(value = "/{storageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StorageProduct> getProducts(@PathVariable("storageId") int storageId) {
        List<StorageProduct> tmp=super.getProducts(storageId);
        return tmp;
    }

    @PostMapping
    public void createOrUpdate(
            @RequestParam("article") int article,
            @RequestParam("storageId") int storageId,
            @RequestParam("volume") int volume,
            @RequestParam("price") float price) {
        super.createOrUpdate(article, storageId, volume, price);


    }


}
