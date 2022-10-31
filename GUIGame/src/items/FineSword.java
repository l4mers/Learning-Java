package items;

import main.GamePanel;

public class FineSword extends Item{
    public FineSword(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues(){
        name = "Fine Sword";
        attackValue = 2;
        stackAble = false;
        toolTip = "ATTRIBUTE VALUES\nAttack +" + attackValue;

        attackAreaX = 40;
        attackAreaY = 40;

        //type
        type = weapon;
    }
    private void setIcon(){
        icon = super.setUp("/items/fine_sword", gamePanel.tileSize, gamePanel.tileSize);
    }
}