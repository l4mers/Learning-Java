package unit.laser;

import main.Utility;

public class SmallLaser extends Laser{

    public SmallLaser(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("small_laser", size, size);

        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 29;
        hitBox.y = 15;
        hitBox.width = 7;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
