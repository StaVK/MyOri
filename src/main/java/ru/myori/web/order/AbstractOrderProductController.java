package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.myori.AuthorizedUser;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;
import ru.myori.model.SummaryOrder;

import java.util.List;
import java.util.Set;

public class AbstractOrderProductController extends AbstractController{
    private final Logger log = LoggerFactory.getLogger(getClass());
    //private final int userId=100000;

    public Product getProductByArticle(int article){
        return productService.getByArticle(article);
    }

    public void productDelete(int prodId) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", prodId, userId);
        orderProductService.delete(prodId);
    }

    public List<Product> getAllProduct(){
        int userId = AuthorizedUser.id();
        log.info("getAll products for User {}", userId);
        return productService.getAll();
    }

    public Product get(int id){
        int userId = AuthorizedUser.id();
        log.info("get product for User {}", userId);
        return productService.get(id, userId);
    }

    public Order getOrder(int id){
        int userId = AuthorizedUser.id();
        log.info("get order for User {}", userId);
        return orderService.get(id,userId);
    }

    public OrderProduct getOP(int orderId, int id){
        int userId = AuthorizedUser.id();
        log.info("get order product for User {}", userId);
        return orderProductService.getProd(orderId,id);
    }

    public OrderProduct create(OrderProduct orderProduct){
        int userId = AuthorizedUser.id();
        log.info("create order product for User {}", userId);
        return orderProductService.create(orderProduct);
    }

    public List<OrderProduct> getAllOP(int orderId){
        int userId = AuthorizedUser.id();
        log.info("getAll products for User {}", userId);
        return orderProductService.getAll(orderId);
    }

    public List<OrderProduct> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll products for User {}", userId);
        return orderProductService.getAll();
    }

    public int update(int orderId, int article, int volume){
        int userId = AuthorizedUser.id();
        log.info("update order product for User {}", userId);
        return orderProductService.update(orderId,article,volume);
    }
}
