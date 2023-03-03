package ui;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HomeState implements GameState {

    private boolean initiate = true;
    private final BufferedImage homeBackground = loadImage("homebackground");

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        int y;

        g2.drawImage(homeBackground, 0, 0, gp.width, gp.height, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
        String title = "Space Splice";

        g2.setColor(Color.black);
        g2.drawString(title, getCenterForX(g2, title, gp) + 2, gp.size * 2 - 5);

        g2.setColor(Color.white);
        g2.drawString(title, getCenterForX(g2, title, gp), gp.size * 2);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26F));
        y = gp.size * 4;

        g2.drawString("Press Enter To Play", getCenterForX(g2,"Press Enter To Play",gp), y);

        y = gp.size * 5;
        g2.drawString("Press ESC To Exit", getCenterForX(g2,"Press ESC To Exit",gp), y);
    }
    @Override
    public void update(GamePanel gp){
        if(initiate){
            gp.dj.stopMusic();
            gp.dj.resetMusic();
            gp.player.resetScore();
            gp.player.name = "";
            gp.unitLoader.resetUnits();
            initiate = false;
        }
    }

    @Override
    public GameState setNext() {
        return new ScoreState();
    }

    @Override
    public GameState setLast() {
        System.exit(0);
        return this;
    }

    private int getCenterForX(Graphics2D g2, String text, GamePanel gp){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.width/2 - length/2;
    }
    private BufferedImage loadImage(String name){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/" + name + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
