package ru.myori.web.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping(value = "/op")
public class jspOrderProductController extends AbstractOrderProductController {
    @GetMapping("/productDelete")
    public String productDelete(HttpServletRequest request) {
        super.productDelete(getId(request));
        return "orders";
    }

    @GetMapping("/getProductForOrder")
    public String addProductInOrder(Model model, @RequestParam(value = "orderId") int orderId) {
        model.addAttribute("products", super.getAllProduct());
        model.addAttribute("orderId", orderId);
        return "productsForOrder";
    }

    @GetMapping("/addProductInOrder")
    public String addProduct(@RequestParam(value = "prodId") int id, @RequestParam(value = "orderId") int orderId, @RequestParam(value = "volume") int vol, Model model) {
        Product product = super.get(id);
        Order order = super.getOrder(orderId);
        OrderProduct newOProduct;
        if((newOProduct=super.getOP(orderId,id))==null){
            newOProduct = new OrderProduct();
            newOProduct.setProduct(product);
            newOProduct.setOrder(order);
            newOProduct.setVolume(vol);
        }
        else{
            newOProduct.setVolume(newOProduct.getVolume()+vol);
        }
        super.create(newOProduct);
//        orderService.addProductInOrder(orderId, id);
//        orderService.update();
        model.addAttribute("order", order);
        model.addAttribute("orderProduct", super.getAllOP(orderId));
        return "orderForm";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
