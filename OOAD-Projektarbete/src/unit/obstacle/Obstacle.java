package unit.obstacle;

import main.GamePanel;
import main.Utility;
import unit.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Obstacle extends Unit {

    private final int defaultY;

    public Obstacle(int size, int defaultY){
        image = new Utility().loadImage("meteorite_obstacle", size, size);
        this.size = size;

        this.defaultY = defaultY;
        positionY = defaultY;
        //setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 15;
        hitBox.width = 40;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
    public void setObstacle(int screenX, int speed){
        positionX = screenX;
        this.speed = speed;
        alive = true;
    }
    public void update(GamePanel gp){
        if (alive){
            if(positionY >= gp.height){
                reset();
            } else {
                positionY += speed;
            }
            updateHitBox();

            if(gp.collisionHandler.checkObstacle(this)){
                if(loot()){
                    lootID = getRandomLootID();
                    image = gp.obstacleFactory.getLootImage(lootID, gp.size, gp.size);
                    hasLoot = true;
                } else {
                    reset();
                }
            }
        }
    }

    private int getRandomLootID() {
        Random rng = new Random();
        return rng.nextInt(1, 5);
    }

    public void reset(){
        alive = false;
        hasLoot = false;
        positionY = defaultY;
        image = defaultImage;
        lootID = 0;
    }
    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
        }
    }
    private boolean loot(){
        Random rng = new Random();
        int randomNr = rng.nextInt(1, 101);
        return randomNr < 11;
    }
    public void setImage(BufferedImage lootImage){
        image = lootImage;
    }
    public int getLootID() {
        return lootID;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
