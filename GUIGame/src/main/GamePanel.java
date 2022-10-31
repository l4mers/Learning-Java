package main;

import entity.Entity;
import entity.Player;
import world.WorldConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tiles for object etc (standard)
    final int scale = 3; // used for scaling game (standard)

    public final int tileSize = originalTileSize * scale; // 48x48 tile actual size
    public final int maxScreenWidthInTiles  = 16; //total size på bredden maxScreenWidthInTiles
    public final int maxScreenHeightInTiles = 12; // på höjden
    public final int screenWidth = tileSize * maxScreenWidthInTiles; // 768 pix
    public final int screenHeight = tileSize * maxScreenHeightInTiles; // 576 pix

    // WORLD SETTINGS
    public int maxWorldWidthInTiles = 50;
    public int maxWorldHeightInTiles = 50;
//    public int worldWidth = tileSize * maxWorldWidthInTiles;
//    public int worldHeight = tileSize * maxWorldHeightInTiles;

    // FPS
    double FPS = 60; //*** ändra till double om det behövs!

    // SYSTEM
    WorldConstructor worldConstructor = new WorldConstructor(this);
    public InputHandler inputHandler = new InputHandler(this);
    public MouseInputHandler mouseInputHandler = new MouseInputHandler(this);
    public MouseMotionHandler mouseMotionHandler = new MouseMotionHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public HitBoxChecker hitBoxChecker = new HitBoxChecker(this);
    public AssetDealer assetDealer = new AssetDealer(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    //MOBILES / OBJECTS
    public Player player = new Player(this, inputHandler);
    public Entity[] objects = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] foe = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    //MUSIC

    //GAME STATE
    //Kan lägga till sub game state i UI
    public boolean homeScreen = true;
    public boolean gamePlay = false;
        public boolean characterInfo = false;
        public boolean inventoryInfo = false;
    public boolean gamePaused = false;
    public boolean gameDialog = false;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //buffrar skärmen vid sidan om. bättre rendering
        this.addKeyListener(inputHandler);
        this.addMouseListener(mouseInputHandler);
        this.addMouseMotionListener(mouseMotionHandler);
        this.setFocusable(true);
    }
    public void setupGame(){

        assetDealer.setObject();
        assetDealer.setNPC();
        assetDealer.setFoe();
        //playMusic(0);

    }

    public void startGameThread() {
        gameThread = new Thread(this); //instansiera tråden
        gameThread.start(); //startar tråden genom att kalla på run metoden
    }

    @Override
    public void run() { //Sköter spel loopen. 60fps

        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()){

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1){
                //Uppdatera information i rätt FPS
                updateScreen();
                //rita om skärmen med det nya bilderna
                repaint(); // kallar på paintComponent
                delta--;
            }
        }
    }
    public void updateScreen(){

        if(gamePlay){
            //Player
            player.updateScreen();
            //NPC
            for (Entity entity : npc) {
                if (entity != null){
                    entity.update();
                }
            }
            //FOES
            for (int i = 0; i < foe.length; i++) {
                if(foe[i] != null){
                    if(foe[i].alive || foe[i].dead){
                        foe[i].update();
                    }else{
                        foe[i] = null;
                    }
                }
            }
            //ITEMS
            for (Entity entity : objects) {
                if (entity != null){
                    entity.update();
                }
            }
        }
        if (gamePaused){
            //sd
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); // super riktar sig till föräldern
        Graphics2D g2 = (Graphics2D)g; // konverterar graphic objektet till 2D

        //Feltesta
        long drawStart = 0;
        if (inputHandler.testDrawTime){
            drawStart = System.nanoTime();
        }

        //home screen
        if (homeScreen){
            ui.draw(g2);

        }else{
            //Tiles
            worldConstructor.drawTiles(g2);

            //Add entities till lisan
            entityList.add(player);

            //för render order, saler ritas i rätt ordning
            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }

            }

            for (Entity entity:foe) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity entity:objects) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            //Sort entity
            entityList.sort(Comparator.comparingInt(e -> e.worldY));

            //entities
            for (Entity entity:entityList) {
                entity.draw(g2);
            }
            // röjer listan
            entityList.clear();

            //UI
            ui.draw(g2);
        }


        //feltesta
        if (inputHandler.testDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose(); //kastar ut system resurser som används, sparar minne
    }
    public void playMusic(int index){
        music.setFile(index);
        music.play();
        //music.repeat();
    }
    public void stopMusic(){
        music.stop();
    }
    public void soundEffect(int index){
        se.setFile(index);
        se.play();
    }
    public void gameState(int gameState){
        switch (gameState){
            //play mode
            case 1 ->{
                gamePlay = true;
                gamePaused = false;
                gameDialog = false;
                homeScreen = false;
            }
            //pause
            case 2 ->{
                gamePlay = false;
                gamePaused = true;
                characterInfo = false;
            }
            //dialog
            case 3 ->{
                gamePlay = false;
                gamePaused = false;
                gameDialog = true;
                characterInfo = false;
            }
            //home screen
            case 4 ->{
                gamePlay = false;
                gamePaused = false;
                gameDialog = false;
                homeScreen = true;
                characterInfo = false;
            }
        }
    }
}
