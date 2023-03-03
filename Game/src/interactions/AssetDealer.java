package interactions;

import entity.foes.Slime;
import entity.npc.Knight;
import entity.npc.Merchant;
import main.GamePanel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AssetDealer {

    GamePanel gp;

    public AssetDealer(GamePanel gp){
        this.gp = gp;
    }

    public void loadAssets(String assetPath){
        int[][] world = new int[gp.worldWidth][gp.worldHeight];
        testmetod(assetPath, world);

        int col = 0;
        int row = 0;

        while (col < gp.worldWidth && row < gp.worldHeight){
            int assetID = world[col][row];
            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;

            switch (assetID){
                case 1 ->{
                    Knight knight = new Knight(gp);
                    knight.worldX = worldX;
                    knight.worldY = worldY;
                    gp.npc.add(knight);
                }
                case 2 ->{
                    Slime slime = new Slime(gp);
                    slime.worldX = worldX;
                    slime.worldY = worldY;
                    gp.foe.add(slime);
                }
                case 3 ->{
                    Merchant merchant = new Merchant(gp);
                    merchant.worldX = worldX;
                    merchant.worldY = worldY;
                    gp.npc.add(merchant);
                }
            }
            col++;
            if (col  == gp.worldWidth){
                col = 0;
                row++;
            }
        }
    }
    public static void testmetod(String assetPath, int[][] world) {
        AtomicInteger height = new AtomicInteger(0);
        try(Stream<String> stream = Files.lines(Paths.get(assetPath))){

            stream.forEach(line -> {
                String[] nums = line.split(" ");
                int width = 0;
                for (String num : nums) {
                    int n = Integer.parseInt(num);
                    world[width][height.intValue()] = n;
                    width++;
                }
                height.getAndIncrement();
            });
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
