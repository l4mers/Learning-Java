package entity.npc;

import entity.Entity;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Merchant extends Entity {

    public Merchant(GamePanel gp){
        super(gp);

        direction = "east";
        speed = 0;

        //HIT BOX
        hitBox.x = 28;
        hitBox.y = gp.tileSize / 2;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 40;
        hitBox.height = 60;
        walking = true;

        setImage();
    }
    public void setAction(){
        actionCounter++;
        actionCount++;

        if(actionCounter > 2000) {
            spriteEffects = true;
            actionCounter = 0;
            actionCount = 0;
        }

        if (actionCounter == 200){
            actionCount = 0;
            spriteEffects = false;
        }
    }

    public BufferedImage walkAnimation(){
        if(!spriteEffects){
            if (actionCount < 100){
                return getSprite(spriteSheet, 1, 1);
            } else if (actionCount < 110){
                return getSprite(spriteSheet, 2, 1);
            } else if (actionCount < 120){
                return getSprite(spriteSheet, 3, 1);
            } else if (actionCount < 130){
                return getSprite(spriteSheet, 4, 1);
            } else if (actionCount < 140){
                return getSprite(spriteSheet, 5, 1);
            } else{
                return getSprite(spriteSheet, 3, 5);
            }
        }else{
            if (actionCount < 10){
                return getSprite(spriteSheet, 1, 2);
            } else if (actionCount < 20){
                return getSprite(spriteSheet, 2, 2);
            } else if (actionCount < 30){
                return getSprite(spriteSheet, 3, 2);
            } else if (actionCount < 40){
                return getSprite(spriteSheet, 4, 2);
            } else if (actionCount < 50){
                return getSprite(spriteSheet, 5, 2);
            } else if (actionCount < 60){
                return getSprite(spriteSheet, 1, 3);
            } else if (actionCount < 70){
                return getSprite(spriteSheet, 2, 3);
            } else if (actionCount < 80){
                return getSprite(spriteSheet, 3, 3);
            } else if (actionCount < 90){
                return getSprite(spriteSheet, 4, 3);
            } else if (actionCount < 100){
                return getSprite(spriteSheet, 5, 3);
            } else if (actionCount < 110){
                return getSprite(spriteSheet, 1, 4);
            } else if (actionCount < 120){
                return getSprite(spriteSheet, 2, 4);
            } else if (actionCount < 130){
                return getSprite(spriteSheet, 3, 4);
            } else if (actionCount < 140){
                return getSprite(spriteSheet, 4, 4);
            } else if (actionCount < 150){
                return getSprite(spriteSheet, 5, 4);
            } else if (actionCount < 160){
                return getSprite(spriteSheet, 1, 5);
            } else if (actionCount < 180){
                return getSprite(spriteSheet, 2, 5);
            } else {
                return getSprite(spriteSheet, 3, 5);
            }
        }
    }
    public void speak(){

        super.speak();
    }

    private void setImage() {
        spriteSheet = loadSpriteSheet("/characters/npc/merchant/merchant.png", 5, 5);
    }
}
