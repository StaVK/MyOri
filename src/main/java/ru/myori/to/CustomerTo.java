package ru.myori.to;

import ru.myori.model.Customer;
import ru.myori.model.People;

public class CustomerTo extends People {
    private int customerId;
    public CustomerTo(Customer customer) {
        super(customer.getPeople().getName(),customer.getPeople().getSurname(),customer.getPeople().getPatronymic(), customer.getPeople().getPhoneNumber());
        this.customerId=customer.getCustomerId();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
