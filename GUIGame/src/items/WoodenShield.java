package items;

import main.GamePanel;

public class WoodenShield extends Item{
    public WoodenShield(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues(){
        name = "Wooden Shield";
        defenseValue = 1;
        stackAble = false;
        toolTip = "ATTRIBUTE VALUES\nDefense: " + defenseValue;

        //type
        type = offHand;
    }
    private void setIcon(){
        icon = super.setUp("/items/wooden_shield", gamePanel.tileSize, gamePanel.tileSize);
    }
}
