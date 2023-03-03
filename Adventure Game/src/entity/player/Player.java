package entity.player;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import main.InputHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    InputHandler ih;
    String[] spritePath = {"/characters/player/knight/sprite_sheet.png",
            "/characters/player/soldier/sprite_sheet.png",
            "/characters/player/mage/sprite_sheet.png",
            "/characters/player/healer/sprite_sheet.png",
            "/characters/player/healer/attack.png"};

    String[] secondarySpritePath = {"/characters/player/knight/secondary_sheet.png",
            "/characters/player/soldier/special_effect.png",
            "/characters/player/mage/secondary_sheet.png",
            "/characters/player/healer/heal.png"};

    String[] specialSpritePath = {"/characters/player/knight/special_sheet.png",
            "/characters/player/soldier/special_sheet.png",
            "/characters/player/mage/special_sheet.png",
            "/characters/player/healer/bubble_1.png"};

    BufferedImage bubble;

    public Projectile projectile;

    public final int screenX;
    public final int screenY;

    //STATE
    public int playerState;
    final public int knight = 1;
    final public int soldier = 2;
    final public int mage = 3;
    final public int healer = 4;
    public int swapHeroCD = 0;


    //SPRITE BOOL
    public boolean soldierSpecialBuff = false;

    //COUNTERS
    public int specialEffectCounter = 0;

    //KNIGHT
    public int knightSecondaryCD = 0;
    public int knightSpecialCD = 0;
    //SOLDIER
    public int soldierSecondaryCD = 0;
    public int soldierSpecialCD = 0;
    //MAGE
    public int magePrimaryCD = 0;
    public int mageSecondaryCD = 0;
    public int mageSpecialCD = 0;
    //HEALER
    public int healerPrimaryCD = 0;
    public int healerSecondaryCD = 0;
    public int healerSpecialCD = 0;

    public boolean projectTileCast = false;



    public Player(GamePanel gp, InputHandler ih) {
        super(gp);
        this.ih = ih;
        projectile = new Projectile(this.gp);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        playerState = knight;

        //HIT BOX
        hitBox.x = 28;
        hitBox.y = gp.playerSize / 4;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 40;
        hitBox.height = 40;

        setDefaultProperties();
        spriteSheet = loadSpriteSheet(spritePath[0], 5, 5);
        secondarySheet = loadSpriteSheet(secondarySpritePath[0], 4, 4);
        specialSheet = loadSpriteSheetNormal(specialSpritePath[0], 8, 8);
        bubble = loadSpriteSheetNormal(specialSpritePath[3], 1, 1);

    }

    public void setDefaultProperties(){

        //SPAWN LOCATION
        worldX = gp.screenWidth / 2;
        worldY = gp.screenHeight / 2;

        speed = 10;
        direction = "south";

        level = 1;
        maxHealth = 6;
        currentHealth = maxHealth;
        attack = 2;
    }

    public void setAttackHitBox(int width, int height){
        attackHitBox.width = width;
        attackHitBox.height = height;
    }

    //UPDATE
    public void update() {

        switch (playerState){
            case knight -> updateKnight();
            case soldier -> updateSoldier();
            case mage -> updateMage();
            case healer -> updateHealer();
        }

        if (soldierSpecialBuff){
            if (specialEffectCounter > 16){
                specialEffectCounter = 1;
            }
            specialEffectCounter++;
            if (soldierSpecialCD == 400){
                soldierSpecialBuff = false;
                //ih.special = false;
            }
        }
        CDCount();
    }

    private void updateHealer() {
        if(attacking){
            stayStillCount = 0;
            spriteCount++;
            if(ih.primary) {
                if (spriteCount > 27) {
                    spriteCount = 0;
                    attacking = false;
                    ih.primary = false;
                }
            } else if (ih.secondary){
                if (spriteCount > 25){
                    spriteCount = 0;
                    attacking = false;
                    ih.secondary = false;
                }
            } else if (ih.special){
                if (spriteCount > 25){
                    spriteCount = 0;
                    attacking = false;
                    ih.special = false;
                    spriteEffects = false;
                }
            }
        }else moveButtonPressed();
    }

    // UPDATE MAGE
    private void updateMage() {
        if(attacking){
            stayStillCount = 0;
            spriteCount++;
            if(ih.primary) {
                if (spriteCount > 8 && projectTileCast){
                    projectile.setUpProjectile(5, 70, true, attack , 2,
                            worldX, worldY, direction, this);
                    gp.projectileList.add(projectile);
                    projectTileCast = false;
                }
                if (spriteCount > 25) {
                    spriteCount = 0;
                    attacking = false;
                    ih.primary = false;
                }
            } else if (ih.secondary){
                if (spriteCount > 30){
                    spriteCount = 0;
                    attacking = false;
                    ih.secondary = false;
                }
            } else if (ih.special){
                if (spriteCount > 30){
                    spriteCount = 0;
                    attacking = false;
                    ih.special = false;
                    spriteEffects = false;
                }
            }
        }else moveButtonPressed();
    }

    //UPDATE KNIGHT
    private void updateKnight(){
        if (attacking) {
            stayStillCount = 0;
            spriteCount++;
            if(ih.primary) {
                if (spriteCount > 25) {
                    spriteCount = 0;
                    attacking = false;
                    ih.primary = false;
                }
            } else if (ih.secondary){
                walk(1);
                if (spriteCount > 69){
                    spriteCount = 0;
                    attacking = false;
                    ih.secondary = false;
                }
            } else if (ih.special){
                if (spriteCount > 30){
                    spriteCount = 0;
                    attacking = false;
                    ih.special = false;
                    spriteEffects = false;
                }
            }
        } else moveButtonPressed();
    }
    //UPDATE SOLDIER
    private void updateSoldier(){
        if (attacking) {
            stayStillCount = 0;
            spriteCount++;
            if(ih.primary) {
                if (spriteCount > 25) {
                    spriteCount = 0;
                    attacking = false;
                    ih.primary = false;
                }
            } else if (ih.secondary){
                if (spriteCount > 8 && projectTileCast){
                    projectile.setUpProjectile(6, 70, true, attack , 1,
                    worldX, worldY, direction, this);
                    gp.projectileList.add(projectile);
                    projectTileCast = false;
                }
                if (spriteCount > 25) {
                    spriteCount = 0;
                    attacking = false;
                    ih.secondary = false;
                }
            } else if (ih.special){
                soldierSpecialBuff = true;
                if (spriteCount > 25){
                    spriteCount = 0;
                    attacking = false;
                    ih.special = false;
                    spriteEffects = false;
                }
            }
        } else moveButtonPressed();

        //LÄGG TILL COLLISION för soldier HÄR
    }
    //MOVES CHARACTER SAME FOR ALL
    private void moveButtonPressed() {
        if (ih.northPressed || ih.southPressed ||
                ih.westPressed || ih.eastPressed) {
            setDirection();
            walk(speed);
            stayStillCount = 0;
            spriteCount++;
            if (spriteCount > 48) {
                spriteCount = 0;
            }
        } else {
            stayStillCount++;
            if (stayStillCount > 5){
                spriteCount = 48;
            }
        }
    }
    //SETS THE DIRECTION
    private void setDirection() {
        if (ih.northPressed) {
            direction = "north";
        } else if (ih.southPressed) {
            direction = "south";
        } else if (ih.westPressed) {
            direction = "west";
        } else if (ih.eastPressed) {
            direction = "east";
        }
    }
    //MAKES THE PLAYER MOVE AFTER DIRECTION IS SET
    private void walk(int speed) {
        switch(direction){
            case "north" -> worldY -= speed;
            case "south" -> worldY += speed;
            case "west" -> worldX -= speed;
            case "east" -> worldX += speed;
        }
    }
    //COOLDOWN COUNT
    private void CDCount(){
        if (knightSpecialCD != 0){
            knightSpecialCD--;
        }
        if (knightSecondaryCD != 0){
            knightSecondaryCD--;
        }
        if (soldierSecondaryCD != 0){
            soldierSecondaryCD--;
        }
        if (soldierSpecialCD != 0){
            soldierSpecialCD--;
        }
        if (magePrimaryCD != 0){
            magePrimaryCD--;
        }
        if (mageSecondaryCD != 0){
            mageSecondaryCD--;
        }
        if (mageSpecialCD != 0){
            mageSpecialCD--;
        }
        if (healerPrimaryCD != 0){
            healerPrimaryCD--;
        }
        if (healerSecondaryCD != 0){
            healerSecondaryCD--;
        }
        if (healerSpecialCD != 0){
            healerSpecialCD--;
        }
        if (swapHeroCD != 0){
            swapHeroCD--;
        }
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    //DRAW
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        BufferedImage secondImage = null;


        switch (playerState){
            case knight -> drawKnight(image, secondImage,  g2);
            case soldier -> drawSoldier(image, secondImage,  g2);
            case mage -> drawMage(image, secondImage,  g2);
            case healer -> drawHealer(image, secondImage,  g2);
        }
        if(healerSpecialCD != 0){
            secondImage = getSpecialSprite(bubble,1, 1, gp.tileSize, gp.tileSize);
            g2.drawImage(secondImage, screenX + 15, screenY+ 5,null);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
    }

    private void drawHealer(BufferedImage image, BufferedImage secondImage, Graphics2D g2) {
        if (attacking){
            image = primaryAttackAnimation();
        }else{
            image = walkAnimation();
        }
        setAlpha(g2);
        g2.drawImage(image, screenX, screenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (spriteEffects){
            secondImage = healerSpecialAnimation();
            if (ih.primary){
                switch (direction){
                    case "north" -> g2.drawImage(secondImage, screenX + gp.tileSize / 4, screenY - gp.playerSize / 2,null);
                    case "south" -> g2.drawImage(secondImage, screenX + gp.playerSize / 6, screenY + gp.playerSize / 2,null);
                    case "east" -> g2.drawImage(secondImage, screenX + gp.tileSize, screenY + gp.tileSize / 8,null);
                    case "west" -> g2.drawImage(secondImage, screenX - 40, screenY + gp.tileSize / 8,null);
                }
            }else if (ih.secondary){
                g2.drawImage(secondImage, screenX + 15, screenY - 5,null);
            }
        }
    }

    private void drawMage(BufferedImage image, BufferedImage secondImage, Graphics2D g2) {
        if (attacking){
            image = primaryAttackAnimation();
        }else{
            image = walkAnimation();
        }
        if (spriteEffects){
            secondImage = mageSpecialAnimation();
            if (ih.secondary){
                switch (direction){
                    case "north" -> {
                        g2.drawImage(secondImage, screenX, screenY - gp.tileSize, null);
                        setAlpha(g2);
                        g2.drawImage(image, screenX, screenY, null);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    }
                    case "south" ->{
                        g2.drawImage(secondImage, screenX, screenY + gp.playerSize / 2,null);
                        setAlpha(g2);
                        g2.drawImage(image, screenX, screenY,null);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    }
                    case "east" ->{
                        g2.drawImage(secondImage, screenX + gp.tileSize, screenY - 30,null);
                        setAlpha(g2);
                        g2.drawImage(image, screenX, screenY,null);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    }
                    case "west" -> {
                        g2.drawImage(secondImage, screenX - gp.tileSize, screenY - 30, null);
                        setAlpha(g2);
                        g2.drawImage(image, screenX, screenY,null);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    }
                }
            }else if (ih.special){
                setAlpha(g2);
                g2.drawImage(image, screenX, screenY,null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                g2.drawImage(secondImage, screenX - 85, screenY - 85,null);
            }else {
                setAlpha(g2);
                g2.drawImage(image, screenX, screenY,null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        } else {
            setAlpha(g2);
            g2.drawImage(image, screenX, screenY,null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    private void drawSoldier(BufferedImage image, BufferedImage secondImage, Graphics2D g2) {
        if (attacking){
            image = soldierAttackAnimation();
        }else{
            image = walkAnimation();
        }
        setAlpha(g2);
        g2.drawImage(image, screenX, screenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (spriteEffects){
            secondImage = soldierSpecialAnimation();
            if (spriteCount <= 16){
                g2.drawImage(secondImage, screenX, screenY - 122,null);
            }
        } else if (soldierSpecialBuff){
            secondImage = soldierSpecialBuffAnimation();
            g2.drawImage(secondImage, screenX, screenY + 10,null);
        }
    }

    private void drawKnight(BufferedImage image, BufferedImage secondImage, Graphics2D g2) {
        if (attacking){
            image = knightAttackAnimation();
        }else{
            image = walkAnimation();
        }
        if (spriteEffects){
            secondImage = knightSpecialAnimation();
            if (spriteCount > 13){
                g2.drawImage(secondImage, screenX - 85, screenY - 85,null);
            }
            setAlpha(g2);
            if(spriteCount > 3 && spriteCount < 8){
                g2.drawImage(image, screenX, screenY + spriteCount,null);
            } else if (spriteCount > 8 && spriteCount < 13){
                g2.drawImage(image, screenX, screenY - spriteCount,null);
            } else {
                g2.drawImage(image, screenX, screenY,null);
            }
        }else{
            g2.drawImage(image, screenX, screenY,null);
        }
    }

    //ATTACK COLLISION
    private void attack() {
        //Get collision
    }
    //COMMON WALK ANIMATION, SAME FOR ALL HEROES
    public BufferedImage walkAnimation(){
        switch (direction){
            case "north" ->{
                if (spriteCount < 12){
                    return getSprite(spriteSheet, 2, 5);
                } else if (spriteCount < 24){
                    return getSprite(spriteSheet, 3, 5);
                } else if (spriteCount < 36){
                    return getSprite(spriteSheet, 4, 5);
                } else {
                    return getSprite(spriteSheet, 3, 5);
                }
            }
            case "south" ->{
                if (spriteCount < 12){
                    return getSprite(spriteSheet, 3, 3);
                } else if (spriteCount < 24){
                    return getSprite(spriteSheet, 4, 3);
                } else if (spriteCount < 36){
                    return getSprite(spriteSheet, 5, 3);
                } else {
                    return getSprite(spriteSheet, 4, 3);
                }
            }
            case "west" ->{
                if (spriteCount < 12){
                    return getSprite(spriteSheet, 1, 4);
                } else if (spriteCount < 24){
                    return getSprite(spriteSheet, 2, 4);
                } else if (spriteCount < 36){
                    return getSprite(spriteSheet, 3, 4);
                } else {
                    return getSprite(spriteSheet, 2, 4);
                }
            }
            case "east" ->{
                if (spriteCount < 12){
                    return getSprite(spriteSheet, 4, 4);
                } else if (spriteCount < 24){
                    return getSprite(spriteSheet, 5, 4);
                } else if (spriteCount < 36){
                    return getSprite(spriteSheet, 1, 5);
                } else {
                    return getSprite(spriteSheet, 5, 4);
                }
            }
            default -> {
                return null;
            }
        }
    }
    //KNIGHT ATTACK ANIMATION
    private BufferedImage knightAttackAnimation() {
        if (ih.primary) {
            return primaryAttackAnimation();
        }
        else if (ih.secondary){
            if (spriteCount < 4){
                return getSprite(secondarySheet, 1, 1);
            } else if (spriteCount < 8){
                return getSprite(secondarySheet, 2, 1);
            } else if (spriteCount < 12){
                return getSprite(secondarySheet, 3, 1);
            } else if (spriteCount < 16){
                return getSprite(secondarySheet, 4, 1);
            } else if (spriteCount < 20){
                return getSprite(secondarySheet, 1, 2);
            } else if (spriteCount < 24){
                return getSprite(secondarySheet, 2, 2);
            } else if (spriteCount < 28){
                return getSprite(secondarySheet, 3, 2);
            } else if (spriteCount < 31){
                return getSprite(secondarySheet, 4, 2);
            } else if (spriteCount < 34){
                return getSprite(secondarySheet, 1, 3);
            } else if (spriteCount < 37){
                return getSprite(secondarySheet, 2, 3);
            } else if (spriteCount < 40){
                return getSprite(secondarySheet, 3, 2);
            } else if (spriteCount < 43){
                return getSprite(secondarySheet, 4, 2);
            }else if (spriteCount < 46){
                return getSprite(secondarySheet, 1, 3);
            } else if (spriteCount < 49){
                return getSprite(secondarySheet, 2, 3);
            }else if (spriteCount < 52){
                return getSprite(secondarySheet, 3, 1);
            } else if (spriteCount < 56){
                return getSprite(secondarySheet, 4, 1);
            } else if (spriteCount < 60){
                return getSprite(secondarySheet, 1, 2);
            } else if (spriteCount < 64){
                return getSprite(secondarySheet, 2, 2);
            } else if (spriteCount < 66){
                return getSprite(secondarySheet, 2, 1);
            }  else {
                return walkAnimation();
            }
        } else {
            switch (direction) {
                case "north" -> {
                    if (spriteCount < 4) {
                        return getSprite(spriteSheet, 2, 5);
                    } else if (spriteCount < 15) {
                        return getSprite(spriteSheet, 3, 5);
                    } else {
                        return getSprite(spriteSheet, 2, 5);
                    }
                }
                case "south" -> {
                    if (spriteCount < 4) {
                        return getSprite(spriteSheet, 5, 3);
                    } else if (spriteCount < 15) {
                        return getSprite(spriteSheet, 4, 3);
                    } else {
                        return getSprite(spriteSheet, 5, 3);
                    }
                }
                case "west" -> {
                    if (spriteCount < 4) {
                        return getSprite(spriteSheet, 1, 4);
                    } else if (spriteCount < 15) {
                        return getSprite(spriteSheet, 2, 4);
                    } else {
                        return getSprite(spriteSheet, 1, 4);
                    }
                }
                case "east" -> {
                    if (spriteCount < 4) {
                        return getSprite(spriteSheet, 1, 5);
                    } else if (spriteCount < 15) {
                        return getSprite(spriteSheet, 5, 4);
                    } else  {
                        return getSprite(spriteSheet, 1, 5);
                    }
                }
                default -> {
                    return null;
                }
            }
        }
    }
    //SOLDIER ATTACK ANIMATION
    private BufferedImage soldierAttackAnimation(){
        if (ih.primary){
            return primaryAttackAnimation();
        } else if (ih.secondary){
            return primaryAttackAnimation();
        } else {
            return walkAnimation();
        }
    }
    //STANDARD ATTACK, SAME FOR ALL
    private BufferedImage primaryAttackAnimation(){
        switch (direction) {
            case "north" -> {
                if (spriteCount < 5) {
                    return getSprite(spriteSheet, 5, 2);
                } else if (spriteCount < 15) {
                    return getSprite(spriteSheet, 1, 3);
                } else if (spriteCount < 20) {
                    return getSprite(spriteSheet, 2, 3);
                } else {
                    return getSprite(spriteSheet, 4, 5);
                }
            }
            case "south" -> {
                if (spriteCount < 5) {
                    return getSprite(spriteSheet, 1, 1);
                } else if (spriteCount < 15) {
                    return getSprite(spriteSheet, 2, 1);
                } else if (spriteCount < 20) {
                    return getSprite(spriteSheet, 3, 1);
                } else {
                    return getSprite(spriteSheet, 3, 3);
                }
            }
            case "west" -> {
                if (spriteCount < 5) {
                    return getSprite(spriteSheet, 4, 1);
                } else if (spriteCount < 15) {
                    return getSprite(spriteSheet, 5, 1);
                } else if (spriteCount < 20) {
                    return getSprite(spriteSheet, 1, 2);
                } else {
                    return getSprite(spriteSheet, 1, 4);
                }
            }
            case "east" -> {
                if (spriteCount < 5) {
                    return getSprite(spriteSheet, 2, 2);
                } else if (spriteCount < 15) {
                    return getSprite(spriteSheet, 3, 2);
                } else if (spriteCount < 20) {
                    return getSprite(spriteSheet, 4, 2);
                } else {
                    return getSprite(spriteSheet, 4, 4);
                }
            }
            default -> {
                return null;
            }
        }
    }
    //HEALER ATTACK ANIMATION
    private BufferedImage healerSpecialAnimation() {
        if(ih.primary){
            if (spriteCount < 4){
                return getSpecialSprite(specialSheet, 2,1, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 8){
                return getSpecialSprite(specialSheet, 3,1, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 11){
                return getSpecialSprite(specialSheet, 1,2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 14){
                return getSpecialSprite(specialSheet, 2,2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 17){
                return getSpecialSprite(specialSheet, 3,2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 20){
                return getSpecialSprite(specialSheet, 1,3, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 22){
                return getSpecialSprite(specialSheet, 2,3, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 25){
                return getSpecialSprite(specialSheet, 3,3, gp.tileSize, gp.tileSize);
            } else {
                return getSpecialSprite(specialSheet, 1,4, gp.tileSize, gp.tileSize);
            }
        } else {
            if (spriteCount < 3){
                return getSpecialSprite(secondarySheet, 1, 1, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 5){
                return getSpecialSprite(secondarySheet, 2, 1, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 7){
                return getSpecialSprite(secondarySheet, 3, 1, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 10){
                return getSpecialSprite(secondarySheet, 1, 2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 13){
                return getSpecialSprite(secondarySheet, 2, 2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 16){
                return getSpecialSprite(secondarySheet, 3, 2, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 19){
                return getSpecialSprite(secondarySheet, 1, 3, gp.tileSize, gp.tileSize);
            } else if (spriteCount < 22){
                return getSpecialSprite(secondarySheet, 2, 3, gp.tileSize, gp.tileSize);
            } else {
                return getSpecialSprite(secondarySheet, 3, 3, gp.tileSize, gp.tileSize);
            }
        }
    }
    private BufferedImage knightSpecialAnimation() {
        if (spriteCount > 8 && spriteCount >= 19){
            return getSpecialSprite(specialSheet, 2,2, 256, 256);
        }else{
            return getSpecialSprite(specialSheet, 1,2, 256, 256);
        }
    }
    private BufferedImage soldierSpecialAnimation() {
        if (spriteCount <= 2) {
            return getSpecialSprite(secondarySheet, 2, 1, gp.playerSize, 193);
        } else if (spriteCount <= 4) {
            return getSpecialSprite(secondarySheet, 3, 1, gp.playerSize, 193);
        } else if (spriteCount <= 6) {
            return getSpecialSprite(secondarySheet, 4, 1, gp.playerSize, 193);
        } else if (spriteCount <= 8) {
            return getSpecialSprite(secondarySheet, 5, 1, gp.playerSize, 193);
        } else if (spriteCount <= 10) {
            return getSpecialSprite(secondarySheet, 1, 2, gp.playerSize, 193);
        } else if (spriteCount <= 12) {
            return getSpecialSprite(secondarySheet, 2, 2, gp.playerSize, 193);
        } else if (spriteCount <= 14) {
            return getSpecialSprite(secondarySheet, 3, 2, gp.playerSize, 193);
        } else {
            return getSpecialSprite(secondarySheet, 4, 2, gp.playerSize, 193);
        }
    }
    private BufferedImage mageSpecialAnimation() {
        if(ih.secondary){
            switch (direction){
                case "north" -> {
                    if (spriteCount < 5){
                        return getSprite(secondarySheet, 5, 3);
                    } else if (spriteCount < 9){
                        return getSprite(secondarySheet, 1, 4);
                    } else if (spriteCount < 13){
                        return getSprite(secondarySheet, 2, 4);
                    } else if (spriteCount < 17){
                        return getSprite(secondarySheet, 3, 4);
                    } else {
                        return getSprite(secondarySheet, 4, 4);
                    }
                }
                case "south" ->{
                    if (spriteCount < 5){
                        return getSprite(secondarySheet, 2, 5);
                    } else if (spriteCount < 9){
                        return getSprite(secondarySheet, 3, 5);
                    } else if (spriteCount < 13){
                        return getSprite(secondarySheet, 4, 5);
                    } else if (spriteCount < 17){
                        return getSprite(secondarySheet, 5, 5);
                    } else {
                        return getSprite(secondarySheet, 1, 6);
                    }
                }
                case "east" ->{
                    if (spriteCount < 5){
                        return getSprite(secondarySheet, 1, 1);
                    } else if (spriteCount < 9){
                        return getSprite(secondarySheet, 2, 1);
                    } else if (spriteCount < 13){
                        return getSprite(secondarySheet, 3, 1);
                    } else if (spriteCount < 17){
                        return getSprite(secondarySheet, 4, 1);
                    } else {
                        return getSprite(secondarySheet, 5, 1);
                    }
                }
                default ->{
                    if (spriteCount < 5){
                        return getSprite(secondarySheet, 3, 2);
                    } else if (spriteCount < 9){
                        return getSprite(secondarySheet, 4, 2);
                    } else if (spriteCount < 13){
                        return getSprite(secondarySheet, 5, 2);
                    } else if (spriteCount < 17){
                        return getSprite(secondarySheet, 1, 3);
                    } else {
                        return getSprite(secondarySheet, 2, 3);
                    }
                }
            }
        } else {
            if (spriteCount < 3){
                return getSpecialSprite(specialSheet, 1, 1, 256, 256);
            } else if (spriteCount < 6){
                return getSpecialSprite(specialSheet, 2, 1, 256, 256);
            } else if (spriteCount < 9){
                return getSpecialSprite(specialSheet, 3, 1, 256, 256);
            } else if (spriteCount < 12){
                return getSpecialSprite(specialSheet, 1, 2, 256, 256);
            } else if (spriteCount < 15){
                return getSpecialSprite(specialSheet, 2, 2, 256, 256);
            } else if (spriteCount < 18){
                return getSpecialSprite(specialSheet, 3, 2, 256, 256);
            } else if (spriteCount < 21){
                return getSpecialSprite(specialSheet, 1, 3, 256, 256);
            } else if (spriteCount < 24){
                return getSpecialSprite(specialSheet, 2, 3, 256, 256);
            } else if (spriteCount < 27){
                return getSpecialSprite(specialSheet, 3, 3, 256, 256);
            } else {
                return getSpecialSprite(specialSheet, 1, 4, 256, 256);
            }
        }
    }
    private BufferedImage soldierSpecialBuffAnimation() {
        if (specialEffectCounter <= 4) {
            return getSprite(specialSheet, 1, 1);
        } else if (specialEffectCounter <= 8) {
            return getSprite(specialSheet, 2, 1);
        } else if (specialEffectCounter <= 12) {
            return getSprite(specialSheet, 1, 2);
        } else {
            return getSprite(specialSheet, 2, 2);
        }
    }

    public void swapHeroImage() {
        switch (playerState){
            case knight -> {
                spriteSheet = loadSpriteSheet(spritePath[0], 5, 5);
                secondarySheet = loadSpriteSheet(secondarySpritePath[0], 4, 4);
                specialSheet = loadSpriteSheetNormal(specialSpritePath[0], 8, 8);
            }
            case soldier -> {
                spriteSheet = loadSpriteSheet(spritePath[1], 5, 5);
                secondarySheet = loadSpriteSheetLong(secondarySpritePath[1], 5, 2);
                specialSheet = loadSpriteSheet(specialSpritePath[1], 2, 2);
            }
            case mage ->{
                spriteSheet = loadSpriteSheet(spritePath[2], 5, 5);
                secondarySheet = loadSpriteSheet(secondarySpritePath[2], 5, 6);
                specialSheet = loadSpriteSheetNormal(specialSpritePath[2], 12, 16);
            }
            case healer -> {
                spriteSheet = loadSpriteSheet(spritePath[3], 5, 5);
                secondarySheet = loadSpriteSheetNormal(secondarySpritePath[3], 3, 3);
                specialSheet = loadSpriteSheetNormal(spritePath[4], 3, 4);
            }
        }
    }
}
