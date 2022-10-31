package main;

import entity.Entity;
import entity.ItemAsEntity;
import entity.MobileKnight;
import foe.MobileRedSlime;
import items.BlueShield;
import items.FineSword;
import items.Key;
import object.OBJ_Key;


public class AssetDealer {
    GamePanel gamePanel;

    public AssetDealer(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        gamePanel.objects[0] = new Entity(new Key(gamePanel));
        gamePanel.objects[0].worldX = gamePanel.tileSize * 27;
        gamePanel.objects[0].worldY = gamePanel.tileSize * 25;

        gamePanel.objects[1] = new Entity(new FineSword(gamePanel));
        gamePanel.objects[1].worldX = gamePanel.tileSize * 40;
        gamePanel.objects[1].worldY = gamePanel.tileSize * 40;

        gamePanel.objects[2] = new Entity(new BlueShield(gamePanel));
        gamePanel.objects[2].worldX = gamePanel.tileSize * 10;
        gamePanel.objects[2].worldY = gamePanel.tileSize * 40;
    }
    public void setNPC(){
        gamePanel.npc[0] = new MobileKnight(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 26;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 24;

        gamePanel.npc[1] = new MobileKnight(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 30;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 30;
    }
    public void setFoe(){
        gamePanel.foe[0] = new MobileRedSlime(gamePanel);
        gamePanel.foe[0].worldX = gamePanel.tileSize * 20;
        gamePanel.foe[0].worldY = gamePanel.tileSize * 35;

        gamePanel.foe[1] = new MobileRedSlime(gamePanel);
        gamePanel.foe[1].worldX = gamePanel.tileSize * 21;
        gamePanel.foe[1].worldY = gamePanel.tileSize * 39;
    }
}
