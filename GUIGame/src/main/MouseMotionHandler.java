package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    GamePanel gamePanel;
    public boolean hover = false;
    public int mouseCheck;
    Rectangle rec;
    public MouseMotionHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        rec = new Rectangle(0,0, 5, 5);

    }
    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setRec(e.getX(), e.getY());

    }
    public void setRec(int x, int y){
        rec.x = x;
        rec.y = y;
    }
}
