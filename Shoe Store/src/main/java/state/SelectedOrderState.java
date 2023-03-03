package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class SelectedOrderState extends StateTemplate {
    SelectedOrderState(Program p) {
        super(p);

        state = State.SELECTED_ORDER_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane jpString = new JScrollPane();
        jpString.setViewportView(itemList);
        jpString.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpString);

        price = new JLabel();
        price.setBounds(width / 2 - 200, height - size * 3, 400, 25);
        this.add(price);

        JButton b1 = new JButton("OK");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e ->{
            p.getSh().changeState(State.CUSTOMER_STATE);
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            p.getSh().changeState(State.ORDER_VIEW_STATE);
        });
        this.add(b2);
    }
}
