package ru.myori.web.product;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.Product;

import java.util.List;

@RestController
@RequestMapping("/ajax/product")
public class AjaxProductController extends AbstractProductController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(Product product){
        if (product.isNew()) {
            super.create(product);
        } else {
            super.update(product);
        }
    }

    @GetMapping("/{prodId}")
    public Product get(@PathVariable("prodId") int prodId){
        return super.get(prodId);
    }
}
