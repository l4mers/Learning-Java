package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class CustomerState extends StateTemplate {
    CustomerState(Program p) {
        super(p);

        state = State.CUSTOMER_STATE;
        lastState = State.LOGIN_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        welcome = new JLabel("Welcome");
        welcome.setBounds(size, size, width-size, 30);
        this.add(welcome);

        JButton b1 = new JButton("Browse Products");
        b1.setBounds(width / 2 - 100,height / 2 - size * 2,200,50);
        b1.addActionListener(e -> {
            toggleCartButton();
            p.getSh().changeState(State.SELECT_STATE);
        });
        this.add(b1);

        JButton b2 = new JButton("My Orders");
        b2.setBounds(width / 2 - 100,height / 2 - size,200,50);
        b2.addActionListener(e->{
            p.getSh().changeState(State.ORDER_VIEW_STATE);
            p.getSh().getCurrentState().updateOrderList(p.getDh().getCustomerOrders(p.getCh().getCustomer()));
        });
        this.add(b2);

        JButton b3 = new JButton("My Profile");
        b3.setBounds(width / 2 - 100,height / 2 ,200,50);
        b3.addActionListener(e ->{
            p.getSh().changeState(State.PROFILE_STATE);
        });
        this.add(b3);

        JButton b4 = new JButton("Logout");
        b4.setBounds(width / 2 - 100,height / 2 + size,200,50);
        b4.addActionListener(e ->{
            p.getCh().setCustomer(null);
            p.getCart().setOrder(null);
            p.getSh().changeState(State.INIT_STATE);
        });
        this.add(b4);
    }

}
