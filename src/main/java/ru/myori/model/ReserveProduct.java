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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StorageProduct storageProduct;

    @ManyToOne
    @JoinColumn(name = "opId")
    private OrderProduct orderProduct;

    @Column(name = "reserveVolume")
    private int reserveVolume;

    public ReserveProduct() {
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
