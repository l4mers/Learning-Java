package program;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Membership");

        Panel panel = new Panel();
        window.add(panel);

        window.pack(); //Gör bilden så liten som möjligt utan att utesluta verktyg

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}