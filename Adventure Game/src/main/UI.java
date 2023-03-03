package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;

    public int manuChoice = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try{
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException | IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        switch (gp.gameState){
            case 1 -> {
                homeScreen();
            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
            case 5 -> {

            }
            case 6 -> {

            }
        }
    }

    private void homeScreen() {
        g2.setColor(Color.darkGray);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        //TITLE
        g2.setFont(g2.getFont().deriveFont(Font.BOLD | Font.ITALIC, 100F));
        String text = "The Adventurers Tale";
        int x = getCenterForX(text);
        int y = gp.tileSize * 3;
        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //MAIN TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //IMAGE
        x = gp.screenWidth / 2 -(gp.tileSize*2) /2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.getSprite(gp.player.spriteSheet, 5,3), x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x  = getCenterForX(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (manuChoice == 0){
            g2.drawString("->", x- gp.tileSize, y);
        }

        text = "LOAD GAME";
        x  = getCenterForX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (manuChoice == 1){
            g2.drawString("->", x- gp.tileSize, y);
        }

        text = "QUIT";
        x  = getCenterForX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (manuChoice == 2){
            g2.drawString("->", x- gp.tileSize, y);
        }
    }
    public int getCenterForX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
