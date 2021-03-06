package ru.myori.web.order;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.*;
import ru.myori.to.OrderProductTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/ajax/op")
public class AjaxOrderProductController extends AbstractOrderProductController {
    @Override
    @DeleteMapping("/{id}")
    public void productDelete(@PathVariable("id") int id) {
        super.productDelete(id);
    }

/*    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getAll(@PathVariable("orderId") int orderId) {
        return super.getAllOP(orderId);
    }*/

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProductTo> getAllTo(@PathVariable("orderId") int orderId) {
        return super.getAllOP(orderId);
    }
/*    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getAll() {
        return super.getAll();
    }*/

    @GetMapping(value = "/summary",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderProduct> getSummary() {
        return super.getSummary();
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
    public void editVolume(@RequestParam(value = "opId") int opId, @RequestParam(value = "volume") int volume) {
        super.update(opId, volume);
    }
}
