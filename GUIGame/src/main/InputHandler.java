package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
Lägg till if och else if för sub states flr input
 */

public class InputHandler implements KeyListener{

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, interact;
    public boolean upAttack, downAttack, leftAttack, rightAttack;
    //for testing
    boolean testDrawTime = false;
    public InputHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode(); //registrerar vilken tangent med java nummer

        //Home screen
        if (gamePanel.homeScreen) {
            homeScreen(key);
        }

        //play
        else if (gamePanel.gamePlay) {
            //WALK
            walk(key);

            //ATTACK
            if(!gamePanel.player.attacking){
                attack(key);
            }

            //CHARACTER INFO
            UI(key);

            //INTERACT
            action(key);
        }
        //PAUSED
        else if (gamePanel.gamePaused) {
            pause(key);
        }
        //DIALOG
        else if (gamePanel.gameDialog) {
            dialog(key);
        }
        //Ändrar rörligheten något
//        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
//            upPressed = true;
//        } if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
//            downPressed = true;
//        } if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
//            leftPressed = true;
//        } if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
//            rightPressed = true;
//        }
    }
    public void homeScreen(int key){
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            gamePanel.ui.manuChoice--;
            if (gamePanel.ui.manuChoice < 0){
                gamePanel.ui.manuChoice = 2;
            }
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            gamePanel.ui.manuChoice++;
            if (gamePanel.ui.manuChoice > 2){
                gamePanel.ui.manuChoice = 0;
            }
        }
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER){
            switch(gamePanel.ui.manuChoice){
                case 0 ->{
                    gamePanel.gameState(1);
                   // gamePanel.playMusic(0);
                }
                case 1 ->{
                    //add later
                }
                case 2 ->{
                    System.exit(0);
                }
            }
        }
    }
    public void pause(int key){
        if (key == KeyEvent.VK_P) {
            gamePanel.gameState(1);
            //gamePanel.playMusic(0);
        }
    }
    public void attack(int key){
        if (key == KeyEvent.VK_UP) {
            gamePanel.soundEffect(6);
            upAttack = true;
            gamePanel.player.attacking = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_DOWN) {
            gamePanel.soundEffect(6);
            downAttack = true;
            gamePanel.player.attacking = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_LEFT) {
            gamePanel.soundEffect(6);
            leftAttack = true;
            gamePanel.player.attacking = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            gamePanel.soundEffect(6);
            rightAttack = true;
            gamePanel.player.attacking = true;
            gamePanel.player.standStill = false;
        }
    }
    public void walk(int key){
        if (key == KeyEvent.VK_W ) {
            upPressed = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_S) {
            downPressed = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_A) {
            leftPressed = true;
            gamePanel.player.standStill = false;
        } else if (key == KeyEvent.VK_D) {
            rightPressed = true;
            gamePanel.player.standStill = false;
        }
    }
    public void dialog(int key){
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER) {
            gamePanel.gameState(1);
        }
    }
    public void UI(int key){
        if(key == KeyEvent.VK_C){
            if(gamePanel.inventoryInfo){
                gamePanel.inventoryInfo = false;
            }
            gamePanel.characterInfo = !gamePanel.characterInfo;
        }

        if(key == KeyEvent.VK_I){
            if(gamePanel.characterInfo){
                gamePanel.characterInfo = false;
            }
            gamePanel.inventoryInfo = !gamePanel.inventoryInfo;
        }

        //PAUSE
        if (key == KeyEvent.VK_P) {
            gamePanel.gameState(2);
            gamePanel.stopMusic();
        }
    }
    public void action(int key){
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER) {
            interact = true;
        }

        //TESTING
        if (key == KeyEvent.VK_U) {
            testDrawTime = !testDrawTime;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode(); //registrerar vilken tangent med java nummer

        if(key == KeyEvent.VK_W){
            upPressed = false;
            //gamePanel.player.standStill = true;
        } else if(key == KeyEvent.VK_S){
            downPressed = false;
            //gamePanel.player.standStill = true;
        } else if(key == KeyEvent.VK_A){
            leftPressed = false;
            //gamePanel.player.standStill = true;
        } else if(key == KeyEvent.VK_D){
            rightPressed = false;
            //gamePanel.player.standStill = true;
        }


//        if (key == KeyEvent.VK_UP) {
//            upAttack = false;
//        } else if (key == KeyEvent.VK_DOWN) {
//            downAttack = false;
//        } else if (key == KeyEvent.VK_LEFT) {
//            leftAttack = false;
//        } else if (key == KeyEvent.VK_RIGHT) {
//            rightAttack = false;
//        }
    }
}
