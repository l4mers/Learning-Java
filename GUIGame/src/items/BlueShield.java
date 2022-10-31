package items;

import main.GamePanel;

public class BlueShield extends Item{
    public BlueShield(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues(){
        name = "Blue Shield";
        defenseValue = 2;
        stackAble = false;
        toolTip = "ATTRIBUTE VALUES\nDefense: " + defenseValue;

        //type
        type = offHand;
    }
    private void setIcon(){
        icon = super.setUp("/items/blue_shield", gamePanel.tileSize, gamePanel.tileSize);
    }
}