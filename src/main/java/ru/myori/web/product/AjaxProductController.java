package ru.myori.web.product;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.Product;

import java.util.List;

@RestController
@RequestMapping("/ajax/product")
public class AjaxProductController extends AbstractProductController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return super.getAll();
    }
}
