package inputhandlers;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    GamePanel gp;
    public boolean shoot = false;
    public boolean left = false;
    public boolean right = false;

    public InputHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            shoot = true;
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = true;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = true;
        }

        if(key == KeyEvent.VK_ENTER){
            gp.setNextState();
        }
        if(key == KeyEvent.VK_ESCAPE){
            gp.setLastState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            shoot = false;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            right = false;
        }
    }
}
