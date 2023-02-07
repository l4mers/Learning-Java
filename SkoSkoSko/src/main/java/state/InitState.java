package state;

import enums.State;
import interfaces.FrameState;
import main.Program;

import javax.swing.*;

public class InitState extends StateTemplate implements FrameState {

    InitState(Program p) {
        super(p);
        state = State.INIT_STATE;
        lastState = State.LOGIN_STATE;
        this.setVisible(true);

        setup();
    }
    private void setup(){
        standardSettings();

        JButton b1 = new JButton("Login");
        b1.setBounds(width / 2 - 100,height / 2 - size * 2,200,50);
        b1.addActionListener(e -> {
            p.getSh().changeState(State.LOGIN_STATE);
        });
        this.add(b1);

        JButton b2 = new JButton("Become Member");
        b2.setBounds(width / 2 - 100,height / 2 - size,200,50);
        b2.addActionListener(e->{
            p.getSh().changeState(State.CREATE_ACCOUNT_STATE);
        });
        this.add(b2);

        JButton b3 = new JButton("Browse Our Products");
        b3.setBounds(width / 2 - 100,height / 2 ,200,50);
        b3.addActionListener(e ->{
            p.getSh().changeState(State.SELECT_STATE);
        });
        this.add(b3);
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public State lastState() {
        return lastState;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setNext(State state) {
        nextState = state;
    }

    @Override
    public void setLast(State state) {
        lastState = state;
    }
}
