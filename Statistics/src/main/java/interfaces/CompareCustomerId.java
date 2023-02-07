package interfaces;

import components.Customer;
import components.OrderItem;

@FunctionalInterface
public interface CompareCustomerId {
    int compare(OrderItem orderItem, Customer customer);
}
