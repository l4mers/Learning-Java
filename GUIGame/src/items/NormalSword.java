package items;

import main.GamePanel;

public class NormalSword extends Item{
    public NormalSword(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
        setIcon();
    }
    private void setDefaultValues(){
        name = "Rusty Sword";
        attackValue = 1;
        stackAble = false;
        toolTip = "ATTRIBUTE VALUES\nAttack +" + attackValue;

        attackAreaX = 35;
        attackAreaY = 35;

        //type
        type = weapon;
    }
    private void setIcon(){
        icon = super.setUp("/items/normal_sword", gamePanel.tileSize, gamePanel.tileSize);
    }
}
