package items;

import main.GamePanel;

public class Key extends Item{
    public Key(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues() {
        name = "Key";
        stackAble = false;
        toolTip = "Unlock doors";
    }
    private void setIcon(){
        icon = super.setUp("/items/key", gamePanel.tileSize, gamePanel.tileSize);
    }
}
