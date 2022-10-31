package main;

public class EventHandler {
    GamePanel gamePanel;
    EventHitBox[][] eventHitBox;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventHitBox = new EventHitBox[gamePanel.maxWorldWidthInTiles][gamePanel.maxWorldHeightInTiles];

        int width = 0;
        int height = 0;
        while(width < gamePanel.maxWorldWidthInTiles && height < gamePanel.maxWorldHeightInTiles){
            //Set the rec area
            eventHitBox[width][height] = new EventHitBox();
            eventHitBox[width][height].x = 23;
            eventHitBox[width][height].y = 23;
            eventHitBox[width][height].width = 2;
            eventHitBox[width][height].height = 2;
            eventHitBox[width][height].eventHitBoxDefaultX = eventHitBox[width][height].x;
            eventHitBox[width][height].eventHitBoxDefaultY = eventHitBox[width][height].y;

            width++;
            if (width == gamePanel.maxWorldWidthInTiles){
                width = 0;
                height ++;
            }
        }
    }

    public void checkEvent(){

        // check if the player is more than one tile away from last event
        playerMoreThanOneTileAway();

        if(canTouchEvent){
            if(hit(26,24, "east")){damagePit(26, 25, 3);}
            if(hit(27,25, "any")) {healingPool(27,25,3);}
            //if(hit(x, y, "any")) {teleport(gamstate index here)}
        }
    }
    public boolean hit(int width, int height, String reqDirection){

        boolean hit = false;

        gamePanel.player.hitBox.x = gamePanel.player.worldX + gamePanel.player.hitBox.x;
        gamePanel.player.hitBox.y = gamePanel.player.worldY + gamePanel.player.hitBox.y;
        eventHitBox[width][height].x = width*gamePanel.tileSize + eventHitBox[width][height].x;
        eventHitBox[width][height].y = height*gamePanel.tileSize + eventHitBox[width][height].y;

        if(gamePanel.player.hitBox.intersects(eventHitBox[width][height]) && !eventHitBox[width][height].eventDone){
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }
        gamePanel.player.hitBox.x = gamePanel.player.hitBoxDefaultX;
        gamePanel.player.hitBox.y = gamePanel.player.hitBoxDefaultY;
        eventHitBox[width][height].x = eventHitBox[width][height].eventHitBoxDefaultX;
        eventHitBox[width][height].y = eventHitBox[width][height].eventHitBoxDefaultY;

        return hit;
    }
    public void teleport(int gameState){
        gamePanel.gameState(gameState);
        gamePanel.ui.currentDialog = "Teleport!";
        gamePanel.player.worldX = gamePanel.tileSize*37;
        //gamePanel.player.worly = gamePanel.tileSize*20;
    }
    public void damagePit(int width, int height, int gameState){

        //Gör att något bara kan hända en gång
        //if (!eventHitBox[width][height].eventDone)
        gamePanel.gameState(gameState);
        gamePanel.ui.currentDialog = "You fall into a pit!";
        gamePanel.player.currentHealth -= 1;
        if(gamePanel.player.currentHealth < 0){
            gamePanel.player.currentHealth = 0;
        }
        canTouchEvent = false;
        //Gör att något bara kan hända en gång
        //eventHitBox[width][height].eventDone = true;
    }
    public void healingPool(int width, int height, int gameState){

        //Gör att något bara kan hända en gång
        //if (!eventHitBox[width][height].eventDone)
        if(gamePanel.inputHandler.interact){
            gamePanel.gameState(gameState);
            gamePanel.ui.currentDialog = "You have been healed!";
            gamePanel.player.currentHealth = gamePanel.player.maxHealth;
        }
        //Gör att något bara kan hända en gång
        //eventHitBox[width][height].eventDone = true;
    }
    public void playerMoreThanOneTileAway(){

        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize){
            canTouchEvent = true;
        }

    }
}
