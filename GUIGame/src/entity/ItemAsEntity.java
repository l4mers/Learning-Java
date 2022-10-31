package entity;

import items.Item;
import main.GamePanel;

public class ItemAsEntity extends Entity{
    public Item item;
    public ItemAsEntity(Item item) {
        super(item.gamePanel);
        this.item = item;
        restSouth = item.icon;
    }
}
