package entity;

import items.Item;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Entity {

    public GamePanel gamePanel;
    public BufferedImage restNorth, northOne, northTwo, restSouth, southOne,
            southTwo, restWest, westOne, westTwo, restEast, eastOne, eastTwo;
    public BufferedImage attackNorthOne, attackNorthTwo, attackSouthOne, attackSouthTwo, attackWestOne, attackWestTwo, attackEastOne, attackEastTwo;
    public BufferedImage deadSouth;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public Rectangle attackHitBox = new Rectangle(0, 0, 0, 0);
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collision = false; //objects
    String[] dialogs = new String[20];

    //STATE
    public int worldX, worldY;
    public String direction = "south";
    public boolean spriteSwapAnimation = true;
    int dialogIndex = 0;
    public boolean standStill = true;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dead = false;
    boolean hpBarOn = false;

    //COUNTERS
    public int spriteCount = 0;
    public int actionCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //CHARACTER ATTRIBUTES
    public int mobileType; //0 = player, 1 = NPC, 2 = foe. sätt typ nr i klassen
    public String name;
    public int speed;
    public int maxHealth;
    public int currentHealth;
    public int level;
    public int strength;
    public int dexterity;
    public int intelligent;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Item currentWeapon;
    public Item currentOffHand;
    public Item currentHelm;
    public Item currentArmor;
    public Item currentGloves;
    public Item currentBoots;
    public Item currentJewelery;
    public Item itemAsEntity;

    //DAMAGE TEXT
    ArrayList<String> damageText = new ArrayList<>();
    ArrayList<Integer> damageTextCounter = new ArrayList<>();


    public Entity(GamePanel gamePanel){this.gamePanel = gamePanel;}
    public Entity(Item item){
        this.gamePanel = item.gamePanel;
        itemAsEntity = item;
        restSouth = item.icon;
    }
    public void setAction(){

    }
    public void damageReaction(){

    }
    public void speak(){
        if(dialogs[dialogIndex] == null){
            dialogIndex = 0;
        }
        gamePanel.ui.currentDialog = dialogs[dialogIndex];
        dialogIndex++;
        turnMobileTowardsPlayer();
    }
    public void turnMobileTowardsPlayer(){
        switch(gamePanel.player.direction){
            case "north" -> direction = "south";
            case "south" -> direction = "north";
            case "west" -> direction = "east";
            case "east" -> direction = "west";
        }
    }
    public void damageText(int damage){
        damageText.add(String.valueOf(damage));
        damageTextCounter.add(0);
    }
    public void drawDamageText(Graphics2D g2){

        int x = 0; // = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int y = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
        for (int i = 0; i < damageText.size(); i++) {
            if(damageText.get(i) != null){
                x = getCenterForX(g2, damageText.get(i));
                g2.setColor(Color.black);
                g2.drawString(damageText.get(i), x + 1, y + 1);
                g2.setColor(Color.red);
                g2.drawString(damageText.get(i), x, y);

                int counter = damageTextCounter.get(i) + 1;
                damageTextCounter.set(i, counter);
                y -= 15;

                if(damageTextCounter.get(i) > 100){
                    damageText.remove(i);
                    damageTextCounter.remove(i);
                }
            }
        }
    }
    public void update(){

        //Prioritize subclasses setAuction method
        setAction();

        collisionOn = false;
        gamePanel.hitBoxChecker.checkTile(this);
        //check npc collision with object
        //npc med foe
        //player med mobiles
        gamePanel.hitBoxChecker.checkObject(this, false);
        gamePanel.hitBoxChecker.checkMobile(this, gamePanel.npc);
        gamePanel.hitBoxChecker.checkMobile(this, gamePanel.foe);
        boolean contactPlayer = gamePanel.hitBoxChecker.checkPlayer(this);

        //Checkar om foes nuddar spelare
        if(!this.dead){
            if (this.mobileType == 2 && contactPlayer){
                if(!gamePanel.player.invincible){
                    //deal damage
                    gamePanel.soundEffect(8);
                    int damage = this.attack - gamePanel.player.defense;
                    if(damage < 0){
                        damage = 0;
                    }
                    gamePanel.player.currentHealth -= damage;
                    gamePanel.ui.addMessage("Ouch!", 2);
                    if (damage > 0){
                        gamePanel.player.invincible = true;
                    }
                }
            }
        }
        //Om collision är sann kan användaren inte röra sig
        if(dead){
            direction = "";
        }
        //Om collision är sann kan användaren inte röra sig
        if(!collisionOn){
            if(!standStill){
                switch(direction) {
                    case "north" -> worldY -= speed;
                    case "south" -> worldY += speed;
                    case "west" -> worldX -= speed;
                    case "east" -> worldX += speed;
                }
            }
        }
        spriteCount++;
        if (spriteCount > 15){
            spriteSwapAnimation = !spriteSwapAnimation;
            spriteCount = 0;
        }
        //invincible count
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;


        if(worldX + gamePanel.tileSize * 2 > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize * 2 < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize * 2 > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize * 2 < gamePanel.player.worldY + gamePanel.player.screenY){
            switch(direction){
                case "north" -> {
                    if(standStill){image = restNorth;}
                    else{
                        if(spriteSwapAnimation){image = northOne;}
                        else{image = northTwo;}
                    }
                }
                case "south" -> {
                    if(standStill){image = restSouth;}
                    else{
                        if(spriteSwapAnimation){image = southOne;}
                        else{image = southTwo;}
                    }
                }
                case "west" -> {
                    if(standStill){image = restWest;}
                    else{
                        if(spriteSwapAnimation){image = westOne;}
                        else{image = westTwo;}
                    }
                }
                case "east" -> {
                    if(standStill){image = restEast;}
                    else{
                        if(spriteSwapAnimation){image = eastOne;}
                        else{image = eastTwo;}
                    }
                }
            }

            //FOE health bar
            if(mobileType == 2 && hpBarOn){ //redSlime

                double oneScale = (double)gamePanel.tileSize/maxHealth;
                double hpBarValue = oneScale*currentHealth;

                if(currentHealth > 0){
                    g2.setColor(new Color( 35, 35, 35));
                    g2.fillRect(screenX - 1, screenY + 54, gamePanel.tileSize + 2, 9);


                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(screenX, screenY + 55, (int)hpBarValue, 7);
                }
                hpBarCounter++;
                if(hpBarCounter > 600){
                    hpBarCounter =0;
                    hpBarOn = false;
                }
            }

            if(damageText.size() != 0){
                drawDamageText(g2);
            }

            if (dead){

                dyingCounter++;

                image = deadSouth;
                if (dyingCounter > 3600){
                    dead = false;
                    alive = false;
                    dyingCounter = 0;
                }
            }
            else if(invincible){
                //Sätter rit egenskapen transparent
                //0.3f ger 70% transparens
                //Turn on health bar
                hpBarOn = true;
                hpBarCounter = 0;
                if(spriteSwapAnimation) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                }
            }

            g2.drawImage(image, screenX, screenY, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public BufferedImage setUp(String name, int width, int height){
        String imagePath = name + ".png";
        UtilityTool tool = new UtilityTool();
        BufferedImage scaled = null;
        try{
            scaled = tool.scaleImage(ImageIO.read(getClass().getResourceAsStream(imagePath)), width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaled;
    }
    public int getCenterForX(Graphics2D g2, String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return worldX - gamePanel.player.worldX + gamePanel.player.screenX + 24 - length/2;
    }
}
