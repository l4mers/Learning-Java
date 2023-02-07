package interfaces;

import components.Customer;
import components.Order;

public interface OrderCount {
    int countOrders(Customer customer, Order order);
}
