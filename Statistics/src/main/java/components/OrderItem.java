package components;

public record OrderItem(int quantity, Order order, Shoe shoe) {

    @Override
    public String toString() {
        return "OrderItem{" +
                "quantity=" + quantity +
                ", order=" + order.toString() +
                ", shoe=" + shoe.toString() +
                '}';
    }
}
