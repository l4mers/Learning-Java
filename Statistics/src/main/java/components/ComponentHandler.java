package components;


import database.DatabaseHandler;

import java.util.List;

public class ComponentHandler {
    private final List<String> colors;
    private List<OrderItem> orderItems;

    public ComponentHandler(DatabaseHandler p) {
        colors = p.getColors();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItemList(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<String> getColors() {
        return colors;
    }
}
