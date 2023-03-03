package ui;

import main.GamePanel;
import main.Utility;
import unit.laser.Laser;
import unit.obstacle.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlaySate implements GameState {

    private final BufferedImage background = new Utility().loadImage("background", 640, 640);
    private boolean initiate = true;

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        if(!initiate){
            g2.drawImage(background, 0, 0,null);

            gp.player.draw(g2);
            for (Laser laser :
                    gp.lasers) {
                laser.draw(g2);
            }

            for (Obstacle ob :
                    gp.obstacles) {
                ob.draw(g2);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
            g2.setColor(Color.white);
            g2.drawString("Score: " + gp.player.getScore(), gp.size / 2, gp.size / 2);
        }
    }

    @Override
    public void update(GamePanel gp) {
        gp.player.update(gp);
        for (Laser laser :
                gp.lasers) {
            laser.update();
        }

        gp.unitLoader.update();
        for (Obstacle ob :
                gp.obstacles) {
            ob.update(gp);
        }

        if(initiate){
            gp.dj.resumeMusic();
            gp.player.name = JOptionPane.showInputDialog("Player Name?");
            initiate = false;
        }
        gp.controller.increaseDifficulty(gp);
    }

    @Override
    public GameState setNext() {
        return this;
    }

    @Override
    public GameState setLast() {
        return new PauseSate();
    }
}
