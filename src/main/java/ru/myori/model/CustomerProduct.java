package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "customer_products")
public class CustomerProduct {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer cpId;

    @Column(name="volume")
    private int volume;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customerId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    public CustomerProduct() {
    }

    public CustomerProduct(BoxProduct boxProduct, Customer customer){
        this.volume=boxProduct.getVolume();
        this.product=boxProduct.getProduct();
        this.customer=customer;
    }

    public Integer getCpId() {
        return cpId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    @Override
    public String toString() {
        return "CustomerProduct{" +
                "cpId=" + cpId +
                ", volume=" + volume +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
