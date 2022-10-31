package main;

import items.Item;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gamePanel;
    Graphics2D g2;
    Rectangle rec;
    Font maruMonica;
    BufferedImage heartFull, heartHalf, heartEmpty;
    ArrayList<String> info = new ArrayList<>();
    ArrayList<String> harmful = new ArrayList<>();
    ArrayList<String> beneficial = new ArrayList<>();
    ArrayList<Integer> infoCounter = new ArrayList<>();
    ArrayList<Integer> harmCounter = new ArrayList<>();
    ArrayList<Integer> beneficialCounter = new ArrayList<>();
    public String currentDialog = "";
    public String currentToolTip = "";
    public int manuChoice = 0;

    //läg till sub game state here

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //Arial fonten som kan ändras
        //Font.PLAIN är style
        //40 = storlek
        try{
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException | IOException e){
            e.printStackTrace();
        }
        // Create objects
        OBJ_Heart heart = new OBJ_Heart(gamePanel);
        heartFull = heart.southTwo;
        heartHalf = heart.southOne;
        heartEmpty = heart.restSouth;

        rec = new Rectangle(0,0,gamePanel.tileSize,gamePanel.tileSize);

    }
    public void addMessage(String text, int type){
        //type 0 info, 1 harmful, 2 receive, 3 beneficial, 4 damageDealt
        switch (type){
            case 0 ->{info.add(text);
                infoCounter.add(0);}
            case 1 ->{harmful.add(text);
                harmCounter.add(0);}
            case 2 ->{beneficial.add(text);
                beneficialCounter.add(0);}
        }
    }
    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(maruMonica);
        //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //home screen
        if(gamePanel.homeScreen){
            drawHomeScreen();
        }

        //play game
        if(gamePanel.gamePlay){
            drawPlayerLife();
            drawCombatText();
        }

        //pause game
        if (gamePanel.gamePaused){
            drawPlayerLife();
            pauseScreen();
        }

        //Dialog
        if (gamePanel.gameDialog){
            drawPlayerLife();
            dialogScreen();
        }

        //CHARACTER INFO
        if(gamePanel.characterInfo){
            drawCharacterInfo();
        }
        //INVENTORY
        if(gamePanel.inventoryInfo){
            drawInventory();
        }
    }
    public void drawPlayerLife(){

        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;
        int i = 0;

        //draw max health
        while (i < gamePanel.player.maxHealth/2){
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }
        x = gamePanel.tileSize/2;
        i = 0;

        //draw current
        while (i < gamePanel.player.currentHealth){
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if (i< gamePanel.player.currentHealth){
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }
    public void drawCombatText(){
        int infoX = 0; //gamePanel.screenWidth / 2 ;
        int infoY = gamePanel.tileSize * 3;

//        int harmfulX = gamePanel.tileSize;
//        int harmfulY = gamePanel.screenHeight / 2;
//
//        int beneficialX = gamePanel.screenWidth - gamePanel.tileSize;
//        int beneficialY = gamePanel.screenHeight / 2;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < info.size(); i++) {
            if(info.get(i) != null){
                infoX = getCenterForX(info.get(i));
                g2.setColor(Color.black);
                g2.drawString(info.get(i), infoX + 2, infoY - 1);
                g2.setColor(Color.white);
                g2.drawString(info.get(i), infoX, infoY);

                int counter = infoCounter.get(i) + 1;
                infoCounter.set(i, counter);
                infoY += 40;

                if(infoCounter.get(i) > 180){
                    info.remove(i);
                    infoCounter.remove(i);
                }
            }
        }
    }
    public void drawHomeScreen(){

        //check sub state here
        g2.setColor(Color.darkGray);
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);

        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
        String text = "Puzzle Fighter";
        int x = getCenterForX(text);
        int y = gamePanel.tileSize * 3;

        // shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);

        //main text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //image
        x = gamePanel.screenWidth / 2 -(gamePanel.tileSize*2) /2;
        y += gamePanel.tileSize*2;
        g2.drawImage(gamePanel.player.restSouth, x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x  = getCenterForX(text);
        y += gamePanel.tileSize*3.5;
        g2.drawString(text, x, y);
        if (manuChoice == 0){
            g2.drawString("->", x- gamePanel.tileSize, y);
        }

        text = "LOAD GAME";
        x  = getCenterForX(text);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y);
        if (manuChoice == 1){
            g2.drawString("->", x- gamePanel.tileSize, y);
        }

        text = "QUIT";
        x  = getCenterForX(text);
        y += gamePanel.tileSize;
        g2.drawString(text, x, y);
        if (manuChoice == 2){
            g2.drawString("->", x- gamePanel.tileSize, y);
        }
        //else if när vi använder sub game states
    }
    public void pauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
        String text = "GAME PAUSED";
        int x = getCenterForX(text);
        int y = gamePanel.screenHeight / 2;
        g2.drawString(text, x, y);
    }
    public void dialogScreen(){
        //Dialog
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize /2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize*4);
        int height = gamePanel.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
        x += gamePanel.tileSize - 20;
        y += gamePanel.tileSize;

        for (String line: currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawInventory(){

        //Inventory frame
        int frameX = gamePanel.screenWidth / 2 - gamePanel.tileSize * 2;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.screenWidth / 2 + gamePanel.tileSize / 4;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 3;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        int subFrameX = frameX + gamePanel.tileSize / 2;
        int subFrameY = frameY + gamePanel.tileSize / 4;
        int subFrameWidth = gamePanel.tileSize;
        int subFrameHeight = gamePanel.tileSize;

        rec.x = subFrameX;
        rec.y = subFrameY;

        int col = 0;
        int row = 0;
        int inventoryCount = 0;
        while(col < 6 && row < 6){
            if(gamePanel.player.inventory.size() > inventoryCount){
                drawSquareSubWindow(subFrameX, subFrameY, subFrameWidth, subFrameHeight);
                if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
                    drawHoverSubWindow(subFrameX, subFrameY, subFrameWidth, subFrameHeight);
                    drawToolTip(gamePanel.player.inventory.get(inventoryCount), 32F);
                }
                g2.drawImage(gamePanel.player.inventory.get(inventoryCount).icon, subFrameX, subFrameY, null);
                checkIfClicked(gamePanel.player.inventory.get(inventoryCount), inventoryCount);
            }

            inventoryCount++;
            col++;
            subFrameX += gamePanel.tileSize + gamePanel.tileSize / 4;
            rec.x = subFrameX;
            if(col == 6){
                col = 0;
                row++;
                subFrameX = frameX + gamePanel.tileSize / 2;
                subFrameY += gamePanel.tileSize + gamePanel.tileSize / 4;
                rec.x = subFrameX;
                rec.y = subFrameY;
            }
        }
        g2.setColor(Color.yellow);
        g2.fillRoundRect(frameX + gamePanel.tileSize, frameHeight + gamePanel.tileSize / 3, 15, 15, 15, 15);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        g2.drawString(String.valueOf(gamePanel.player.coin),frameX + gamePanel.tileSize + gamePanel.tileSize / 2, frameHeight + 32);
    }

    private void checkIfClicked(Item item, int index) {
        if(rec.intersects(gamePanel.mouseInputHandler.rec)){
            if (gamePanel.mouseInputHandler.click){
                switch (item.type){
                    case 0 ->{
                        gamePanel.player.inventory.set(index, gamePanel.player.currentWeapon);
                        gamePanel.player.currentWeapon = item;
                        gamePanel.player.setAttackAndDef();
                    }
                    case 1 ->{
                        gamePanel.player.inventory.set(index, gamePanel.player.currentOffHand);
                        gamePanel.player.currentOffHand = item;
                        gamePanel.player.setAttackAndDef();
                    }
                    case 2 ->{

                    }
                    case 3 ->{

                    }
                    case 4 ->{

                    }
                    case 5 ->{

                    }
                    case 6 ->{

                    }
                    case 7 ->{
                        item.consume();
                        gamePanel.player.inventory.remove(index);

                    }
                    case 8 ->{

                    }
                }
                gamePanel.mouseInputHandler.click = false;
            }
        }
    }

    public void drawCharacterInfo(){

        //CREATE STAT FRAME
        final int frameX = gamePanel.tileSize;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //CREATE EQUIPMENT FRAME
        final int eqFrameX = gamePanel.tileSize * 7;
        final int eqFrameY = gamePanel.tileSize;
        final int eqFrameWidth = gamePanel.tileSize * 8;
        final int eqFrameHeight = gamePanel.tileSize * 9;
        drawSubWindow(eqFrameX, eqFrameY, eqFrameWidth, eqFrameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 34;

        //NAMES
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Intelligence", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);

        //VALUES
        int tailx = (frameX + frameWidth) - 30;
        //reset text pos
        textY = frameY + gamePanel.tileSize;
        String value;

        value = String.valueOf(gamePanel.player.exp + " / " + gamePanel.player.nextLevelExp);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.level);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.strength);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.intelligent);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.attack);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.defense);
        textX = getXForAlignText(value, tailx);
        g2.drawString(value, textX, textY);

        //EQUIPMENT TEXT
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(Color.white);
        textX = eqFrameX + eqFrameWidth / 2;
        textY = frameY + gamePanel.tileSize;
        int imageX = eqFrameX + eqFrameWidth / 2 - gamePanel.tileSize / 2;
        int imageY = gamePanel.tileSize / 6;

        g2.drawString("Helm", getCenterOfText("Helm", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentHelm != null){
                drawToolTip(gamePanel.player.currentHelm, 32F);
            }
        }
        if(gamePanel.player.currentHelm != null){
            g2.drawImage(gamePanel.player.currentHelm.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY = frameY + gamePanel.tileSize * 3;
        textX = eqFrameX + eqFrameWidth / 4;
        imageX = eqFrameX + eqFrameWidth / 4 - gamePanel.tileSize / 2;         //eqFrameX + gamePanel.tileSize + gamePanel.tileSize / 2; // eqFrameX + eqFrameWidth / 2 - gamePanel.tileSize / 4;

        g2.drawString("Armor", getCenterOfText("Armor", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentArmor != null){
                drawToolTip(gamePanel.player.currentArmor,32F);
            }
        }
        if(gamePanel.player.currentArmor != null){
            g2.drawImage(gamePanel.player.currentArmor.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY += gamePanel.tileSize * 2;

        g2.drawString("Jewelery", getCenterOfText("Jewelery", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentJewelery != null){
                drawToolTip(gamePanel.player.currentJewelery, 32F);
            }
        }
        if(gamePanel.player.currentJewelery != null){
            g2.drawImage(gamePanel.player.currentJewelery.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY += gamePanel.tileSize * 2;

        g2.drawString("Weapon", getCenterOfText("Weapon", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentWeapon != null){
                drawToolTip(gamePanel.player.currentWeapon,32F);
            }
        }
        if(gamePanel.player.currentWeapon != null){
            g2.drawImage(gamePanel.player.currentWeapon.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY = frameY + gamePanel.tileSize * 3;
        textX = eqFrameX + eqFrameWidth - gamePanel.tileSize * 2;
        imageX = eqFrameX + eqFrameWidth - eqFrameWidth / 4 - gamePanel.tileSize / 2;

        g2.drawString("Gloves", getCenterOfText("Gloves", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentGloves != null){
                drawToolTip(gamePanel.player.currentGloves, 32F);
            }
        }
        if(gamePanel.player.currentGloves != null){
            g2.drawImage(gamePanel.player.currentGloves.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY += gamePanel.tileSize * 2;

        g2.drawString("Boots", getCenterOfText("Boots", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentBoots != null){
                drawToolTip(gamePanel.player.currentBoots, 32F);
            }
        }
        if(gamePanel.player.currentBoots != null){
            g2.drawImage(gamePanel.player.currentBoots.icon, imageX, textY + imageY, null);
        }
        g2.setColor(Color.white);

        textY += gamePanel.tileSize * 2;

        g2.drawString("Off Hand", getCenterOfText("Off Hand", textX), textY);
        drawSquareSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
        rec.x = imageX;
        rec.y = textY + imageY;
        if(rec.intersects(gamePanel.mouseMotionHandler.rec)){
            drawHoverSubWindow(imageX, textY + imageY, gamePanel.tileSize, gamePanel.tileSize);
            if(gamePanel.player.currentOffHand != null){
                drawToolTip(gamePanel.player.currentOffHand, 32F);
            }
        }
        if(gamePanel.player.currentOffHand != null){
            g2.drawImage(gamePanel.player.currentOffHand.icon, imageX, textY + imageY, null);
        }
    }
    public void drawToolTip(Item item, float def){

        final int frameX = gamePanel.tileSize;
        final int frameY = gamePanel.tileSize * 7 + gamePanel.tileSize / 2;
        final int frameWidth = gamePanel.tileSize * 4 + gamePanel.tileSize / 2;
        final int frameHeight = gamePanel.tileSize * 4;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        int textX = gamePanel.tileSize + gamePanel.tileSize / 2;
        int textY = gamePanel.tileSize * 7 + gamePanel.tileSize;
        String test = item.name;
        String[] test2 = item.toolTip.split("\n");
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22F));
        g2.drawString(test, textX, textY);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18F));
        for (String sub:test2){
            textY += 20;
            g2.drawString(sub, textX, textY);
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, def));
    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color color = new Color(0,0,0, 200);
        g2.setColor(color);
        //35 round edges
        g2.fillRoundRect(x, y, width, height, 45,45);

        color = new Color (101, 69, 41, 245);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 45,45);

    }
    public void drawSquareSubWindow(int x, int y, int width, int height){

        Color color = new Color(58, 58, 58, 200);
        g2.setColor(color);
        //35 round edges
        g2.fillRoundRect(x, y, width, height, 5,5);

        color = new Color (101, 69, 41, 245);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, width, height, 5,5);

    }
    public void drawHoverSubWindow(int x, int y, int width, int height){
        Color color = new Color(47, 47, 47, 213);
        g2.setColor(color);
        //35 round edges
        g2.fillRoundRect(x, y, width, height, 5,5);
    }
    public int getCenterForX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.screenWidth/2 - length/2;
    }
    public int getXForAlignText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
    public int getCenterOfText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length/2;
    }
}
