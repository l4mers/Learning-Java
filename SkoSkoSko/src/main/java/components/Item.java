package components;

public class Item {

    private final StockProduct product;
    private final Order order;
    private int quantity;

    public Item(StockProduct product, Order order) {
        this.product = product;
        this.order = order;
        this.quantity = 1;
    }
    public Item(StockProduct product, Order order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return product.shoe().name() + " ("+ product.shoe().brand()+") x" + quantity + " " + product.shoe().price() * quantity;
    }

    public StockProduct getProduct() {
        return product;
    }

    public int getPrice(){
        return quantity * product.shoe().price();
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
        quantity--;
    }
}
