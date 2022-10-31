package program;


import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    //Final values to set width, height and something to relate too
    final public int square = 48;
    final public int width = square * 16;
    final public int height = square * 12;

    final private Event event = new Event(this);

    //Main window
    Label welcomeText, select;
    Button quit, start, back, show;
    JComboBox<String> plantSelection;
    TextArea info;

    public Panel(){
        //Main window
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(238, 204, 204));
        this.setFocusable(true);
        //Makes it possible to set location of tools
        this.setLayout(null);

        setUpContent();

        mainScreen();
    }

    private void setUpContent() {
        //Main Window
        welcomeText = new Label("Lets see how hungry the plants are today!");
        welcomeText.setForeground(Color.darkGray);
        welcomeText.setFont(new Font("", Font.PLAIN, 32));
        welcomeText.setBounds(width / 2 - 300, square * 4, 600, 50);
        welcomeText.setVisible(false);
        this.add(welcomeText);

        quit = new Button("Quit");
        quit.setBounds(square / 2, height - square, 75, 25);
        quit.addActionListener(event);
        this.add(quit);

        start = new Button("Start!");
        start.setBounds(width / 2 - 100, square * 6, 200, 50);
        start.setFont(new Font("",Font.PLAIN, 18));
        start.setVisible(false);
        start.addActionListener(event);
        this.add(start);

        select = new Label("Plant selection:");
        select.setFont(new Font("", Font.BOLD, 14));
        select.setBounds(width / 2 - 225, square * 2, 200, 25);
        select.setVisible(false);
        this.add(select);

        show = new Button("Show");
        show.setBounds(width / 2 - 100,square * 3, 75, 25);
        show.setVisible(false);
        show.addActionListener(event);
        this.add(show);

        back = new Button("Back");
        back.setBounds(width - square / 2 - 75, height - square, 75, 25);
        back.setVisible(false);
        back.addActionListener(event);
        this.add(back);

        //Show plant
        info = new TextArea();
        info.setFont(new Font("", Font.BOLD, 14));
        info.setBounds(square * 4, square * 2, square * 6, square * 7);
        info.setVisible(false);
        this.add(info);
    }
    public void populateDropDown(String[] plants){
        plantSelection = new JComboBox<>(plants);
        plantSelection.setBounds(width / 2 - 100, square * 2, 200, 25);
        plantSelection.addItemListener(event);
        plantSelection.setVisible(false);
        this.add(plantSelection);
    }
    public void mainScreen(){
        welcomeText.setVisible(true);
        select.setVisible(false);
        start.setVisible(true);
        back.setVisible(false);
        show.setVisible(false);
        plantSelection.setVisible(false);
        info.setVisible(false);
    }
    public void plantSelectionScreen(){
        welcomeText.setVisible(false);
        select.setVisible(true);
        start.setVisible(false);
        back.setVisible(true);
        show.setVisible(true);
        plantSelection.setVisible(true);
        info.setVisible(false);
    }
    public void infoScreen(){
        welcomeText.setVisible(false);
        select.setVisible(false);
        start.setVisible(false);
        back.setVisible(true);
        show.setVisible(false);
        plantSelection.setVisible(false);
        info.setVisible(true);
    }
}
