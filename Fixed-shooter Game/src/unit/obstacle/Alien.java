package unit.obstacle;

import main.Utility;

public class Alien extends Obstacle{
    public Alien(int size, int defaultY) {
        super(size, defaultY);

        image = new Utility().loadImage("slime_obstacle", size, size);
        defaultImage = image;
        setUpHitBox();
    }
    private void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 15;
        hitBox.width = 48;
        hitBox.height = 40;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }
}
