package ui;

import main.GamePanel;

import java.awt.*;

public class PauseSate implements GameState{

    private boolean initiate = true;

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        int y = gp.height / 2 - gp.size * 2;

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("GAME IS PAUSED", getCenterForX(g2,"GAME IS PAUSED", gp), y);

        y += gp.size;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
        g2.drawString("Press Enter To Resume", getCenterForX(g2,"Press Enter To Resume",gp), y);

        y = gp.size * 5;
        g2.drawString("Press ESC To Go Home", getCenterForX(g2,"Press ESC To Go Home",gp), y);
    }

    @Override
    public void update(GamePanel gp) {
        if(initiate){
            gp.dj.pauseMusic();
            initiate = false;
        }
    }

    @Override
    public GameState setNext() {
        return new PlaySate();
    }

    @Override
    public GameState setLast() {
        return new HomeState();
    }

    private int getCenterForX(Graphics2D g2, String text, GamePanel gp){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.width/2 - length/2;
    }
}
