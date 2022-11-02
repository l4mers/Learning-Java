package main;

import scoreboard.Scoreboard;
import sound.Sound;
import tiles.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    //PANEL VALUES <<<Värden att förhålla sig till>>> (referensvärden)
    final int tileSize = 96;
    final int borderSize = tileSize / 2;
    final int screenWidth = tileSize * 5;
    final int screenHeight = tileSize * 5;

    //klasser
    Sound sound = new Sound();
    ButtonHandler bh = new ButtonHandler(this);
    Scoreboard scoreboard = new Scoreboard();
    GameMaster gm;
    TileManager tm;

    //BUTTONS <<<Provar knappar men vi kanske byter sen vill bara se hur det ser ut>>>
    JButton bt1 = new JButton(),bt2 = new JButton(),bt3 = new JButton(),bt4 = new JButton(),bt5 = new JButton(),
            bt6 = new JButton(),bt7 = new JButton(),bt8 = new JButton(),bt9 = new JButton(),bt10 = new JButton(),
            bt11 = new JButton(),bt12 = new JButton(),bt13 = new JButton(),bt14 = new JButton(),bt15 = new JButton(),
            bt16 = new JButton(),
            btStart,btRestart,btQuit, btBack, btScoreBoard;
    public JButton btSort;
    List<JButton> btList;
    JLabel titleText, victoryText, titleTextShadow, victoryTextShadow, ball, scoreboardTitle;
    public JTextArea currentLeader;
    public JTextArea scoreBoardTextArea;

    //Final picture
    BufferedImage ballImage;

    //GAME STATE
    JPanel startScreen;
    final int startScreenState = 0;
    JPanel playScreen;
    final int playScreenState = 1;
    JPanel victoryScreen;
    final int victoryScreenState = 2;
    JPanel scoreboardScreen;
    final int scoreboardState = 3;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true); //buffrar skärmen vid sidan om. bättre rendering (Kanske inte nödvändigt)
        this.setFocusable(true);
        this.setLayout(null);
        this.addKeyListener(bh);

        setUpPanelComponents();
        gameState(startScreenState);
        gm = new GameMaster(this);
        tm = new TileManager(this);
        sound.playMusic();


    }

    //Sätter upp alla komponenter på fixed plats och storlek
    private void setUpPanelComponents() {

        setUpFont();

        setUpStartScreen();

        setUpPlayScreen();

        setUpVictoryScreen();

        setUpScoreboardScreen();

        currentLeader.setText(scoreboard.getCurrentLeader());

        btScoreBoard = new JButton("<html><font size='+1' face='x12y16pxMaruMonica'>Scoreboard </font></html>");
        btScoreBoard.setBounds(screenWidth / 2 - 60 , screenHeight - 35, 120, 25);
        this.add(btScoreBoard);
        btScoreBoard.addActionListener(bh);

        btSort = new JButton("<html><font size='+1' face='x12y16pxMaruMonica'>Sort by clicks</font></html>");
        btSort.setBounds(screenWidth / 2 - 70 , screenHeight - 35, 140, 25);
        this.add(btSort);
        btSort.addActionListener(bh);

        btBack = new JButton("<html><font size='+1' face='x12y16pxMaruMonica'>Back</font></html>");
        btBack.setBounds(borderSize, screenHeight - 35, 80, 25);
        this.add(btBack);
        btBack.addActionListener(bh);

        btRestart = new JButton("<html><font size='+1' face='x12y16pxMaruMonica'>Home</font></html>");
        btRestart.setBounds(borderSize, screenHeight - 35, 80, 25);
        this.add(btRestart);
        btRestart.addActionListener(bh);

        btQuit = new JButton("<html><font size='+1' face='x12y16pxMaruMonica'>Quit</font></html>");
        btQuit.setBounds(screenWidth - (borderSize + 80) , screenHeight - 35, 80, 25);
        this.add(btQuit);
        btQuit.addActionListener(bh);

        scoreboardTitle = new JLabel("Scoreboard");
        scoreboardTitle.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 24));
        scoreboardTitle.setForeground(Color.WHITE);
        scoreboardTitle.setBounds(centerForX(scoreboardTitle.getText()) - 15, borderSize / 2, screenWidth - tileSize, 20);
        this.add(scoreboardTitle);

        victoryText = new JLabel("CONGRATULATIONS!!! YOU HAVE WON!!!");
        victoryText.setForeground(new Color(0xC20D0D));
        victoryText.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 28));
        victoryText.setBounds(borderSize + 4, 2, 500, 50);
        this.add(victoryText);

        victoryTextShadow = new JLabel("CONGRATULATIONS!!! YOU HAVE WON!!!");
        victoryTextShadow.setForeground(Color.BLACK);
        victoryTextShadow.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 28));
        victoryTextShadow.setBounds(borderSize + 6, 1, 500, 50);
        this.add(victoryTextShadow);

    }

    private void setUpStartScreen(){
        startScreen = new JPanel();
        startScreen.setLayout(null);
        startScreen.setBounds(0, 0, screenWidth, tileSize * 4);
        startScreen.setBackground(Color.DARK_GRAY);
        this.add(startScreen);

        btStart = new JButton("<html><font size='+2' face='x12y16pxMaruMonica'>Play</font></html>");
        btStart.setBounds(screenWidth / 2 - 100, screenHeight / 2 - borderSize, 200, 50);
        startScreen.add(btStart);
        btStart.addActionListener(bh);

        titleText = new JLabel("WELCOME TO 15-PUZZLE GAME");
        titleText.setForeground(new Color(0xC20D0D));
        titleText.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 40));
        titleText.setBounds(screenWidth / 2 - 225, borderSize, 500, 100);
        startScreen.add(titleText);

        titleTextShadow = new JLabel("WELCOME TO 15-PUZZLE GAME");
        titleTextShadow.setForeground(Color.BLACK);
        titleTextShadow.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 40));
        titleTextShadow.setBounds(screenWidth / 2 - 223, borderSize - 2, 500, 100);
        startScreen.add(titleTextShadow);

        currentLeader = new JTextArea();
        currentLeader.setBounds(borderSize, screenHeight / 2 + borderSize, tileSize * 4, tileSize * 3);
        currentLeader.setBackground(Color.DARK_GRAY);
        currentLeader.setForeground(new Color(0xC20D0D));
        currentLeader.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 18));
        currentLeader.setEditable(false);
        startScreen.add(currentLeader);
    }
    private void setUpPlayScreen(){
        playScreen = new JPanel();
        playScreen.setLayout(new GridLayout(4,4));
        playScreen.setBounds(borderSize, borderSize, tileSize * 4, tileSize * 4);
        this.add(playScreen);

        btList = new ArrayList<>(List.of(bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13,bt14,bt15,bt16));
        int name = 1;
        for (JButton bt:btList) {
            bt.setName(String.valueOf(name));
            bt.addActionListener(bh);
            playScreen.add(bt);
            name++;
        }
    }
    private void setUpVictoryScreen(){
        victoryScreen = new JPanel();
        victoryScreen.setLayout(null);
        victoryScreen.setBounds(borderSize, borderSize, tileSize * 4, tileSize * 4);
        this.add(victoryScreen);

        ballImage = loadImage();

        ball = new JLabel(new ImageIcon(ballImage), SwingConstants.CENTER);
        ball.setBounds(0 , 0, tileSize * 4, tileSize * 4);
        victoryScreen.add(ball);

    }
    private void setUpScoreboardScreen(){
        scoreboardScreen = new JPanel();
        scoreboardScreen.setLayout(null);
        scoreboardScreen.setBounds(0, borderSize, screenWidth, tileSize * 4);
        this.add(scoreboardScreen);

        ScrollPane sp = new ScrollPane();
        sp.setBounds(0, 0, screenWidth, screenHeight - tileSize);
        scoreBoardTextArea = new JTextArea();
        scoreBoardTextArea.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 18));
        scoreBoardTextArea.setEditable(false);
        scoreBoardTextArea.setLineWrap(true);
        scoreBoardTextArea.setBackground(Color.GRAY);
        sp.add(scoreBoardTextArea);
        scoreboardScreen.add(sp);
    }
    public void gameState(int state){
        switch (state){
            case 0 ->{
                startScreen.setVisible(true);
                playScreen.setVisible(false);
                victoryScreen.setVisible(false);
                scoreboardScreen.setVisible(false);
                btRestart.setVisible(false);
                victoryText.setVisible(false);
                victoryTextShadow.setVisible(false);
                btBack.setVisible(false);
                btScoreBoard.setVisible(true);
                scoreboardTitle.setVisible(false);
                btSort.setVisible(false);
            }
            case 1 ->{
                startScreen.setVisible(false);
                playScreen.setVisible(true);
                victoryScreen.setVisible(false);
                scoreboardScreen.setVisible(false);
                btRestart.setVisible(true);
                victoryText.setVisible(false);
                victoryTextShadow.setVisible(false);
                btBack.setVisible(false);
                btScoreBoard.setVisible(false);
                scoreboardTitle.setVisible(false);
                btSort.setVisible(false);
            }
            case 2 ->{
                startScreen.setVisible(false);
                playScreen.setVisible(false);
                victoryScreen.setVisible(true);
                scoreboardScreen.setVisible(false);
                btRestart.setVisible(true);
                victoryText.setVisible(true);
                victoryTextShadow.setVisible(true);
                btBack.setVisible(false);
                btScoreBoard.setVisible(false);
                scoreboardTitle.setVisible(false);
                btSort.setVisible(false);
            }
            case 3 ->{
                startScreen.setVisible(false);
                playScreen.setVisible(false);
                victoryScreen.setVisible(false);
                scoreboardScreen.setVisible(true);
                btRestart.setVisible(false);
                victoryText.setVisible(false);
                victoryTextShadow.setVisible(false);
                btBack.setVisible(true);
                btScoreBoard.setVisible(false);
                scoreboardTitle.setVisible(true);
                btSort.setVisible(true);
            }
        }
    }
    //Laddar buffered image(Kommer kunna göras generellt för alla bilder sen)
    public BufferedImage loadImage() {
        String imagePath = "src/resources/images/ball/badboll.jpg";
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(imagePath));
        }catch (IOException e){
            e.printStackTrace();
        }
        return bi;
    }
    private void setUpFont() {
        String path = "/font/x12y16pxMaruMonica.ttf";
        try{
            InputStream stream = getClass().getResourceAsStream(path);
            assert stream != null;
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
    }
    private int centerForX(String text){

        return screenWidth / 2 - text.length() * 4;
    }
}
