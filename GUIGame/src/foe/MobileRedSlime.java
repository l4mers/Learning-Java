package foe;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MobileRedSlime extends Entity {

    public MobileRedSlime(GamePanel gamePanel){
        super(gamePanel);

        mobileType = 2;

        name = "Red Slime";
        speed = 1;
        maxHealth = 3;
        currentHealth = maxHealth;
        attack = 3;
        defense = 0;
        exp = 2;

        hitBox.x = 4;
        hitBox.y = 18;
        hitBox.width = 34;
        hitBox.height = 24;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        getImage();
    }
    public void getImage(){
        northOne = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        northTwo = setUp("/mobs/1", gamePanel.tileSize, gamePanel.tileSize);
        southOne = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        southTwo = setUp("/mobs/1", gamePanel.tileSize, gamePanel.tileSize);
        westOne = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        westTwo = setUp("/mobs/1", gamePanel.tileSize, gamePanel.tileSize);
        eastOne = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        eastTwo = setUp("/mobs/1", gamePanel.tileSize, gamePanel.tileSize);
        restEast = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        restNorth = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        restSouth = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        restWest = setUp("/mobs/0", gamePanel.tileSize, gamePanel.tileSize);
        deadSouth = setUp("/mobs/2", gamePanel.tileSize, gamePanel.tileSize);
    }
    public void setAction(){

        if (!dead){
            actionCounter++;

            if(actionCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(1, 126);

                if (i <= 25) {
                    direction = "north"; standStill = false;
                } else if (i <= 50) {
                    direction = "south"; standStill = false;
                } else if (i <= 75) {
                    direction = "west"; standStill = false;
                } else if (i <= 100) {
                    direction = "east"; standStill = false;
                } else {
                    standStill = true;
                }
                actionCounter = 0;
            }
        }
    }
    public void damageReaction(){
        actionCounter = 0;
        //super.turnMobileTowardsPlayer();
        direction = gamePanel.player.direction;

    }
}
