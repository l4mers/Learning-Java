package object;

import entity.Entity;
import items.Item;
import items.Key;
import main.GamePanel;

public class OBJ_Key extends Entity {
    public Item item;
    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        item = new Key(gamePanel);
        restSouth = item.icon;
    }
}
