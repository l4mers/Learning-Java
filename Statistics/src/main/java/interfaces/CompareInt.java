package interfaces;

import components.OrderItem;
import components.Shoe;

@FunctionalInterface
public interface CompareInt {
    int topCount(OrderItem orderItem, Shoe shoe);
}
