package components;

import java.time.LocalDate;

public class Order {
    private int id = 0;
    private final LocalDate date;
    private final Customer customer;

    public Order(Customer customer, LocalDate date) {
        this.customer = customer;
        this.date = date;
    }
    public Order(int id, Customer customer, LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order made by " + customer.getName() + " on " + date.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }
}
