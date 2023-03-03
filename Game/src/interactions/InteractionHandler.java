package interactions;

import main.GamePanel;

public class InteractionHandler {

    GamePanel gp;
    InteractionHitBox[][] hitBoxes;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public InteractionHandler(GamePanel gp){
        this.gp = gp;

        hitBoxes = new InteractionHitBox[gp.worldWidth][gp.worldHeight];

        int width = 0;
        int height = 0;
        while(width < gp.worldWidth && height < gp.worldHeight){
            hitBoxes[width][height] = new InteractionHitBox();
            hitBoxes[width][height].x = 23;
            hitBoxes[width][height].y = 23;
            hitBoxes[width][height].width = 2;
            hitBoxes[width][height].height = 2;
            hitBoxes[width][height].hitBoxDefaultX = hitBoxes[width][height].x;
            hitBoxes[width][height].hitBoxDefaultY = hitBoxes[width][height].y;

            width++;
            if (width == gp.worldWidth){
                width = 0;
                height ++;
            }
        }
    }
    public void checkEvent(){
        resetEvent();
        if(canTouchEvent){
            //LÄGG TILL HÄNDELSE METODER HÄR
//            if(hit(26,24, "east")){damagePit(26, 25, 3);}
//            if(hit(27,25, "any")) {healingPool(27,25,3);}
            //if(hit(x, y, "any")) {teleport(gamstate index here)}
        }
    }
    public boolean hit(int width, int height, String reqDirection){

        boolean hit = false;

        gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
        gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;
        hitBoxes[width][height].x = width * gp.tileSize + hitBoxes[width][height].x;
        hitBoxes[width][height].y = height * gp.tileSize + hitBoxes[width][height].y;

        if(gp.player.hitBox.intersects(hitBoxes[width][height]) && !hitBoxes[width][height].done){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.hitBox.x = gp.player.hitBoxDefaultX;
        gp.player.hitBox.y = gp.player.hitBoxDefaultY;
        hitBoxes[width][height].x = hitBoxes[width][height].hitBoxDefaultX;
        hitBoxes[width][height].y = hitBoxes[width][height].hitBoxDefaultY;

        return hit;
    }
    //LÄGG TILL HÄNDERLSER HÄR

    private void resetEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }
    }
}
