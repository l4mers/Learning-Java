package main;

import entity.Entity;

public class HitBoxChecker {

    GamePanel gamePanel;

    public HitBoxChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }
    public void checkTile(Entity entity){

        int mobileLeftWorldX = entity.worldX + entity.hitBox.x;
        int mobileRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int mobileTopWorldY = entity.worldY + entity.hitBox.y;
        int mobileBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int hitBoxWest = mobileLeftWorldX / gamePanel.tileSize;
        int hitBoxEast = mobileRightWorldX / gamePanel.tileSize;
        int hitBoxNorth = mobileTopWorldY / gamePanel.tileSize;
        int hitBoxSouth = mobileBottomWorldY / gamePanel.tileSize;

        int tileNumOne, tileNumTwo;

        switch (entity.direction){
            case "north" -> {
                hitBoxNorth = (mobileTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNumOne = gamePanel.worldConstructor.world[hitBoxWest][hitBoxNorth];
                tileNumTwo = gamePanel.worldConstructor.world[hitBoxEast][hitBoxNorth];
                if (gamePanel.worldConstructor.tiles[tileNumOne].collision || gamePanel.worldConstructor.tiles[tileNumTwo].collision){
                    entity.collisionOn = true;
                }
            }
            case "south" ->{
                hitBoxSouth = (mobileBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNumOne = gamePanel.worldConstructor.world[hitBoxWest][hitBoxSouth];
                tileNumTwo = gamePanel.worldConstructor.world[hitBoxEast][hitBoxSouth];
                if (gamePanel.worldConstructor.tiles[tileNumOne].collision || gamePanel.worldConstructor.tiles[tileNumTwo].collision){
                    entity.collisionOn = true;
                }
            }
            case "west" ->{
                hitBoxWest = (mobileLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNumOne = gamePanel.worldConstructor.world[hitBoxWest][hitBoxNorth];
                tileNumTwo = gamePanel.worldConstructor.world[hitBoxWest][hitBoxSouth];
                if (gamePanel.worldConstructor.tiles[tileNumOne].collision || gamePanel.worldConstructor.tiles[tileNumTwo].collision){
                    entity.collisionOn = true;
                }
            }
            case "east" ->{
                hitBoxEast = (mobileRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNumOne = gamePanel.worldConstructor.world[hitBoxEast][hitBoxNorth];
                tileNumTwo = gamePanel.worldConstructor.world[hitBoxEast][hitBoxSouth];
                if (gamePanel.worldConstructor.tiles[tileNumOne].collision || gamePanel.worldConstructor.tiles[tileNumTwo].collision){
                    entity.collisionOn = true;
                }
            }
        }
    }
    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; i++) {

            if(gamePanel.objects[i] != null){

                //Mobilens hit box
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                //föremålets hit box
                gamePanel.objects[i].hitBox.x = gamePanel.objects[i].worldX + gamePanel.objects[i].hitBox.x;
                gamePanel.objects[i].hitBox.y = gamePanel.objects[i].worldY + gamePanel.objects[i].hitBox.y;

                switch(entity.direction){
                    case "north" -> entity.hitBox.y -= entity.speed;
                    case "south" -> entity.hitBox.y += entity.speed;
                    case "west" ->entity.hitBox.x -= entity.speed;
                    case "east" -> entity.hitBox.x += entity.speed;
                }

                if(entity.hitBox.intersects(gamePanel.objects[i].hitBox)){
                    if (gamePanel.objects[i].collision){
                        entity.collisionOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }

                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gamePanel.objects[i].hitBox.x = gamePanel.objects[i].hitBoxDefaultX;
                gamePanel.objects[i].hitBox.y = gamePanel.objects[i].hitBoxDefaultY;
            }
        }
        return index;
    }
    //NPC and FOEs
    public int checkMobile(Entity entity, Entity[] target){

        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if(target[i] != null){

                //player hit box
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                //mobiles hit box
                target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
                target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;

                switch(entity.direction){
                    case "north" -> entity.hitBox.y -= entity.speed;
                    case "south" -> entity.hitBox.y += entity.speed;
                    case "west" -> entity.hitBox.x -= entity.speed;
                    case "east" ->entity.hitBox.x += entity.speed;
                }

                if(entity.hitBox.intersects(target[i].hitBox)){
                    //Om mobilen inte är sig själv ingen collision
                    if (target[i] != entity){
                        if (target[i].dead){
                            entity.collisionOn = false;
                        }else{
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target[i].hitBox.x = target[i].hitBoxDefaultX;
                target[i].hitBox.y = target[i].hitBoxDefaultY;
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
        gamePanel.player.hitBox.x = gamePanel.player.worldX + gamePanel.player.hitBox.x;
        gamePanel.player.hitBox.y = gamePanel.player.worldY + gamePanel.player.hitBox.y;

        switch(entity.direction){
            case "north" -> entity.hitBox.y -= entity.speed;
            case "south" -> entity.hitBox.y += entity.speed;
            case "west" -> entity.hitBox.x -= entity.speed;
            case "east" ->entity.hitBox.x += entity.speed;
        }

        if(entity.hitBox.intersects(gamePanel.player.hitBox)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        gamePanel.player.hitBox.x = gamePanel.player.hitBoxDefaultX;
        gamePanel.player.hitBox.y = gamePanel.player.hitBoxDefaultY;

        return contactPlayer;
    }
    public boolean contactGrid(UI ui, MouseMotionHandler mm){
        return ui.rec.intersects(mm.rec);
    }
}
