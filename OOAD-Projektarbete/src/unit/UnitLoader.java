package unit;

import main.GamePanel;
import unit.laser.Laser;
import unit.obstacle.Obstacle;

import java.util.Random;

public class UnitLoader {

    GamePanel gp;

    private int obstacleCount = 0;
    private int loopCount = 0;
    private int obstacleInterval = 50;
    private int obstacleSpeed = 1;
    private int obstacleID = 1;

    public UnitLoader(GamePanel gp){
        this.gp = gp;
    }

    public void update(){
        loopCount++;
        if (loopCount == obstacleInterval){
            loopCount = 0;
            gp.obstacles[obstacleCount] = gp.obstacleFactory.getObstacle(getObstacleID(), gp.size, -gp.size);
            gp.obstacles[obstacleCount].setObstacle(randomSpawnX(), randomSpeed());
            obstacleCount++;
            if (obstacleCount == 20){
                obstacleCount = 0;
            }
        }
    }
    public void setObstacleInterval(int interval){
        obstacleInterval = interval;
    }
    public void resetUnits(){
        for (Laser laser: gp.lasers
             ) {
            laser.reset();
        }
        for (Obstacle obstacle: gp.obstacles
        ) {
            obstacle.reset();
        }
    }

    private int randomSpeed() {
        Random rng = new Random();
        int randomNr = rng.nextInt(1, 101);
        if (randomNr < 25){
            return obstacleSpeed;
        } else if (randomNr < 50){
            return obstacleSpeed + 1;
        } else if (randomNr < 75){
            return obstacleSpeed + 2;
        } else {
            return obstacleSpeed + 3;
        }
    }

    private int randomSpawnX() {
        Random rng = new Random();
        return rng.nextInt(5, gp.width - gp.size);
    }

    public void setObstacleSpeed(int obstacleSpeed) {
        this.obstacleSpeed = obstacleSpeed;
    }
    private int getRandomObstacleID(int floor, int roof){
        Random rng = new Random();
        return rng.nextInt(floor, roof);
    }
    public void setObstacleID(int obstacleID){
        this.obstacleID = obstacleID;
    }
    private int getObstacleID(){
        switch (obstacleID){
            case 2 ->{
                return getRandomObstacleID(1, 3);
            }case 3 ->{
                return getRandomObstacleID(1, 4);
            }case 4 ->{
                return getRandomObstacleID(2, 4);
            }default ->{
                return obstacleID;
            }
        }
    }
}
