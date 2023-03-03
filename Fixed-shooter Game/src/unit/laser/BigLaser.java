package unit.laser;

import main.Utility;

public class BigLaser extends Laser{
    public BigLaser(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("big_laser", size, size);

        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 11;
        hitBox.y = 6;
        hitBox.width = 42;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
