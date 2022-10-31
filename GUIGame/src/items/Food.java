package items;

import main.GamePanel;

public class Food extends Item {
    public Food(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues() {
        name = "Food";
        stackAble = false;
        toolTip = "Increase run speed\nBut be aware, it's spicy!";

        //type
        type = consume;
    }
    private void setIcon(){
        icon = super.setUp("/items/food", gamePanel.tileSize, gamePanel.tileSize);
    }
    public void consume(){
        gamePanel.player.speed += 1;
    }
}
