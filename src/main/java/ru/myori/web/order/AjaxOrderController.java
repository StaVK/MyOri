package ru.myori.web.order;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.Order;
import ru.myori.model.User;

import java.util.HashSet;
import java.util.List;

import static ru.myori.model.Order.ORDER_WORK;


@RestController
@RequestMapping("/ajax/order")
public class AjaxOrderController extends AbstractOrderController {

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int orderId) {
        super.delete(orderId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll() {
        return super.getAll();
    }

    @GetMapping("/chgCust")
    public User changeCustomer(@RequestParam("orderId") int orderId, @RequestParam("customerId") int customerId) {
        return super.chgCustomer(customerId, orderId);
    }

    @GetMapping("/setStatusWork")
    public void changeStatus(@RequestParam("orderId") int orderId) {
        super.changeStatus(orderId, ORDER_WORK);
    }

    @PostMapping("/create")
    public void create(@RequestParam("customerId") int customerId) {
        super.create(customerId);
    }
}
