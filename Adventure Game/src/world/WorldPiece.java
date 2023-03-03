package world;

import java.awt.image.BufferedImage;

public class WorldPiece {
    public BufferedImage image;
    public boolean collision;

    public WorldPiece(BufferedImage image, boolean collision){
        this.image = image;
        this.collision = collision;
    }
}
