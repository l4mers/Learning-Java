package unit;

import main.GamePanel;
import inputhandlers.InputHandler;
import main.Utility;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;

    public int score = 0;
    public String name;

    //Shooting
    private int laserCount = 0;
    private int laserCoolDown = 0;
    private int shootInterval;
    boolean shoot = false;
    private int laserID = 1;

    public Player(InputHandler inputHandler, int size, int positionY){
        this.inputHandler = inputHandler;
        image = new Utility().loadImage("player", size, size);

        this.positionY = positionY;
        positionX = size * 5;

        setUpHitBox();
        setUpBaseStats();
    }
    public void setUpBaseStats(){
        //antal pixlar
        speed = 3;
        shootInterval = 25;
        alive = true;
        score = 0;
    }
    private void setUpHitBox(){
        hitBox.x = 6;
        hitBox.y = 22;
        hitBox.width = 48;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }

    public void update(GamePanel gp) {
        if(shoot){
            laserCoolDown++;
            if(laserCoolDown == shootInterval){
                shoot = false;
                laserCoolDown = 0;
            }
        } else if(inputHandler.shoot){
            gp.dj.playSoundEffect(1);
            shoot = true;
            gp.lasers[laserCount] = gp.laserFactory.getLaser(laserID, gp.lasers[laserCount].getSize(), gp.lasers[laserCount].getDefaultY());
            gp.lasers[laserCount].setUpLaser(positionX - 1, 7);
            laserCount++;
            if (laserCount == 10){
                laserCount = 0;
            }
        }
        //om spelaren trycker vänster så minskar positionen med antal pixlar speed //samma med höger fast ökar speed
        if (inputHandler.left){
            if(positionX > gp.size / 8){
                positionX -= speed;
            }
        } else if (inputHandler.right){
            //om x blir större än bredden så förflyttas man inte för att inte hamna utanför rutan
            if(positionX < gp.width - (gp.size + gp.size / 8)){
                positionX += speed;
            }
        }
        updateHitBox();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, positionX, positionY, null);
    }
    public void incrementScore(){
        score++;
    }
    public void resetScore(){
        score = 0;
    }
    public int getScore(){
        return score;
    }
    public void powerUp(){
        if (speed != 10){
            speed++;
            shootInterval--;
        }
    }
    public void setLaserID(int id){
        laserID = id;
    }
}
