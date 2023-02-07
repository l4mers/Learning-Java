package state;

import enums.State;
import main.Program;

import javax.swing.*;

public class ProfileState extends StateTemplate {
    ProfileState(Program p) {
        super(p);

        state = State.PROFILE_STATE;

        setup();
    }
    private void setup(){
        standardSettings();

        JButton b1 = new JButton("Change Email");
        b1.setBounds(width / 2 - 100,height / 2 - size * 2,200,50);
        b1.addActionListener(e -> {
            p.getSh().changeState(State.CHANGE_EMAIL_STATE);
        });
        this.add(b1);

        JButton b2 = new JButton("Change Password");
        b2.setBounds(width / 2 - 100,height / 2 - size,200,50);
        b2.addActionListener(e->{
            p.getSh().changeState(State.CHANGE_PASSWORD_STATE);
        });
        this.add(b2);

        JButton b3 = new JButton("Change Adress");
        b3.setBounds(width / 2 - 100,height / 2 ,200,50);
        b3.addActionListener(e ->{
            p.getSh().changeState(State.CHANGE_ADRESS_STATE);
        });
        this.add(b3);

        JButton b4 = new JButton("Back");
        b4.setBounds(width / 2 - 100,height / 2 + size,200,50);
        b4.addActionListener(e ->{
            p.getSh().changeState(State.CUSTOMER_STATE);
        });
        this.add(b4);
    }
}
