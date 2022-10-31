package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Treasure extends Entity {
    public OBJ_Treasure(GamePanel gamePanel) {

        super(gamePanel);

        name = "treasure";

        restSouth = setUp("/objects/treasure", gamePanel.tileSize, gamePanel.tileSize);
    }
}
