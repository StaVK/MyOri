package ru.myori.web.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.myori.model.Order;

import javax.servlet.http.HttpServletRequest;
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
        return "orderForm";
    }



    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
