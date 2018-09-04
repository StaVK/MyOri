package ru.myori.web.customer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @Override
    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable("customerId") int customerId) {
        super.delete(customerId);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public People getPeople(@PathVariable("id") int id) {
        return super.getPeople(id);
    }
}
