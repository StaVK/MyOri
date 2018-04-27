package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Repository;
import ru.myori.repository.reports.SummaryOrderRepository;

import javax.persistence.*;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "order_products")
public class OrderProduct{



    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer opId;

    @Column(name="volume")
    private int volume;

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

    public OrderProduct() {
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
}
