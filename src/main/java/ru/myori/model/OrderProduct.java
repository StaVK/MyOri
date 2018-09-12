package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "order_products")
public class OrderProduct{

    public static final int ORDER_PRODUCT_INORDER=0;
    public static final int ORDER_PRODUCT_WORK=1;
    public static final int ORDER_PRODUCT_CLIENT =2;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer opId;

    @Column(name="volume")
    private int volume;

    @Column(name="executedVolume")
    private int executedVolume;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    /*@OneToOne
    @JoinColumn(name = "moneyId")
    private Money price; //TODO Заняться ценой*/

    @Column(name="money")
    private Money money;

    @NotNull
    private int status;

    public OrderProduct() {
    }

    public OrderProduct(OrderProduct orderProduct) {
        this.opId=orderProduct.getOpId();
        this.volume=orderProduct.getVolume();
        this.executedVolume=orderProduct.getExecutedVolume();
        this.order=orderProduct.getOrder();
        this.product=orderProduct.getProduct();
        this.status=orderProduct.getStatus();
    }

    public OrderProduct(Order order, Product product, int volume){
        this.order=order;
        this.product=product;
        this.volume=volume;
    }

    public OrderProduct(Product product, long volume){
        this.product=product;
        this.volume=(int)volume;
    }

    public int getExecutedVolume() {
        return executedVolume;
    }

    public void setExecutedVolume(int executedVolume) {
        this.executedVolume = executedVolume;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderProduct(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    /*    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }*/
}
