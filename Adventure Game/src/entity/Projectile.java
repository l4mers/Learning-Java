package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);

        isProjectile = true;
    }

    public void setUpProjectile(int speed, int maxHealth, boolean alive, int attack, int type,
                                int worldX, int worldY, String direction, Entity user){

        if (type == 2){
            switch (direction){
                case "north" ->{
                    spriteSheet = loadSpriteSheet("/characters/player/mage/projectile_north.png", 5, 6);
                }
                case "east" ->{
                    spriteSheet = loadSpriteSheet("/characters/player/mage/projectile_east.png", 5, 6);
                }
                case "west" ->{
                    spriteSheet = loadSpriteSheet("/characters/player/mage/projectile_west.png", 5, 6);
                }
                default -> {
                    spriteSheet = loadSpriteSheet("/characters/player/mage/projectile_south.png", 5, 6);
                }
            }
        } else if(type == 1){
            spriteSheet = loadSpriteSheet("/characters/player/soldier/secondary_sheet.png", 3, 4);
        }

        this.speed = speed;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.alive = alive;
        this.attack = attack;
        projectileType = type;


        set(worldX, worldY, direction, user);

    }
    public void set(int worldX, int worldY, String direction, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.currentHealth = this.maxHealth;
        this.user = user;
    }

    public void update(){

//        if(user.equals(gp.player)){
//            //KOLLA COLLISION
//        } else{
//
//        }

        switch(direction){
            case "north" -> worldY -= speed;
            case "south" -> worldY += speed;
            case "west" -> worldX -= speed;
            case "east" -> worldX += speed;
        }

        currentHealth--;
        if(currentHealth < 0){
            alive = false;
        }
        spriteCount++;
        switch (projectileType){
            case spear -> {
                if(spriteCount > 16) {
                    spriteCount = 0;
                }
            }
            case fireball -> {
                if(spriteCount > 30) {
                    spriteCount = 1;
                }
            }
        }
    }

//    public void draw(Graphics2D g2) {
//        BufferedImage image = null;
//
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        switch (direction){
//            case "north" -> screenY -= 10;
//            case "south" -> screenY += 10;
//            case "east" -> screenX += 10;
//            default -> screenX -= 10;
//        }
//        switch (projectileType){
//            case spear -> image = getSpearImage();
//            case fireball -> image = getFireballImage();
//        }

//        g2.drawImage(image, screenX, screenY,null);
//    }

//    private BufferedImage getFireballImage() {
//
//        if (spriteCount < 3){
//            return getSpecialSprite(spriteSheet, 1, 1, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 5){
//            return getSpecialSprite(spriteSheet, 3, 1, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 7){
//            return getSpecialSprite(spriteSheet, 5, 1, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 9){
//            return getSpecialSprite(spriteSheet, 2, 2, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 11){
//            return getSpecialSprite(spriteSheet, 4, 2, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 13){
//            return getSpecialSprite(spriteSheet, 1, 3, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 15){
//            return getSpecialSprite(spriteSheet, 3, 3, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 17){
//            return getSpecialSprite(spriteSheet, 5, 3, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 19){
//            return getSpecialSprite(spriteSheet, 2, 4, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 21){
//            return getSpecialSprite(spriteSheet, 4, 4, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 23){
//            return getSpecialSprite(spriteSheet, 1, 5, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 25){
//            return getSpecialSprite(spriteSheet, 3, 5, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 27){
//            return getSpecialSprite(spriteSheet, 5, 5, gp.playerSize, gp.playerSize);
//        } else if (spriteCount < 29){
//            return getSpecialSprite(spriteSheet, 2, 6, gp.playerSize, gp.playerSize);
//        } else {
//            return getSpecialSprite(spriteSheet, 4, 6, gp.playerSize, gp.playerSize);
//        }
//    }
//
//    private BufferedImage getSpearImage() {
//
//        switch(direction){
//            case "north" -> {
//                return getSprite(spriteSheet, 2, 4);
//            }
//            case "south" -> {
//                return getSprite(spriteSheet, 2, 1);
//            }
//            case "west" -> {
//                return getSprite(spriteSheet, 2, 2);
//            }
//            default -> {
//                return getSprite(spriteSheet, 2, 3);
//            }
//        }
//    }
}
