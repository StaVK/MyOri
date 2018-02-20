package ru.myori.web.order;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/op")
public class AjaxOrderProductController extends AbstractOrderProductController {
    @Override
    @DeleteMapping("/{id}")
    public void productDelete(@PathVariable("id") int id) {
        super.productDelete(id);
    }
}
