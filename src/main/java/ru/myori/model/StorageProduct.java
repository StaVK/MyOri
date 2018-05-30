package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "storage_products")
public class StorageProduct {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer spId;

    //    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Column(name = "volume")
    private int volume;

    @Column(name = "price")
    private float price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "storageId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Storage storage;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "storageProduct")
    private Set<ReserveProduct> reserve;

    public StorageProduct() {
    }

    public StorageProduct(Product product, int volume, float price, Storage storage) {
        this(null, product, volume, price, storage);
    }

    public StorageProduct(Integer spId, Product product, int volume, float price, Storage storage) {
        this.spId = spId;
        this.product = product;
        this.volume = volume;
        this.price = price;
        this.storage = storage;
    }


    public Set<ReserveProduct> getReserve() {
        return reserve;
    }

    public void setReserve(Set<ReserveProduct> reserve) {
        this.reserve = reserve;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean isNew() {
        return getSpId() == null;
    }


    @Override
    public String toString() {
        return "StorageProduct{" +
                "spId=" + spId +
                ", product=" + product +
                ", volume=" + volume +
                ", price=" + price +
                ", storage=" + storage +
                ", reserve=" + reserve +
                '}';
    }
}
