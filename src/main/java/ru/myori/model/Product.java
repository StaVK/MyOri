package ru.myori.model;

import javax.persistence.*;
import java.util.List;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"article"}))
public class Product {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer prodId;

    @Column(name = "article", nullable = false)
    private Integer article;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    public Product() {
    }

    public Product(Integer article, String description, double price) {
        this.article = article;
        this.description = description;
        this.price = price;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
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


    public boolean isNew() {
        return this.prodId == null;
    }
}
