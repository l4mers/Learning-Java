package factory;

import main.Utility;
import unit.obstacle.Alien;
import unit.obstacle.Meteorite;
import unit.obstacle.Obstacle;
import unit.obstacle.UFO;

import java.awt.image.BufferedImage;

public class ObstacleFactory {

    public Obstacle getObstacle(int id, int size, int defaultY){
        switch (id){
            case 1 ->{
                return new Meteorite(size, defaultY);
            }case 2 ->{
                return new UFO(size, defaultY);
            }default ->{
                return new Alien(size, defaultY);
            }
        }
    }

    public BufferedImage getLootImage(int id, int width, int height) {
        Utility tool = new Utility();
        switch (id){
            case 1 ->{
                return tool.getSubImage(tool.loadImage("powerups", width * 2, height * 2), 1, 1, width, height);
            }case 2 ->{
                return tool.getSubImage(tool.loadImage("powerups", width * 2, height * 2), 2, 1, width, height);
            }case 3 ->{
                return tool.getSubImage(tool.loadImage("powerups", width * 2, height * 2), 1, 2, width, height);
            }case 4 ->{
                return tool.getSubImage(tool.loadImage("powerups", width * 2, height * 2), 2, 2, width, height);
            }default ->{
                return null;
            }
        }
    }
}
