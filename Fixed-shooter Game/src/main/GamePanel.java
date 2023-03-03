package main;

import factory.LaserFactory;
import factory.ObstacleFactory;
import inputhandlers.InputHandler;
import sound.Sound;
import ui.GameState;
import ui.HomeState;
import ui.State;
import unit.laser.Laser;
import unit.obstacle.Obstacle;
import unit.Player;
import unit.UnitLoader;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Värden att mäta med(mätinstrument)
    public int size = 64;
    public int width = size * 10;
    public int height = size * 10;
    final private double FPS = 60.0;
    //UNITS
    public Laser[] lasers = new Laser[10];
    public Obstacle[] obstacles = new Obstacle[20];

    //OBJEKT
    public Controller controller = new Controller();
    public LaserFactory laserFactory = new LaserFactory();
    public ObstacleFactory obstacleFactory = new ObstacleFactory();
    public final DJ dj = new DJ(this);
    public final Sound music = new Sound();
    public Sound soundEffect = new Sound();
    private Thread gameThread;
    public final State state = new State();
    private final InputHandler inputHandler = new InputHandler(this);
    public Player player = new Player(inputHandler, size, height - (size + size / 10));
    public UnitLoader unitLoader = new UnitLoader(this);
    public CollisionHandler collisionHandler = new CollisionHandler(this);
    public HighScore highScore = new HighScore();


    public GamePanel() {

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.addKeyListener(inputHandler);
        this.setDoubleBuffered(true); //förbuffrar grafik
        this.setFocusable(true);

        setupGame();
        startThread();
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void setupGame() {
        music.setFile(0);
        state.setCurrentGameState(new HomeState());
        for (int i = 0; i < lasers.length; i++) {
            lasers[i] = new Laser(size, height - (size + size/2));
        }
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(size, -size);
        }
    }
    //LOOP SOM UPPDATERAR 60 ggr / sekund
    @Override
    public void run() {
        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        state.update(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        state.draw(g2,this);
    }
    public void setState(GameState state){
        this.state.setCurrentGameState(state);
    }
    public void setNextState(){
        state.setNextState();
    }
    public void setLastState(){
        state.setLastState();
    }
}
