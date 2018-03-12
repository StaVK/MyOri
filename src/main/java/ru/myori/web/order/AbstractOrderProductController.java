package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;
import ru.myori.service.OrderProductService;
import ru.myori.service.OrderService;
import ru.myori.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AbstractOrderProductController extends AbstractController{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int userId=100000;

    public Product getProductByArticle(int article){
        return productService.getByArticle(article);
    }

    public void productDelete(int prodId) {
//        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", prodId, userId);
        orderProductService.delete(prodId);
    }

    public List<Product> getAllProduct(){
        log.info("getAll products for User {}", userId);
        return productService.getAll();
    }

    public Product get(int id){
        log.info("get product for User {}", userId);
        return productService.get(id);
    }

    public Order getOrder(int id){
        log.info("get order for User {}", userId);
        return orderService.get(id);
    }

    public OrderProduct getOP(int orderId, int id){
        log.info("get order product for User {}", userId);
        return orderProductService.getProd(orderId,id);
    }

    public OrderProduct create(OrderProduct orderProduct){
        log.info("create order product for User {}", userId);
        return orderProductService.create(orderProduct);
    }

    public Set<OrderProduct> getAllOP(int orderId){
        log.info("getAll products for User {}", userId);
        return orderProductService.getAll(orderId);
    }

    public int update(int orderId, int article, int volume){
        log.info("update order product for User {}", userId);
        return orderProductService.update(orderId,article,volume);
    }
}
