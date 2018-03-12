package ru.myori.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.myori.service.OrderService;
import ru.myori.service.ProductService;
import ru.myori.service.UserService;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String root() {
        return "index";
    }
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }
    @GetMapping("/orders")
    public String orders() {
//        model.addAttribute("orders", orderService.getAll());
        return "orders";
    }
}
