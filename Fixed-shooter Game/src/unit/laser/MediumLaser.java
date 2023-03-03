package unit.laser;

import main.Utility;

public class MediumLaser extends Laser{
    public MediumLaser(int size, int defaultY) {
        super(size, defaultY);
        image = new Utility().loadImage("medium_laser", size, size);

        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 17;
        hitBox.y = 3;
        hitBox.width = 29;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
