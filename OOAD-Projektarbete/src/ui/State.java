package ui;

import main.GamePanel;

import java.awt.*;

public class State {
    private GameState currentState;

    public void draw(Graphics2D g2, GamePanel gp){
        currentState.draw(g2, gp);
    }
    public void update(GamePanel gp){
        currentState.update(gp);
    }
    public void setCurrentGameState(GameState currentScreen){
        this.currentState = currentScreen;
    }
    public void setNextState(){
        setCurrentGameState(currentState.setNext());
    }
    public void setLastState() {
        setCurrentGameState(currentState.setLast());
    }
}
