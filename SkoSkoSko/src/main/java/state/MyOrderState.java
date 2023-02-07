package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class MyOrderState extends StateTemplate {
    MyOrderState(Program p) {
        super(p);

        state = State.ORDER_VIEW_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        JScrollPane jpOrder = new JScrollPane();
        jpOrder.setViewportView(orderList);
        jpOrder.setBounds(width / 2 - 200, size, 400, height - size * 4);
        this.add(jpOrder);

        JButton b1 = new JButton("Select");
        b1.setBounds(width / 2 - 200, height - size * 3 + 30, 100, 30);
        b1.addActionListener(e->{
            if(orderList.getSelectedIndex() != -1){
                p.getSh().changeState(State.SELECTED_ORDER_STATE);
                p.getSh().getCurrentState().updateItemList(p.getDh().getItemsByOrder(orderListModel.getElementAt(orderList.getSelectedIndex())));
            }
        });
        this.add(b1);

        JButton b2 = new JButton("Back");
        b2.setBounds(width / 2 + 100, height - size * 3 + 30, 100, 30);
        b2.addActionListener(e ->{
            p.getSh().changeState(State.CUSTOMER_STATE);
        });
        this.add(b2);


    }
}
