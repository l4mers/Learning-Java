package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;


public class MobileKnight extends Entity {

    public MobileKnight(GamePanel gamePanel){
        super(gamePanel);

        direction = "south";
        speed = 1;

        //hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 32;
        hitBox.height = 32;

        getMobileImage();
        setDialog();
    }
    public void getMobileImage(){
        restNorth = setUp("/mobiles/9", gamePanel.tileSize, gamePanel.tileSize);
        northOne = setUp("/mobiles/10", gamePanel.tileSize, gamePanel.tileSize);
        northTwo = setUp("/mobiles/11", gamePanel.tileSize, gamePanel.tileSize);
        restSouth = setUp("/mobiles/0", gamePanel.tileSize, gamePanel.tileSize);
        southOne = setUp("/mobiles/1", gamePanel.tileSize, gamePanel.tileSize);
        southTwo = setUp("/mobiles/2", gamePanel.tileSize, gamePanel.tileSize);
        restWest = setUp("/mobiles/6", gamePanel.tileSize, gamePanel.tileSize);
        westOne = setUp("/mobiles/6", gamePanel.tileSize, gamePanel.tileSize);
        westTwo = setUp("/mobiles/8", gamePanel.tileSize, gamePanel.tileSize);
        restEast = setUp("/mobiles/3", gamePanel.tileSize, gamePanel.tileSize);
        eastOne = setUp("/mobiles/4", gamePanel.tileSize, gamePanel.tileSize);
        eastTwo = setUp("/mobiles/3", gamePanel.tileSize, gamePanel.tileSize);
    }
    public void setDialog(){
        dialogs[0] = "Hello filthy scrub, this is no place for noobs!";
        dialogs[1] = "Why are you playing games instead of studying?";
        dialogs[2] = "Come back when you acquired the skill of Dave.";
        dialogs[3] = "So, on with ya now...";
    }
    //behavior
    public void setAction(){

        actionCounter++;

        if(actionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(1, 126);

            if (i <= 25) {
                direction = "north"; standStill = false;
            } else if (i <= 50) {
                direction = "south"; standStill = false;
            } else if (i <= 75) {
                direction = "west"; standStill = false;
            } else if (i <= 100) {
                direction = "east"; standStill = false;
            } else {
                standStill = true;
            }
            actionCounter = 0;
        }
    }
    public void speak(){

        super.speak();
    }
}
