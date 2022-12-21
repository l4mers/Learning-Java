package unit.obstacle;

import main.Utility;

public class UFO extends Obstacle{
    public UFO(int size, int defaultY) {
        super(size, defaultY);

        image = new Utility().loadImage("UFO_obstacle", size, size);
        defaultImage = image;
        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 2;
        hitBox.y = 15;
        hitBox.width = 58;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
