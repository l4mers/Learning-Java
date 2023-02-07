package interfaces;

import components.OrderItem;

@FunctionalInterface
public interface CompareString {
    int topCounty(OrderItem orderItem,String string);
}
