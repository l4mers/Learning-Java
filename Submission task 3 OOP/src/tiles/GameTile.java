package tiles;

import java.awt.image.BufferedImage;

public class GameTile {
    private final BufferedImage image;
    public Boolean isBlank;
    private final int ID;
    private int currentPos;

    public GameTile(BufferedImage image, Boolean isBlank, int ID, int currentPos) {
        this.image = image;
        this.isBlank = isBlank;
        this.ID = ID;
        this.currentPos = currentPos;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getID() {
        return ID;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }
}
