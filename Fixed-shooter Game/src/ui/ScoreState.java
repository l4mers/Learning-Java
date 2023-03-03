package ui;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class ScoreState implements GameState{

    private boolean initiate = true;
    private boolean initiated = false;
    private ArrayList<String> scoreList;

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        if(initiated){
            int y = gp.size;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
            g2.fillRect(0, 0, gp.width, gp.height);
            g2.setColor(Color.red);
            for (String score:
                    scoreList) {
                String[] split = score.split("/");
                g2.drawString(split[0] + " - Score: " + split[1] + " - " + split[2], gp.size, y);
                y += gp.size / 4;
            }
            y = gp.width - gp.size;
            g2.setColor(Color.white);
            g2.drawString("Press Enter To Play Again!", getCenterForX(g2,"Press Enter To Play Again!",gp), y);

            y += gp.size / 4;
            g2.drawString("Press ESC To Go Home", getCenterForX(g2,"Press ESC To Go Home",gp), y);
            initiated = false;
        }
    }

    @Override
    public void update(GamePanel gp) {
        if(initiate){
            gp.dj.stopMusic();
            gp.dj.resetMusic();
            gp.player.resetScore();
            gp.unitLoader.resetUnits();
            scoreList = gp.highScore.getScoreToStringList();
            initiate = false;
            initiated = true;
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
