package ru.myori.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static ru.myori.model.AbstractBaseEntity.START_SEQ;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer customerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne
    @JoinColumn(name = "peopleId")
    private People people;

    public Customer() {
    }

    public Customer(People people, User user){
        this.people=people;
        this.user=user;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }



    public boolean isNew() {
        return this.customerId == null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", user=" + user +
                ", people=" + people +
                '}';
    }
}
