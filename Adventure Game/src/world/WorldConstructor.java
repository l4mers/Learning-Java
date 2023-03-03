package world;

import interactions.AssetDealer;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class WorldConstructor {

    public String worldFolder;
    public String assetFolder;
    GamePanel gp;

    public ArrayList<WorldPiece> pieces;
    public int[][] world;

    boolean con;

    public WorldConstructor(GamePanel gp, boolean con){
        this.gp = gp;
        this.con = con;
        pieces = new ArrayList<>();
        world = new int[gp.worldWidth][gp.worldHeight];

        if(con){
            worldFolder = "src/resource/map/worlds/test_world.txt";
            assetFolder = "src/resource/world/tiles/summer";
        } else {
            assetFolder = "src/resource/world/world_objects";
            worldFolder = "src/resource/map/world_objects/test_world_objects.txt";
        }

        createAssets();
        loadMap();
    }
    public void createAssets() {
        //pieces.clear();
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(assetFolder),"*.{png}")){
            for (Path file: stream) {
                if (file.toString().endsWith("c.png")){
                    pieces.add(new WorldPiece(loadImage(file.toString()), true));
                }else{
                    pieces.add(new WorldPiece(loadImage(file.toString()), false));
                }
            }
        }catch (IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }
    }

    public void loadMap() {
        AssetDealer.testmetod(worldFolder, world);
    }
    public void draw(Graphics g2){
        int worldWidth = 0;
        int worldHeight = 0;

        while (worldWidth < gp.worldWidth && worldHeight < gp.worldHeight){
            int tileNumber = world[worldWidth][worldHeight];

            int worldX = worldWidth * gp.tileSize;
            int worldY = worldHeight * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                if(con){
                    g2.drawImage(pieces.get(tileNumber).image, screenX, screenY, null);
                } else {
                    if (tileNumber != 0){
                        g2.drawImage(pieces.get(tileNumber).image, screenX, screenY, null);
                    }
                }
            }

            worldWidth++;
            if (worldWidth  == gp.worldWidth){
                worldWidth = 0;
                worldHeight++;
            }
        }
    }

    private BufferedImage loadImage(String path){
        UtilityTool ut = new UtilityTool();
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        return ut.scaleImage(bi, gp.tileSize, gp.tileSize);
    }
}
