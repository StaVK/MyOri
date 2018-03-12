package ru.myori.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;
import ru.myori.service.OrderProductService;
import ru.myori.service.OrderService;
import ru.myori.service.ProductService;
import ru.myori.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Controller
@RequestMapping(value = "/orders")
public class JspOrderController extends AbstractOrderController {

    @GetMapping("/orderDelete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "orders";
    }

    @GetMapping("/orderUpdate")
    public String update(@RequestParam(value = "orderId") int orderId, Model model) {
        Order order = super.get(orderId);
        model.addAttribute("order", order);
        //model.addAttribute("orderProduct", super.orderProductService.getAll(orderId));
        return "orderForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Order order = new Order();
        order.setProducts(new HashSet<>());
        order.setUser(super.getUser(100000));
        order.setForUser(super.getUser(100001));

        model.addAttribute("order", super.create(order));
        return "orderForm";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
