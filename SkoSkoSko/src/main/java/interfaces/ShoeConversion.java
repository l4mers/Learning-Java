package interfaces;

import components.Shoe;
import components.StockProduct;

@FunctionalInterface
public interface ShoeConversion {
     StockProduct conversion(Shoe shoe, int size, String color);
}
