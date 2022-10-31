package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {

    GamePanel gamePanel;
    Rectangle rec;
    public boolean click = false;
    public MouseInputHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        rec = new Rectangle(0,0, 5, 5);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        setRec(e.getX(), e.getY());
        click = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void setRec(int x, int y){
        rec.x = x;
        rec.y = y;
    }
}
