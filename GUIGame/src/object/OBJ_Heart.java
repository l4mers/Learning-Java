package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "heart";

        restSouth = setUp("/obj_assets/heart0", gamePanel.tileSize, gamePanel.tileSize);
        southOne = setUp("/obj_assets/heart1", gamePanel.tileSize, gamePanel.tileSize);
        southTwo = setUp("/obj_assets/heart2", gamePanel.tileSize, gamePanel.tileSize);

    }
}
