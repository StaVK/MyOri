package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "reserved_products")
public class ReserveProduct {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer rpId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "spId")
    private StorageProduct storageProduct;

    @ManyToOne
    @JoinColumn(name = "opId")
    private OrderProduct orderProduct;

    @Column(name = "reserveVolume")
    private int reserveVolume;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ReserveProduct() {
    }

    public ReserveProduct(StorageProduct storageProduct, OrderProduct orderProduct, int reserveVolume, User user) {
        this.storageProduct = storageProduct;
        this.orderProduct = orderProduct;
        this.reserveVolume = reserveVolume;
        this.user=user;
    }

    public ReserveProduct(StorageProduct storageProduct, OrderProduct orderProduct, long reserveVolume, User user) {
        this.storageProduct = storageProduct;
        this.orderProduct = orderProduct;
        this.reserveVolume = (int)reserveVolume;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StorageProduct getStorageProduct() {
        return storageProduct;
    }

    public void setStorageProduct(StorageProduct storageProduct) {
        this.storageProduct = storageProduct;
    }

    public Integer getRpId() {
        return rpId;
    }

    public void setRpId(Integer rpId) {
        this.rpId = rpId;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public int getReserveVolume() {
        return reserveVolume;
    }

    public void setReserveVolume(int reserveVolume) {
        this.reserveVolume = reserveVolume;
    }
}
