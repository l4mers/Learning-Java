package interactions;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class InteractionValidator {

    GamePanel gp;

    public InteractionValidator(GamePanel gp) {
        this.gp = gp;
    }

    public void validateTile(Entity entity) {

        int leftX = entity.worldX + entity.hitBox.x;
        int rightX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int topY = entity.worldY + entity.hitBox.y;
        int bottomY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int hitBoxWest = leftX / gp.tileSize;
        int hitBoxEast = rightX / gp.tileSize;
        int hitBoxNorth = topY / gp.tileSize;
        int hitBoxSouth = bottomY / gp.tileSize;

        int tileNumOne, tileNumTwo;

        switch (entity.direction) {
            case "north" -> {
                hitBoxNorth = (topY - entity.speed) / gp.tileSize;
                tileNumOne = gp.objConstructor.world[hitBoxWest][hitBoxNorth];
                tileNumTwo = gp.objConstructor.world[hitBoxEast][hitBoxNorth];
                if (gp.objConstructor.pieces.get(tileNumOne).collision || gp.objConstructor.pieces.get(tileNumTwo).collision) {
                    entity.collisionOn = true;
                }
            }
            case "south" -> {
                hitBoxSouth = (bottomY - entity.speed) / gp.tileSize;
                tileNumOne = gp.objConstructor.world[hitBoxWest][hitBoxSouth];
                tileNumTwo = gp.objConstructor.world[hitBoxEast][hitBoxSouth];
                if (gp.objConstructor.pieces.get(tileNumOne).collision || gp.objConstructor.pieces.get(tileNumTwo).collision) {
                    entity.collisionOn = true;
                }
            }
            case "west" -> {
                hitBoxWest = (leftX - entity.speed) / gp.tileSize;
                tileNumOne = gp.objConstructor.world[hitBoxWest][hitBoxNorth];
                tileNumTwo = gp.objConstructor.world[hitBoxWest][hitBoxSouth];
                if (gp.objConstructor.pieces.get(tileNumOne).collision || gp.objConstructor.pieces.get(tileNumTwo).collision) {
                    entity.collisionOn = true;
                }
            }
            case "east" -> {
                hitBoxEast = (rightX - entity.speed) / gp.tileSize;
                tileNumOne = gp.objConstructor.world[hitBoxEast][hitBoxNorth];
                tileNumTwo = gp.objConstructor.world[hitBoxEast][hitBoxSouth];
                if (gp.objConstructor.pieces.get(tileNumOne).collision || gp.objConstructor.pieces.get(tileNumTwo).collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.objects.size(); i++) {

            if (gp.objects.get(i) != null) {

                //Mobilens hit box
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                //föremålets hit box
                gp.objects.get(i).hitBox.x = gp.objects.get(i).worldX + gp.objects.get(i).hitBox.x;
                gp.objects.get(i).hitBox.y = gp.objects.get(i).worldY + gp.objects.get(i).hitBox.y;

                direction(entity);

                if (entity.hitBox.intersects(gp.objects.get(i).hitBox)) {
                    if (gp.objects.get(i).collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gp.objects.get(i).hitBox.x = gp.objects.get(i).hitBoxDefaultX;
                gp.objects.get(i).hitBox.y = gp.objects.get(i).hitBoxDefaultY;
            }
        }
        return index;
    }

    public int checkMobile(Entity entity, ArrayList<Entity> target) {

        int index = 999;

        for (int i = 0; i < target.size(); i++) {

            if (target.get(i) != null) {

                //player hit box
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                //mobiles hit box
                target.get(i).hitBox.x = target.get(i).worldX + target.get(i).hitBox.x;
                target.get(i).hitBox.y = target.get(i).worldY + target.get(i).hitBox.y;

                direction(entity);

                if (entity.hitBox.intersects(target.get(i).hitBox)) {
                    //Om mobilen inte är sig själv ingen collision
                    if (target.get(i) != entity) {
                        if (target.get(i).dead) {
                            entity.collisionOn = false;
                        } else {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target.get(i).hitBox.x = target.get(i).hitBoxDefaultX;
                target.get(i).hitBox.y = target.get(i).hitBoxDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){

        boolean contactPlayer = false;

        //player hit box
        entity.hitBox.x = entity.worldX + entity.hitBox.x;
        entity.hitBox.y = entity.worldY + entity.hitBox.y;
        //mobiles hit box
        gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
        gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;

        direction(entity);

        if(entity.hitBox.intersects(gp.player.hitBox)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        gp.player.hitBox.x = gp.player.hitBoxDefaultX;
        gp.player.hitBox.y = gp.player.hitBoxDefaultY;

        return contactPlayer;
    }
    private void direction(Entity entity) {
        switch (entity.direction) {
            case "north" -> entity.hitBox.y -= entity.speed;
            case "south" -> entity.hitBox.y += entity.speed;
            case "west" -> entity.hitBox.x -= entity.speed;
            case "east" -> entity.hitBox.x += entity.speed;
        }
    }
}