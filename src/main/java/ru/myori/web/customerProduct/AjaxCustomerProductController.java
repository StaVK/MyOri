package ru.myori.web.customerProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.CustomerProduct;

import java.util.Set;

@RestController
@RequestMapping("/ajax/cp")
public class AjaxCustomerProductController extends AbstractCustomerProductController {

    @GetMapping(value = "/{customerId}")
    public Set<CustomerProduct> getAll(@PathVariable("customerId") int customerId) {
        return super.getAll(customerId);
    }
}
