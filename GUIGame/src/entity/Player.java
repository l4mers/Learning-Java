package entity;

import items.*;
import main.GamePanel;
import main.InputHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {


    InputHandler inputHandler;
    public final int screenX;
    public final int screenY;
    public int aSpriteCount = 0;
    public boolean attackAnimation = false;
    public ArrayList<Item> inventory = new ArrayList<>();
    public final int maxInventorySize = 36;


    public Player(GamePanel gamePanel, InputHandler inputHandler) {

        super(gamePanel);

        this.inputHandler = inputHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        //hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 32;
        hitBox.height = 32;
        collisionOn = true;
        //same value as rectangle

        setDefaultProperties();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultProperties(){
        //starting pos
        worldX = 1000;
        worldY = 1000;
        speed = 4;
        direction = "south";
        standStill = true;

        //Player status
        level = 1;
        maxHealth = 6;
        currentHealth = maxHealth;
        strength = 1;
        dexterity = 1;
        intelligent = 1;
        exp = 0;
        nextLevelExp = 3; //50 * level;
        currentWeapon = new NormalSword(gamePanel);
        currentOffHand = new WoodenShield(gamePanel);
        attack = getAttack();
        defense = getDefense();
        setAttackHitBox(currentWeapon.attackAreaX, currentWeapon.attackAreaY);
    }
    public void setItems(){
        inventory.add(new Food(gamePanel));
        inventory.add(new Key(gamePanel));
    }
    public void setAttackHitBox(int width, int height){
        attackHitBox.width = width;
        attackHitBox.height = height;
    }
    public int getAttack(){
        setAttackHitBox(currentWeapon.attackAreaX, currentWeapon.attackAreaY);
        return strength + currentWeapon.getAttackValue();
    }
    public int getDefense(){
        return dexterity + currentOffHand.getDefenseValue();
    }
    public void getPlayerImage(){
        restNorth = setUp("/character/resting_north", gamePanel.tileSize, gamePanel.tileSize);
        northOne = setUp("/character/go_north1", gamePanel.tileSize, gamePanel.tileSize);
        northTwo = setUp("/character/go_north2", gamePanel.tileSize, gamePanel.tileSize);
        restSouth = setUp("/character/resting_south", gamePanel.tileSize, gamePanel.tileSize);
        southOne = setUp("/character/go_south1", gamePanel.tileSize, gamePanel.tileSize);
        southTwo = setUp("/character/go_south2", gamePanel.tileSize, gamePanel.tileSize);
        restWest = setUp("/character/resting_west", gamePanel.tileSize, gamePanel.tileSize);
        westOne = setUp("/character/go_west1", gamePanel.tileSize, gamePanel.tileSize);
        westTwo = setUp("/character/resting_west", gamePanel.tileSize, gamePanel.tileSize);
        restEast = setUp("/character/resting_east", gamePanel.tileSize, gamePanel.tileSize);
        eastOne = setUp("/character/go_east1", gamePanel.tileSize, gamePanel.tileSize);
        eastTwo = setUp("/character/resting_east", gamePanel.tileSize, gamePanel.tileSize);
    }
    public void getPlayerAttackImage(){
        attackNorthOne = setUp("/character/sword_north0", gamePanel.tileSize, gamePanel.tileSize*2);
        attackNorthTwo = setUp("/character/sword_north1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackSouthOne = setUp("/character/sword_south0", gamePanel.tileSize, gamePanel.tileSize*2);
        attackSouthTwo = setUp("/character/sword_south1", gamePanel.tileSize, gamePanel.tileSize*2);
        attackWestOne = setUp("/character/sword_west0", gamePanel.tileSize*2, gamePanel.tileSize);
        attackWestTwo = setUp("/character/sword_west1", gamePanel.tileSize*2, gamePanel.tileSize);
        attackEastOne = setUp("/character/sword_east0", gamePanel.tileSize*2, gamePanel.tileSize);
        attackEastTwo = setUp("/character/sword_east1", gamePanel.tileSize*2, gamePanel.tileSize);
    }
    public void updateScreen(){
        //ATTACK
        if(attacking){
            attack();
        }
        else if (inputHandler.upPressed || inputHandler.downPressed ||
                inputHandler.leftPressed || inputHandler.rightPressed || inputHandler.interact){

            standStill = false;

            if(inputHandler.upPressed){
                direction = "north";
            } else if (inputHandler.downPressed){
                direction = "south";
            } else if (inputHandler.leftPressed){
                direction = "west";
            } else if (inputHandler.rightPressed){
                direction = "east";
            }

            //checkar kollision med omgivningen
            collisionOn = false;
            gamePanel.hitBoxChecker.checkTile(this);

            //checkar kollision med objekt
            int objectIndex = gamePanel.hitBoxChecker.checkObject(this, true);
            interactWithObject(objectIndex);

            //checkar kollision med NPC
            //Flytta för att inte behöva hålla ner en gå knapp
            int npcIndex = gamePanel.hitBoxChecker.checkMobile(this, gamePanel.npc);
            interactWithNPC(npcIndex);

            //check foe kollision
            int foeIndex = gamePanel.hitBoxChecker.checkMobile(this, gamePanel.foe);
            interactWithFoe(foeIndex);

            //check event
            gamePanel.eventHandler.checkEvent();

            //Om collision är sann kan användaren inte röra sig
            if(!collisionOn && !inputHandler.interact){
                switch(direction){
                    case "north" -> worldY -= speed;
                    case "south" -> worldY += speed;
                    case "west" -> worldX -= speed;
                    case "east" -> worldX += speed;
                }
            }
            gamePanel.inputHandler.interact = false;

        }
        spriteCount++;
        if(spriteCount > 15) {
            spriteSwapAnimation = !spriteSwapAnimation;
            spriteCount = 0;
            if(!attacking){
                standStill = true;
            }
        }

        //invincible count
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 90){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    private void attack() {

        if(inputHandler.upAttack){
            direction = "north";
        } else if (inputHandler.downAttack){
            direction = "south";
        } else if (inputHandler.leftAttack){
            direction = "west";
        } else if (inputHandler.rightAttack){
            direction = "east";
        }
        aSpriteCount++;
        attackAnimation = false;
        if (aSpriteCount > 5){
            attackAnimation = true;

            //Save default values
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int hitBoxAreaWidth = hitBox.width;
            int hitBoxAreaHeight = hitBox.height;

            //temporär ändring av hit box
            switch(direction){
                case "north" -> worldY -= attackHitBox.height;
                case "south" -> worldY += attackHitBox.height;
                case "west" -> worldX -= attackHitBox.width;
                case "east" -> worldX += attackHitBox.width;
            }

            //ADJUST ATTACK HIT BOX
            hitBox.width = attackHitBox.width;
            hitBox.height = attackHitBox.height;

            //CHECK foe interaction with new hit box values
            int foeIndex = gamePanel.hitBoxChecker.checkMobile(this, gamePanel.foe);
            damageFoe(foeIndex);

            //RESTORING default values
            worldX = currentWorldX;
            worldY = currentWorldY;
            hitBox.width = hitBoxAreaWidth;
            hitBox.height = hitBoxAreaHeight;

        }
        if(aSpriteCount > 25){
            aSpriteCount = 0;
            attacking = false;
            inputHandler.upAttack = false;
            inputHandler.downAttack = false;
            inputHandler.leftAttack = false;
            inputHandler.rightAttack = false;
        }
    }
    public void interactWithObject(int index){
        if (index != 999){

            String text;

            if(inventory.size() != maxInventorySize){
                inventory.add(gamePanel.objects[index].itemAsEntity);
                gamePanel.soundEffect(2);
                text = gamePanel.objects[index].itemAsEntity.getName() + " looted";
            }
            else {
                text = "Your inventory is full";
            }
            gamePanel.ui.addMessage(text, 0);
            gamePanel.objects[index] = null;
        }
    }
    public void interactWithNPC(int index){
        if (index != 999){
            if(gamePanel.inputHandler.interact){
                gamePanel.gameState(3);
                gamePanel.npc[index].speak();
                //test som gör att mobilen healar
                //gamePanel.eventHandler.healingPool(27,25,  3);
            }
        }
    }
    public void interactWithFoe(int index){
        if (index != 999){
            //Ändras med stats
            if (!invincible){
                gamePanel.soundEffect(8);

                int damage = gamePanel.foe[index].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                currentHealth -= damage;
                if (damage > 0){
                    invincible = true;
                }
            }
        }
    }
    public void damageFoe(int index){
        if (index != 999){

            if(!gamePanel.foe[index].invincible){
                gamePanel.soundEffect(7);

                int damage = attack - gamePanel.foe[index].defense;
                if(damage < 0){
                    damage = 0;
                }
                gamePanel.foe[index].currentHealth -= damage;

                gamePanel.foe[index].damageText(damage);

                gamePanel.foe[index].invincible = true;
                //set foe reaction on taking damage
                gamePanel.foe[index].damageReaction();
                if(gamePanel.foe[index].currentHealth <= 0){
                    gamePanel.foe[index].dead = true;
                    //testa sett sound effect här
                    gamePanel.soundEffect(9);
                    gamePanel.ui.addMessage("+ " + gamePanel.foe[index].exp + " Exp", 0);
                    getExp(gamePanel.foe[index].exp);
                }
            }
        }
    }
    public void getExp(int xp){
        exp += xp;
        if(exp >= nextLevelExp){
            levelUp();
        }
    }
    public void levelUp(){
        gamePanel.soundEffect(10);
        level++;
        strength++;
        intelligent++;
        dexterity++;
        attack = getAttack();
        exp = exp - nextLevelExp;
        nextLevelExp = 50 * level;
        gamePanel.gameState(3);
        gamePanel.ui.currentDialog = "You reached level " + level + "!\nAll attributes has been increased by 1";
    }
    public void setAttackAndDef(){
        attack = getAttack();
        defense = getDefense();
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "north" -> {
                if(standStill){image = restNorth;}
                else if (attacking){ tempScreenY -= gamePanel.tileSize;
                    if(attackAnimation){image = attackNorthTwo;}
                    else{image = attackNorthOne;}
                }
                else{
                    if(spriteSwapAnimation){image = northOne;}
                    else{image = northTwo;}
                }
            }
            case "south" -> {
                if(standStill){image = restSouth;}
                else if (attacking){
                    if(attackAnimation){image = attackSouthTwo;}
                    else{image = attackSouthOne;}
                }
                else{
                    if(spriteSwapAnimation){image = southOne;}
                    else{image = southTwo;}
                }
            }
            case "west" -> {
                if(standStill){image = restWest;}
                else if (attacking){ tempScreenX -= gamePanel.tileSize;
                    if(attackAnimation){image = attackWestTwo;}
                    else{image = attackWestOne;}
                }
                else{
                    if(spriteSwapAnimation){image = westOne;}
                    else{image = westTwo;}
                }
            }
            case "east" -> {
                if(standStill){image = restEast;}
                else if (attacking){
                    if(attackAnimation){image = attackEastTwo;}
                    else{image = attackEastOne;}
                }
                else{
                    if(spriteSwapAnimation){image = eastOne;}
                    else{image = eastTwo;}
                }
            }
        }

        if(invincible){
            //Sätter rit egenskapen transparent
            //0.3f ger 70% transparens
            if(spriteSwapAnimation) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);

        //reset transparent
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
