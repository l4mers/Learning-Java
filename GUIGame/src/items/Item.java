package items;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item {

    public GamePanel gamePanel;
    public BufferedImage icon;

    public String name;
    public String toolTip;
    public int requiredLevel;
    public int strengthValue;
    public int dexterityValue;
    public int intelligentValue;
    public int attackValue;
    public int defenseValue;
    public int coinValue;

    public int attackAreaX, attackAreaY;

    public boolean stackAble;
    public int stack = 0;
    public int type;
    public int weapon = 0;
    public int offHand = 1;
    public int helm = 2;
    public int armor = 3;
    public int hands = 4;
    public int boots = 5;
    public int jewelry = 6;
    public int consume = 7;
    public int other = 8;


    public Item(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public int getStrengthValue() {
        return strengthValue;
    }

    public void setStrengthValue(int strengthValue) {
        this.strengthValue = strengthValue;
    }

    public int getDexterityValue() {
        return dexterityValue;
    }

    public void setDexterityValue(int dexterityValue) {
        this.dexterityValue = dexterityValue;
    }

    public int getIntelligentValue() {
        return intelligentValue;
    }

    public void setIntelligentValue(int intelligentValue) {
        this.intelligentValue = intelligentValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public int getStack() {
        return stack;
    }

    public void consume(){

    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public BufferedImage setUp(String name, int width, int height){
        String imagePath = name + ".png";
        UtilityTool tool = new UtilityTool();
        BufferedImage scaled = null;
        try{
            scaled = tool.scaleImage(ImageIO.read(getClass().getResourceAsStream(imagePath)), width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaled;
    }
}
