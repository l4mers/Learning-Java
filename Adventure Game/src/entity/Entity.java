package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {

    public GamePanel gp;

    //MOVEMENT AND ATTACK
    public BufferedImage spriteSheet;
    public BufferedImage secondarySheet;
    public BufferedImage specialSheet;
    public UtilityTool ut = new UtilityTool();

    //HIT BOX
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public Rectangle attackHitBox = new Rectangle(0, 0, 0, 0);
    public int hitBoxDefaultX, hitBoxDefaultY;

    //OBJECT INTERSECTION
    public boolean collision = false;
    String[] dialogs = new String[20];

    //STATE
    public int worldX, worldY;
    public String direction = "south";
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean walking = false;
    public boolean alive = true;
    public boolean dead = false;
    public boolean isProjectile = false;
    public boolean hpBarOn = false;

    //COUNTERS
    public int spriteCount = 48;
    public int stayStillCount = 0;
    public int actionCounter = 0;
    public int actionCount = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public boolean spriteEffects = false;


    //TYPE
    public int entityType;
    public int player;
    public int NPC;
    public int foe;
    public int projectileType;
    final public int spear = 1;
    final public int fireball = 2;

    //ATTRIBUTES
    public String name;
    public int speed;
    public int maxHealth;
    public int currentHealth;
    public int level;
    public int attack;
    public int coin;
    //public Item currenEq;

    //DAMAGE TEXT
    ArrayList<String> damageText = new ArrayList<>();
    ArrayList<Integer> damageTextCounter = new ArrayList<>();

    public Entity(GamePanel gp){this.gp=gp;}

    public void setAction(){}

    public void damageReaction(){}

    public void speak(){}

    public void damageText(int damage){}

    public void drawDamageText(Graphics2D g2){}

    public void update(){
        //Prioritize subclasses setAuction method
        setAction();

        //CHECK COLLISION

        if (walking) {
            if(!collisionOn){
                switch(direction) {
                    case "north" -> worldY -= speed;
                    case "south" -> worldY += speed;
                    case "west" -> worldX -= speed;
                    case "east" -> worldX += speed;
                }
            }
            stayStillCount = 0;
            spriteCount++;
            if (spriteCount > 47) {
                spriteCount = 0;
            }
        } else {
            stayStillCount++;
            if (stayStillCount > 5){
                spriteCount = 48;
            }
        }

        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void standardWalkReaction(){
        actionCounter++;


        if(actionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(1, 126);

            if (i <= 25) {
                direction = "north"; walking = true;
            } else if (i <= 50) {
                direction = "south"; walking = true;
            } else if (i <= 75) {
                direction = "west"; walking = true;
            } else if (i <= 100) {
                direction = "east"; walking = true;
            } else {
                walking = false;
            }
            actionCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //g2.drawRect(100, 100, 100, 100);

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize * 2 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize * 2 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 2 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY) {

            if (isProjectile){
                switch (projectileType){
                    case spear -> image = getSpearImage();
                    case fireball -> image = getFireballImage();
                }
                switch (direction){
                    case "north" -> screenY -= 10;
                    case "south" -> screenY += 10;
                    case "east" -> screenX += 10;
                    default -> screenX -= 10;
                }
                g2.drawImage(image, screenX, screenY, null);
            } else {
                setAlpha(g2);
                image = walkAnimation();
                g2.drawImage(image, screenX, screenY, null);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
    }
    public BufferedImage walkAnimation(){
//        switch (direction){
//            case "north" ->{
//                if (spriteCount < 12){
//                    return getSprite(spriteSheet, 2, 5);
//                } else if (spriteCount < 24){
//                    return getSprite(spriteSheet, 3, 5);
//                } else if (spriteCount < 36){
//                    return getSprite(spriteSheet, 4, 5);
//                } else {
//                    return getSprite(spriteSheet, 3, 5);
//                }
//            }
//            case "south" ->{
//                if (spriteCount < 12){
//                    return getSprite(spriteSheet, 3, 3);
//                } else if (spriteCount < 24){
//                    return getSprite(spriteSheet, 4, 3);
//                } else if (spriteCount < 36){
//                    return getSprite(spriteSheet, 5, 3);
//                } else {
//                    return getSprite(spriteSheet, 4, 3);
//                }
//            }
//            case "west" ->{
//                if (spriteCount < 12){
//                    return getSprite(spriteSheet, 1, 4);
//                } else if (spriteCount < 24){
//                    return getSprite(spriteSheet, 2, 4);
//                } else if (spriteCount < 36){
//                    return getSprite(spriteSheet, 3, 4);
//                } else {
//                    return getSprite(spriteSheet, 2, 4);
//                }
//            }
//            case "east" ->{
//                if (spriteCount < 12){
//                    return getSprite(spriteSheet, 4, 4);
//                } else if (spriteCount < 24){
//                    return getSprite(spriteSheet, 5, 4);
//                } else if (spriteCount < 36){
//                    return getSprite(spriteSheet, 1, 5);
//                } else {
//                    return getSprite(spriteSheet, 5, 4);
//                }
//            }
//            default -> {
//                return null;
//            }
        return null;
    }
    private BufferedImage getFireballImage() {

        if (spriteCount < 3){
            return getSpecialSprite(spriteSheet, 1, 1, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 5){
            return getSpecialSprite(spriteSheet, 3, 1, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 7){
            return getSpecialSprite(spriteSheet, 5, 1, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 9){
            return getSpecialSprite(spriteSheet, 2, 2, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 11){
            return getSpecialSprite(spriteSheet, 4, 2, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 13){
            return getSpecialSprite(spriteSheet, 1, 3, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 15){
            return getSpecialSprite(spriteSheet, 3, 3, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 17){
            return getSpecialSprite(spriteSheet, 5, 3, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 19){
            return getSpecialSprite(spriteSheet, 2, 4, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 21){
            return getSpecialSprite(spriteSheet, 4, 4, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 23){
            return getSpecialSprite(spriteSheet, 1, 5, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 25){
            return getSpecialSprite(spriteSheet, 3, 5, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 27){
            return getSpecialSprite(spriteSheet, 5, 5, gp.playerSize, gp.playerSize);
        } else if (spriteCount < 29){
            return getSpecialSprite(spriteSheet, 2, 6, gp.playerSize, gp.playerSize);
        } else {
            return getSpecialSprite(spriteSheet, 4, 6, gp.playerSize, gp.playerSize);
        }
    }

    private BufferedImage getSpearImage() {

        switch(direction){
            case "north" -> {
                return getSprite(spriteSheet, 2, 4);
            }
            case "south" -> {
                return getSprite(spriteSheet, 2, 1);
            }
            case "west" -> {
                return getSprite(spriteSheet, 2, 2);
            }
            default -> {
                return getSprite(spriteSheet, 2, 3);
            }
        }
    }

    public void setAlpha(Graphics2D g2){
        if(invincible){
            //Sätter rit egenskapen transparent
            //0.3f ger 70% transparens
            if(spriteCount > 5 || stayStillCount > 5) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
    }

    //MAKE REACTION METHODS, LIKE WHAT WILL HAPPEN IF PLAYER HIT
    public BufferedImage loadSpriteSheet(String spritePath, int width, int height){
        BufferedImage img = null;
        try{
            img = ut.scaleImage(ImageIO.read(getClass().getResource(spritePath)), gp.playerSize * width, gp.playerSize * height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
    public BufferedImage loadSpriteSheetLong(String spritePath, int width, int height){
        BufferedImage img = null;
        try{
            img = ut.scaleImage(ImageIO.read(getClass().getResource(spritePath)), gp.playerSize * width, 193 * height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
    public BufferedImage loadSpriteSheetNormal(String spritePath, int width, int height){
        BufferedImage img = null;
        try{
            img = ut.scaleImage(ImageIO.read(getClass().getResource(spritePath)), gp.tileSize * width, gp.tileSize * height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
    public BufferedImage getSprite(BufferedImage img, int col, int row){
        return ut.getSubImage(img, col, row, gp.playerSize, gp.playerSize);
    }
    public BufferedImage getSpecialSprite(BufferedImage img, int col, int row, int width, int height){
        return ut.getSubImage(img, col, row, width, height);
    }
}
