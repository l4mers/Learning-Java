package world;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class WorldConstructor {

    GamePanel gamePanel;

    public Tile[] tiles;
    public int[][] world;

    public WorldConstructor(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tiles = new Tile[20];
        world = new int[gamePanel.maxWorldWidthInTiles][gamePanel.maxWorldHeightInTiles];

        getTileImage();
        loadMap("/maps/map000.txt");

    }
    public void getTileImage(){
            setUp(0, "0");
            setUp(1, "1");
            setUp(2, "2");
            setUp(3, "3");
            setUp(4, "4");
            setUp(5, "5");
            setUp(6, "6");
            setUp(7, "7");
            setUp(8, "8");
            setUp(9, "9");
            setUp(10, "10");
            setUp(11, "11");
            setUp(12, "12");
            setUp(13, "13");
            setUp(14, "14");
            setUp(15, "15");
            setUp(16, "16");
    }
    public void setUp(int index, String name){
        String imagePath = "/world_tiles/" + name + ".png";
        UtilityTool tool = new UtilityTool();
        try{
            tiles[index] = new Tile();
            tiles[index].image = tool.scaleImage(ImageIO.read(getClass().getResourceAsStream(imagePath)), gamePanel.tileSize, gamePanel.tileSize);
            if (index < 2){
                tiles[index].collision = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int width = 0;
            int height = 0;

            while(width < gamePanel.maxWorldWidthInTiles && height < gamePanel.maxWorldHeightInTiles){
            String line = bufferedReader.readLine();

                while (width < gamePanel.maxWorldWidthInTiles){

                    String[] tileNumbers = line.split(" ");
                    int number = Integer.parseInt(tileNumbers[width]);
                    world[width][height] = number;
                    width++;
                }
                if(width == gamePanel.maxWorldWidthInTiles){
                    width = 0;
                    height++;
                }
            }
            bufferedReader.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void drawTiles(Graphics2D g2){

        int worldWidth = 0;
        int worldHeight = 0;

        while (worldWidth < gamePanel.maxWorldWidthInTiles && worldHeight < gamePanel.maxWorldHeightInTiles){

            int tileNumber = world[worldWidth][worldHeight];

            int worldX = worldWidth * gamePanel.tileSize;
            int worldY = worldHeight * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
               worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
               worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
               worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                g2.drawImage(tiles[tileNumber].image, screenX, screenY, null);
            }

            worldWidth++;

            if (worldWidth  == gamePanel.maxWorldWidthInTiles){
                worldWidth = 0;
                worldHeight++;
            }
        }
    }
}
