package main;

import ui.EndState;
import unit.Unit;

public class CollisionHandler {

    GamePanel gp;

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public boolean checkObstacle(Unit unit){

        for (int i = 0; i < gp.lasers.length; i++) {
            if(gp.lasers[i].alive){
                if (gp.lasers[i].hitBox.intersects(unit.hitBox)){
                    if(!unit.hasLoot) {
                        gp.lasers[i].reset();
                        gp.player.incrementScore();
                        return true;
                    }
                }
            }
        }
        if (gp.player.hitBox.intersects(unit.hitBox)){
            if(unit.hasLoot){
                gp.controller.setPlayerPowerUp(unit.getLootID(), gp.player);
                unit.alive = false;
                return true;
            }else {
                gp.dj.stopMusic();
                gp.dj.playSoundEffect(2);
                unit.alive = false;
                gp.setState(new EndState());
                return true;
            }
        }
        return false;
    }
}
