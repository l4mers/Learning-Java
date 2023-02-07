package interfaces;

import components.OrderItem;

@FunctionalInterface
public interface Search {
    boolean search(OrderItem orderItem, String s);
}
