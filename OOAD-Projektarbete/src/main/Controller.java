package main;

import unit.Player;

public class Controller {
    int obstacleSpeed = 1;
    int obstacleSpawnInterval = 75;
    int obstacleID = 1;


    public void increaseDifficulty(GamePanel gp){
        if(gp.player.score < 50){
            obstacleSpeed = 1;
            obstacleSpawnInterval = 75;
            obstacleID = 1;
        }else if (gp.player.score < 100){
            obstacleSpeed = 2;
            obstacleSpawnInterval = 60;
            obstacleID = 2;
        }else if (gp.player.score < 150){
            obstacleSpeed = 3;
            obstacleSpawnInterval = 45;
            obstacleID = 3;
        }else if (gp.player.score < 200){
            obstacleSpeed = 4;
            obstacleSpawnInterval = 30;
            obstacleID = 4;
        }else if (gp.player.score < 300){
            obstacleSpeed = 5;
            obstacleSpawnInterval = 15;
            obstacleID = 5;
        }else if (gp.player.score < 400){
            obstacleSpeed = 6;
            obstacleSpawnInterval = 15;
            obstacleID = 5;
        }else if (gp.player.score < 500){
            obstacleSpeed = 7;
            obstacleSpawnInterval = 15;
            obstacleID = 5;
        }

        gp.unitLoader.setObstacleSpeed(obstacleSpeed);
        gp.unitLoader.setObstacleInterval(obstacleSpawnInterval);
        gp.unitLoader.setObstacleID(obstacleID);
    }
    public void setPlayerPowerUp(int lootID, Player player) {
        switch (lootID){
            case 1 -> player.powerUp();
            case 2 -> player.setLaserID(1);
            case 3 -> player.setLaserID(2);
            default -> player.setLaserID(3);
        }
    }
}