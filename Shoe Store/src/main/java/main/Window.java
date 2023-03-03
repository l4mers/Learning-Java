package main;

import enums.State;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int size = 96;
    private int width = size * 8;
    private int height = size * 8;
    private final JPanel mainPanel = new JPanel();
    private final Program p;
    private final JButton cart;

    public Window(Program p){
        this.p = p;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Sko");

        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setFocusable(true);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JButton close = new JButton("Close");
        close.addActionListener(e->System.exit(0));
        close.setBounds(width - size, height - size / 2, 75,25);
        mainPanel.add(close);
        this.add(mainPanel);

        cart = new JButton("Cart");
        cart.setBounds(width / 2 - 75, height - size / 2 - 20, 150,40);
        cart.addActionListener(e ->{
            p.getSh().changeState(State.CART_STATE);
            p.getSh().getCurrentState().updateItemList(p.getCart().getItemList());
            cart.setVisible(false);
        });
        cart.setVisible(false);
        mainPanel.add(cart);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addPanels();
    }
    private void addPanels(){
        p.getSh().getStateList().forEach(this.mainPanel::add);
    }

    public JButton getCart(){
        return cart;
    }
}
