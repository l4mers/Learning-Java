package unit.laser;

import main.Utility;
import unit.Unit;

import java.awt.*;

public class Laser extends Unit {

    private final int defaultY;

    public Laser(int size, int defaultY){
        image = new Utility().loadImage("small_laser", size, size);
        this.size = size;

        this.defaultY = defaultY;
        positionY = defaultY;

        //setUpHitBox();
    }
    public void setUpLaser(int positionX, int speed){
        super.positionX = positionX;
        super.speed = speed;
        alive = true;
    }
    private void setUpHitBox(){
        hitBox.x = 29;
        hitBox.y = 15;
        hitBox.width = 7;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
    public void update(){
        if(alive){
            if(positionY <= 0){
                alive = false;
                positionY = defaultY;
            } else {
                positionY -= speed;
            }
            updateHitBox();
        }
    }
    public void draw(Graphics2D g2){
        if(alive){
            g2.drawImage(image, positionX, positionY, null);
        }
    }
    public void reset(){
        alive = false;
        positionY = defaultY;
    }

    public int getSize() {
        return this.size;
    }
    public int getDefaultY(){
        return this.defaultY;
    }
}
