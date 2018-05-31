package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.myori.AuthorizedUser;
import ru.myori.model.Order;
import ru.myori.model.OrderProduct;
import ru.myori.model.Product;
import ru.myori.to.OrderProductTo;

import java.util.List;

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

    public List<OrderProductTo> getAllOP(int orderId){
        int userId = AuthorizedUser.id();
        log.info("getAll products for User {}", userId);
        return orderProductService.getAll(orderId, userId);
    }

/*    public List<OrderProduct> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll products for User {}", userId);
        return orderProductService.getAll(userId);
    }*/

    public List<OrderProduct> getSummary(){
        int userId = AuthorizedUser.id();
        log.info("getSummary products for User {}", userId);
        return orderProductService.getAllForSummary(userId);
    }

    public int update(int opId, int volume){
        int userId = AuthorizedUser.id();
        log.info("update order product for User {}", userId);
        return orderProductService.update(opId,volume);
    }
}
