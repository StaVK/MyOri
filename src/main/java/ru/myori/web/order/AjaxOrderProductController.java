package ru.myori.web.order;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;
import ru.myori.model.SummaryOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ajax/op")
public class AjaxOrderProductController extends AbstractOrderProductController {
    @Override
    @DeleteMapping("/{id}")
    public void productDelete(@PathVariable("id") int id) {
        super.productDelete(id);
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getAll(@PathVariable("orderId") int orderId) {
        return super.getAllOP(orderId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void addProductInOrder(@RequestParam(value = "article") int article, @RequestParam(value = "volume") int volume, @RequestParam(value = "orderId") int orderId) {

        Product product = super.getProductByArticle(article);
        Order order = super.getOrder(orderId);
        OrderProduct newOProduct;
        if ((newOProduct = super.getOP(orderId, product.getProdId())) == null) {
            newOProduct = new OrderProduct();
            newOProduct.setProduct(product);
            newOProduct.setOrder(order);
            newOProduct.setVolume(volume);
        } else {
            newOProduct.setVolume(newOProduct.getVolume() + volume);
        }
        super.create(newOProduct);
    }

    @PostMapping("/editV")
    public void editVolume(@RequestParam(value = "article") int article, @RequestParam(value = "volume") int volume, @RequestParam(value = "orderId") int orderId) {
        super.update(orderId, article, volume);
    }
}
