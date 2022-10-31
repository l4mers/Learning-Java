package object;

import entity.Entity;
import items.Food;
import main.GamePanel;

public class OBJ_Food extends Entity {
    public Food item;
    public OBJ_Food(GamePanel gamePanel) {
        super(gamePanel);
        item = new Food(gamePanel);
    }
}
