package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

public class TileManager {

    public final ArrayList<GameTile> tileList;


    public TileManager(){
        tileList = new ArrayList<>();

        loadTiles();
    }
    private void loadTiles() {
        String path = "src/resources/images";
        ArrayList<String> fileNames = new ArrayList<>();
        //ADDS ALL NAMES FROM ALL .PNG FILES IN FOLDER
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path),"*.{png}")){
            for (Path file: stream) {
                fileNames.add(file.toString());
            }
        }catch (IOException | DirectoryIteratorException e){
            e.printStackTrace();
        }

        Collections.sort(fileNames);
        for (int i = 0; i < fileNames.size(); i++) {
            if (i == fileNames.size() - 1){
                tileList.add(new GameTile(loadImage(fileNames.get(i)), true, i + 1, 0));
            }else{
                tileList.add(new GameTile(loadImage(fileNames.get(i)), false, i + 1, 0));
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
