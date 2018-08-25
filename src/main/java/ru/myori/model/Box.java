package ru.myori.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "boxes")
public class Box {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer boxId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Customer customer;

    @OneToMany
    @JoinColumn(name="storageId", insertable = false, updatable = false)
    private Set<Storage> storageSet;

    public Box() {
    }

    public Box(User user, Customer customer){
        this.user=user;
        this.customer=customer;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Storage> getStorageSet() {
        return storageSet;
    }

    public void setStorageSet(Set<Storage> storageSet) {
        this.storageSet = storageSet;
    }
}
