package ru.myori.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.Set;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer storageId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "storage")
    private Set<StorageProduct> products;

    public Storage() {
    }

    public Storage(Storage storage){
        this(storage.getStorageId(),storage.getName(),storage.getUser(),storage.getProducts());
    }

    public Storage(String name, User user, Set<StorageProduct> products) {
        this(null,name,user, products);
    }

    public Storage(Integer storageId, String name, User user, Set<StorageProduct> products) {
        this.storageId=storageId;
        this.name = name;
        this.user = user;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<StorageProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<StorageProduct> products) {
        this.products = products;
    }

    public boolean isNew() {
        return getStorageId() == null;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
                ", name='" + name + '\'' +
                ", user=" + user +
//                ", products=" + products +
                '}';
    }
}
