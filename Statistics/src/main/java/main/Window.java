package main;

import enums.State;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private final JPanel mainPanel = new JPanel();
    private final Program p;

    public Window(Program p){
        this.p = p;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Sko");

        int size = 96;
        int height = size * 8;
        int width = size * 8;
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setFocusable(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JButton back = new JButton("Back");
        back.setBounds(size / 4, height - size / 2, 75,25);
        back.addActionListener(e->{
            p.getSh().changeState(State.INIT_STATE);
        });
        mainPanel.add(back);
        JButton close = new JButton("Close");
        close.addActionListener(e->System.exit(0));
        close.setBounds(width - size, height - size / 2, 75,25);
        mainPanel.add(close);
        this.add(mainPanel);


        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addPanels();
    }
    private void addPanels(){
       p.getSh().getStateList().forEach(this.mainPanel::add);
    }
}
