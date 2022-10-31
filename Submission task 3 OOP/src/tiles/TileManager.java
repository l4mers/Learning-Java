package tiles;

import main.GamePanel;
import scoreboard.Scoreboard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TileManager {

    public final ArrayList<GameTile> tileList;
    GamePanel gp;

    public TileManager(GamePanel gp){

        this.gp = gp;
        tileList = new ArrayList<>();

        loadTiles();
    }
    private void loadTiles() {
        //Path till foldern med bilder
        String path = "src/resources/images";
        //ArrayList med filvägar
        ArrayList<String> fileNames = new ArrayList<>();
        //Streamar alla namn på alla .png filer i foldern
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path),"*.{png}")){
            for (Path file: stream) {
                fileNames.add(file.toString());
            }
        }catch (IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }
        //Sorterar listan i bokstavsordning
        Collections.sort(fileNames);
        //Holder
        GameTile tile;
        //Lägger till gameTile objekt i listan
        for (int i = 0; i < fileNames.size(); i++) {
            //Den blanka brickan
            if (i == fileNames.size() - 1){
                tile = new GameTile(loadImage(fileNames.get(i)), true, i + 1, 0);
                tileList.add(tile);
            }else{
                tile = new GameTile(loadImage(fileNames.get(i)), false, i + 1, 0);
                tileList.add(tile);
            }
        }
    }
    public BufferedImage loadImage(String imagePath) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(imagePath));
        }catch (IOException e){
            e.printStackTrace();
        }
        return bi;
    }
}
