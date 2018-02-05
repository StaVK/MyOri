package ru.myori.model;

import javax.persistence.Column;
import java.util.List;

public class Order extends AbstractBaseEntity {

    @Column(name = "products", nullable = false)
    private List<Product> products;

    @Column(name = "user", nullable = false)
    private User user;

    @Column(name = "forUser", nullable = false)
    private User forUser;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getForUser() {
        return forUser;
    }

    public void setForUser(User forUser) {
        this.forUser = forUser;
    }
}
