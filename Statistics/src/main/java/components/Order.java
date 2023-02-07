package components;

import java.time.LocalDate;

public class Order {
    private final int id;
    private final Customer customer;
    private final LocalDate date;


    public Order(int id, Customer customer, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "";
    }
}
