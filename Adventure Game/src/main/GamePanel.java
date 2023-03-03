package main;

import entity.Entity;
import entity.player.Player;

import interactions.AssetDealer;
import world.WorldConstructor;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    private final int originalTileSize = 32;
    private final int scale = 2;
    public final int playerSize = originalTileSize * 3;
    public final int tileSize = originalTileSize * scale;
    private  final int tilesInWidth = 16;
    private final int tilesInHeight = 12;

    public final int screenWidth = tileSize * tilesInWidth; //maxScreenWidthInTiles;

    public final int screenHeight = tileSize * tilesInHeight;

    public int worldWidth = 50; //maxWorldWidthInTiles

    public int worldHeight = 50;

    //FPS
    double FPS = 60;

    //SYSTEM
    Thread gameThread;

    //ENTITIES AND OBJECTS
    public InputHandler inputHandler = new InputHandler(this);
    public Player player = new Player(this, inputHandler);
    WorldConstructor tileConstructor = new WorldConstructor(this, true);
    public WorldConstructor objConstructor = new WorldConstructor(this, false);
    public UI ui = new UI(this);
    public ArrayList<Entity> objects = new ArrayList<>();
    public ArrayList<Entity> npc = new ArrayList<>();
    public ArrayList<Entity> foe = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    ArrayList<Entity> entityList = new ArrayList<>();

    //SOUND

    //GAME STATE
    public int gameState;
    final public int homeScreen = 1;
    final public int adventureMode = 2;
    final public int fightMode = 3;
    final public int pauseScreen = 4;
    final public int gameDialog = 5;
    final public int cutScene = 6;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputHandler);
        this.setFocusable(true);

    }

    public void setupGame(){
        gameState = homeScreen;
        AssetDealer ad = new AssetDealer(this);
        ad.loadAssets("src/resource/map/assets/assets.txt");
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()){

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        switch (gameState){
            case homeScreen ->{

            }
            case adventureMode ->{
                player.update();
                for (Entity entity : npc) {
                    if (entity != null){
                        entity.update();
                    }
                }
                for (int i = 0; i < foe.size(); i++) {
                    if(foe.get(i) != null){
                        if(foe.get(i).alive || foe.get(i).dead){
                            foe.get(i).update();
                        }else{
                            foe.remove(i);
                        }
                    }
                }
                //ITEMS
                for (Entity entity : objects) {
                    if (entity != null){
                        entity.update();
                    }
                }
                for (int i = 0; i < projectileList.size(); i++) {
                    if(projectileList.get(i) != null){
                        if(projectileList.get(i).alive){
                            projectileList.get(i).update();
                        }else{
                            projectileList.remove(i);
                        }
                    }
                }
            }
            case fightMode ->{

            }
            case pauseScreen ->{

            }
            case gameDialog ->{

            }
            case cutScene ->{

            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        switch (gameState) {
            case homeScreen -> {
                ui.draw(g2);
            }
            case adventureMode -> {
                tileConstructor.draw(g2);
                objConstructor.draw(g2);
                entityList.add(player);

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
                for (Entity entity:projectileList) {
                    if (entity != null) {
                        entityList.add(entity);
                    }
                }
                //Sort entity
                entityList.sort(Comparator.comparingInt(e -> e.worldY));

                for (Entity entity:entityList) {
                    entity.draw(g2);
                }

                entityList.clear();

                ui.draw(g2);
            }
            case fightMode -> {

            }
            case pauseScreen -> {

            }
            case gameDialog -> {

            }
            case cutScene -> {

            }
        }
        g2.dispose();
    }
}
