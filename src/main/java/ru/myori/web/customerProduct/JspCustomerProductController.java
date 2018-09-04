package ru.myori.web.customerProduct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/customerProducts")
public class JspCustomerProductController extends AbstractCustomerProductController {

    @GetMapping("/{customerId}")
    public String customerProducts(@PathVariable("customerId") int customerId, Model model) {
        model.addAttribute("customer", customerId);
//        model.addAttribute("customerProducts", super.getAll(cpId));
        return "customerProducts";
    }
}
