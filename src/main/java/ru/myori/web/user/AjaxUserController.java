package ru.myori.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.Customer;

import java.util.Set;

@RestController
@RequestMapping("/ajax/users")
public class AjaxUserController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Customer> getCustomers() {
        return super.getCustomers();
    }
}
