package ru.myori.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.service.*;

public abstract class AbstractController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    ProductService productService;

//    @Autowired
//    SummaryOrderService summaryOrderService;
}
