package ru.myori.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.service.OrderProductService;
import ru.myori.service.OrderService;
import ru.myori.service.ProductService;
import ru.myori.service.UserService;

public abstract class AbstractController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    ProductService productService;
}
