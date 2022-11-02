package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonHandler implements ActionListener, KeyListener {

    private final GamePanel gp;
    private boolean on = true;
    public ButtonHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(gp.bt1)) {
            gp.gm.moveTiles(1, 2, 5);
        }
        if (e.getSource().equals(gp.bt2)) {
            gp.gm.moveTiles(2, 1, 3, 6);
        }
        if (e.getSource().equals(gp.bt3)) {
            gp.gm.moveTiles(3, 2, 4, 7);
        }
        if (e.getSource().equals(gp.bt4)) {
            gp.gm.moveTiles(4, 3, 8);
        }
        if (e.getSource().equals(gp.bt5)) {
            gp.gm.moveTiles(5, 1, 6, 9);
        }
        if (e.getSource().equals(gp.bt6)) {
            gp.gm.moveTiles(6, 2, 5, 7, 10);
        }
        if (e.getSource().equals(gp.bt7)) {
            gp.gm.moveTiles(7, 3, 6, 8, 11);
        }
        if (e.getSource().equals(gp.bt8)) {
            gp.gm.moveTiles(8, 4, 7, 12);
        }
        if (e.getSource().equals(gp.bt9)) {
            gp.gm.moveTiles(9, 5, 10, 13);
        }
        if (e.getSource().equals(gp.bt10)) {
            gp.gm.moveTiles(10, 6, 9, 11, 14);
        }
        if (e.getSource().equals(gp.bt11)) {
            gp.gm.moveTiles(11, 7, 10, 12, 15);
        }
        if (e.getSource().equals(gp.bt12)) {
            gp.gm.moveTiles(12, 8, 11, 16);
        }
        if (e.getSource().equals(gp.bt13)) {
            gp.gm.moveTiles(13, 9, 14);
        }
        if (e.getSource().equals(gp.bt14)) {
            gp.gm.moveTiles(14, 10, 13, 15);
        }
        if (e.getSource().equals(gp.bt15)) {
            gp.gm.moveTiles(15, 11, 14, 16);
        }
        if (e.getSource().equals(gp.bt16)) {
            gp.gm.moveTiles(16, 12, 15);
        }
        if (e.getSource().equals(gp.btStart)) {
            startButton();
        }
        if (e.getSource().equals(gp.btRestart)) {
            gp.sound.playSoundEffect(3);
            gp.gameState(gp.startScreenState);
        }
        if (e.getSource().equals(gp.btBack)) {
            gp.sound.playSoundEffect(3);
            gp.gameState(gp.startScreenState);
        }
        if (e.getSource().equals(gp.btScoreBoard)) {
            gp.sound.playSoundEffect(3);
            gp.gameState(gp.scoreboardState);
            gp.scoreboard.loadScoreboard(gp.scoreBoardTextArea);
        }
        if (e.getSource().equals(gp.btSort)) {
            gp.sound.playSoundEffect(3);
            gp.scoreboard.setSortOrder();
            gp.btSort.setText(gp.scoreboard.sortList());
            gp.scoreboard.loadScoreboard(gp.scoreBoardTextArea);
        }
        if (e.getSource().equals(gp.btQuit)) {
            gp.sound.playSoundEffect(3);
            System.exit(0);
        }
        if (e.getSource().equals(gp.btMusic)) {
            gp.sound.playSoundEffect(3);
            toggleMusic();
        }
    }

    public void startButton() {
        gp.sound.playSoundEffect(3);
        //ta in namnet och skicka in i valideringsmetoden
        if (inputPlayerName()) {
            gp.gm.shuffleGameTiles();
            gp.gm.dealGameTiles();
            gp.gameState(gp.playScreenState);
        }
    }

    private boolean inputPlayerName() {
        String playerInputName = JOptionPane.showInputDialog("Who's playing?");
        if(playerInputName != null){
            if (gp.scoreboard.setPlayerName(playerInputName)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid name!");
                return false;
            }
        }
        else {
            return false;
        }
    }
    private void toggleMusic() {
        if (on) {
            gp.sound.stop();
            on = false;
            gp.btMusic.setText("<html><font size='+1' face='x12y16pxMaruMonica'>Music ON</font></html>");
        } else {
            gp.sound.restartMusic();
            on = true;
            gp.btMusic.setText("<html><font size='+1' face='x12y16pxMaruMonica'>Music OFF</font></html>");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_C) {
            gp.gm.cheat();
            gp.sound.playSoundEffect(2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
