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

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderProductService orderProductService;

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "orders";
    }

    /*    @GetMapping("/update")
        public String update(HttpServletRequest request, Model model) {
            model.addAttribute("meal", super.get(getId(request)));
            return "mealForm";
        }*/
    @GetMapping("/getProductForOrder")
    public String addProductInOrder(Model model, @RequestParam(value = "orderId") int orderId) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("orderId", orderId);
        return "productsForOrder";
    }

    @GetMapping("/addProductInOrder")
    public String addProduct(@RequestParam(value = "id") int id, @RequestParam(value = "orderId") int orderId, Model model) {
/*        int id = Integer.valueOf(request.getParameter("id"));
        int orderId = Integer.valueOf(request.getParameter("orderId"));*/
        Product product = productService.get(id);
        Order order=orderService.get(orderId);
        OrderProduct newOProduct=new OrderProduct();
        newOProduct.setProduct(product);
        newOProduct.setOrder(order);
        newOProduct.setVolume(3);
        orderProductService.create(newOProduct);
//        orderService.addProductInOrder(orderId, id);
//        orderService.update();
        model.addAttribute("order", order);
        model.addAttribute("orderProduct", orderProductService.getAll());
        return "orderForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Order order=new Order();
        order.setProducts(new HashSet<>());
        order.setUser(userService.get(100000));
        order.setForUser(userService.get(100001));

        model.addAttribute("order", orderService.create(order));
        return "orderForm";
    }

/*    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Product product = new Product(Integer.valueOf(request.getParameter("article")),
                request.getParameter("description"),
                Double.valueOf(request.getParameter("price")));

        if (request.getParameter("id").isEmpty()) {
            super.create(product);
        } else {
            super.update(product, getId(request));
        }
        return "redirect:/products";
    }*/

/*    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }*/

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
