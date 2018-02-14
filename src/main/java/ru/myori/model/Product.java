package ru.myori.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"article"}))
public class Product extends AbstractBaseEntity {
    @Column(name = "article", nullable = false)
    private Integer article;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<Order> order;

    public Product() {
    }

    public Product(Integer article, String description, Double price) {
        this(null, article, description, price);
    }

    public Product(Integer id, Integer article, String description, Double price) {
        super(id);
        this.article = article;
        this.description = description;
        this.price = price;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
