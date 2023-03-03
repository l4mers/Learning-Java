package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    GamePanel gp;

    public boolean northPressed, southPressed, westPressed, eastPressed, interact, select;

    public boolean primary, secondary, special;

    public InputHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (gp.gameState){
            case 1 -> {
                homeScreen(key);
            }
            case 2 -> {
                if(!gp.player.attacking){
                    walk(key);
                    attack(key);
                    if(gp.player.swapHeroCD == 0) {
                        swapHero(key);
                    }
                }
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

    private void swapHero(int key) {
        if (key == KeyEvent.VK_1) {
            gp.player.spriteEffects = false;
            gp.player.playerState = gp.player.knight;
            gp.player.swapHeroImage();
            gp.player.swapHeroCD = 150;
        } else if (key == KeyEvent.VK_2) {
            gp.player.spriteEffects = false;
            gp.player.playerState = gp.player.soldier;
            gp.player.swapHeroImage();
            gp.player.swapHeroCD = 150;
        } else if (key == KeyEvent.VK_3) {
            gp.player.spriteEffects = false;
            gp.player.playerState = gp.player.mage;
            gp.player.swapHeroImage();
            gp.player.swapHeroCD = 150;
        } else if (key == KeyEvent.VK_4) {
            gp.player.spriteEffects = false;
            gp.player.playerState = gp.player.healer;
            gp.player.swapHeroImage();
            gp.player.swapHeroCD = 150;
        }
    }

    private void walk(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            northPressed = true;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            southPressed = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            westPressed = true;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            eastPressed = true;
        }
    }

    private void attack(int key) {
        switch (gp.player.playerState){
            case 1 ->{
                if (key == KeyEvent.VK_J) {
                    primary = true;
                    gp.player.attacking = true;
                    gp.player.spriteCount = 0;
                } else if (key == KeyEvent.VK_K) {
                    if (gp.player.knightSecondaryCD == 0){
                        secondary = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.knightSecondaryCD = 400;
                    }
                } else if (key == KeyEvent.VK_L) {
                    if(gp.player.knightSpecialCD == 0){
                        special = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.knightSpecialCD = 800;
                    }
                }
            } case 2 -> {
                if (key == KeyEvent.VK_J) {
                    primary = true;
                    gp.player.attacking = true;
                    gp.player.spriteCount = 0;
                } else if (key == KeyEvent.VK_K) {
                    if (gp.player.soldierSecondaryCD == 0){
                        secondary = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.soldierSecondaryCD = 400;
                        gp.player.projectTileCast = true;
                    }
                } else if (key == KeyEvent.VK_L) {
                    if(gp.player.soldierSpecialCD == 0){
                        special = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.soldierSpecialCD = 800;
                    }
                }
            } case 3 -> {
                if (key == KeyEvent.VK_J) {
                    if (gp.player.magePrimaryCD == 0) {
                        primary = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.magePrimaryCD = 100;
                        gp.player.projectTileCast = true;
                    }
                } else if (key == KeyEvent.VK_K) {
                    if (gp.player.mageSecondaryCD == 0){
                        secondary = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.mageSecondaryCD = 400;
                    }
                } else if (key == KeyEvent.VK_L) {
                    if(gp.player.mageSpecialCD == 0){
                        special = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.mageSpecialCD = 800;
                    }
                }
            } case 4 -> {
                if (key == KeyEvent.VK_J) {
                    primary = true;
                    gp.player.attacking = true;
                    gp.player.spriteCount = 0;
                    gp.player.spriteEffects = true;
                } else if (key == KeyEvent.VK_K) {
                    if (gp.player.healerSecondaryCD == 0){
                        secondary = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.healerSecondaryCD = 800;
                    }
                } else if (key == KeyEvent.VK_L) {
                    if(gp.player.healerSecondaryCD == 0){
                        special = true;
                        gp.player.attacking = true;
                        gp.player.spriteCount = 0;
                        gp.player.spriteEffects = true;
                        gp.player.healerSpecialCD = 800;
                    }
                }
            }
        }
    }

    private void homeScreen(int key) {
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            gp.ui.manuChoice--;
            if (gp.ui.manuChoice < 0){
                gp.ui.manuChoice = 2;
            }
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            gp.ui.manuChoice++;
            if (gp.ui.manuChoice > 2){
                gp.ui.manuChoice = 0;
            }
        }
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_U){
            switch(gp.ui.manuChoice){
                case 0 ->{
                    gp.gameState = gp.adventureMode;
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

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            northPressed = false;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            southPressed = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            westPressed = false;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            eastPressed = false;
        } //else if (key == KeyEvent.VK_J) {
//            primary = false;
        //}
        //else if (key == KeyEvent.VK_K) {
//            secondary = false;

        //} //else if (key == KeyEvent.VK_L) {
//            special = false;
//        }
    }
}
