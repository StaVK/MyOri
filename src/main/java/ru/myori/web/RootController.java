package ru.myori.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.myori.service.UserService;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "index";
    }
    @GetMapping("/users")
    public String users() {
        return "users";
    }
    @GetMapping("/products")
    public String products() {
        return "products";
    }
}
