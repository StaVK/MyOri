package ru.myori.web.customer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.Customer;
import ru.myori.model.People;

import java.util.List;

@RestController
@RequestMapping("/ajax/customer")
public class AjaxCustomerController extends AbstractCustomerController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void createOrUpdate(People people){
        if (people.isNew()) {
            super.create(people);
        } else {
            super.update(people);
        }
    }
}
