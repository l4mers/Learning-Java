package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    //Scalar bilderna innan de ritas istället för när dom ritas
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        //Skapar en blankt objekt
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        //sparar storleken för bilden
        Graphics2D g2 = scaledImage.createGraphics();
        //ritar bild
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
