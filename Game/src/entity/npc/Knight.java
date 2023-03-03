package entity.npc;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Knight extends Entity {
    public Knight(GamePanel gp) {
        super(gp);

        direction = "south";
        speed = 1;

        //HIT BOX
        hitBox.x = 28;
        hitBox.y = gp.playerSize / 4;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 40;
        hitBox.height = 40;
        
        setImage();
    }

    public void setAction(){
        standardWalkReaction();
    }
    public BufferedImage walkAnimation(){
        switch (direction) {
            case "north" -> {
                if (spriteCount < 12) {
                    return getSprite(spriteSheet, 4, 6);
                } else if (spriteCount < 24) {
                    return getSprite(spriteSheet, 5, 6);
                } else if (spriteCount < 36) {
                    return getSprite(spriteSheet, 6, 6);
                } else {
                    return getSprite(spriteSheet, 5, 6);
                }
            }
            case "south" -> {
                if (spriteCount < 12) {
                    return getSprite(spriteSheet, 1, 5);
                } else if (spriteCount < 24) {
                    return getSprite(spriteSheet, 2, 5);
                } else if (spriteCount < 36) {
                    return getSprite(spriteSheet, 3, 5);
                } else {
                    return getSprite(spriteSheet, 2, 5);
                }
            }
            case "west" -> {
                if (spriteCount < 12) {
                    return getSprite(spriteSheet, 4, 5);
                } else if (spriteCount < 24) {
                    return getSprite(spriteSheet, 5, 5);
                } else if (spriteCount < 36) {
                    return getSprite(spriteSheet, 6, 5);
                } else {
                    return getSprite(spriteSheet, 5, 5);
                }
            }
            default ->{
                if (spriteCount < 12) {
                    return getSprite(spriteSheet, 1, 6);
                } else if (spriteCount < 24) {
                    return getSprite(spriteSheet, 2, 6);
                } else if (spriteCount < 36) {
                    return getSprite(spriteSheet, 3, 6);
                } else {
                    return getSprite(spriteSheet, 2, 6);
                }
            }
        }
    }
    public void speak(){

        super.speak();
    }

    private void setImage() {
        spriteSheet = loadSpriteSheet("/characters/npc/knight/blue_knight.png", 6, 6);
    }
}
