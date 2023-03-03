package unit.obstacle;

import main.Utility;

public class Meteorite extends Obstacle{

    public Meteorite(int size, int defaultY) {
        super(size, defaultY);

        image = new Utility().loadImage("meteorite_obstacle", size, size);
        defaultImage = image;
        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 15;
        hitBox.width = 40;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
