package entity.foes;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Slime extends Entity {
    public Slime(GamePanel gp) {

        super(gp);

        entityType = 2;

        speed = 1;
        maxHealth = 3;
        currentHealth = maxHealth;
        attack = 3;

        hitBox.x = gp.tileSize / 2;
        hitBox.y = gp.tileSize + 5;
        hitBox.width = 28;
        hitBox.height = 28;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        setImage();
    }

    public void setAction() {

        if(!dead){
            standardWalkReaction();
        }
    }
    public void damageReaction(){

    }
    public BufferedImage walkAnimation(){
        switch (direction) {
            case "north", "south" -> {
                if (spriteCount < 7){
                    return getSprite(spriteSheet, 1, 1);
                } else if (spriteCount < 14){
                    return getSprite(spriteSheet, 2, 1);
                } else if (spriteCount < 21){
                    return getSprite(spriteSheet, 3, 1);
                } else if (spriteCount < 28){
                    return getSprite(spriteSheet, 2, 1);
                } else if (spriteCount < 35) {
                    return getSprite(spriteSheet, 1, 1);
                } else if (spriteCount < 42){
                    return getSprite(spriteSheet, 2, 1);
                } else if (spriteCount < 48){
                    return getSprite(spriteSheet, 3, 1);
                } else {
                    return getSprite(spriteSheet, 2, 1);
                }
            }
            case "west" -> {
                if (spriteCount < 6){
                    return getSprite(spriteSheet, 1, 2);
                } else if (spriteCount < 13){
                    return getSprite(spriteSheet, 2, 2);
                } else if (spriteCount < 20){
                    return getSprite(spriteSheet, 3, 2);
                } else if (spriteCount < 27){
                    return getSprite(spriteSheet, 2, 2);
                } else if (spriteCount < 34) {
                    return getSprite(spriteSheet, 1, 2);
                } else if (spriteCount < 41){
                    return getSprite(spriteSheet, 2, 2);
                } else if (spriteCount < 48){
                    return getSprite(spriteSheet, 3, 2);
                } else {
                    return getSprite(spriteSheet, 2, 1);
                }
            }
            default ->{
                if (spriteCount < 7){
                    return getSprite(spriteSheet, 1, 3);
                } else if (spriteCount < 14){
                    return getSprite(spriteSheet, 2, 3);
                } else if (spriteCount < 21){
                    return getSprite(spriteSheet, 3, 3);
                } else if (spriteCount < 28){
                    return getSprite(spriteSheet, 2, 3);
                } else if (spriteCount < 35) {
                    return getSprite(spriteSheet, 1, 3);
                } else if (spriteCount < 42){
                    return getSprite(spriteSheet, 2, 3);
                } else if (spriteCount < 48){
                    return getSprite(spriteSheet, 3, 3);
                } else {
                    return getSprite(spriteSheet, 2, 1);
                }
            }
        }
    }

    private void setImage() {
        spriteSheet = loadSpriteSheet("/characters/foes/slimes/red_slime.png", 3, 3);
    }
}
