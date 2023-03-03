package components;


import main.Program;
import java.util.ArrayList;

public class Cart {
    final private Program p;
    private Order order;
    private ArrayList<Item> itemList;

    public Cart(Program p) {
        this.p = p;
        itemList = new ArrayList<>();
    }
    public boolean orderExist(){
        return order != null;
    }

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}
    public void addItem(Item item) {
        this.itemList.add(item);
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
    public int createOrder(){
        return p.getDh().callAddToCard(itemList.get(0), order);
    }
    public void sendItemList(){
        itemList.stream().skip(1).toList().forEach(e-> p.getDh().callAddToCard(e, order));
    }
    public void emptyCart() {
        order = null;
        itemList = new ArrayList<>();
    }
}
