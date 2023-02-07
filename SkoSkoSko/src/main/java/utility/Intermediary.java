package utility;

import components.Item;
import components.Order;
import components.Shoe;
import interfaces.ShoeConversion;

public class Intermediary {

    public Item stockProductToItem(ShoeConversion sc, Order o, Shoe shoe, int size, String color){
        return new Item(sc.conversion(shoe, size, color), o);
    }
}
