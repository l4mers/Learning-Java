package state;

import components.Customer;
import enums.State;
import interfaces.FrameState;
import utility.PasswordValidator;
import main.Program;

import javax.swing.*;

public class LoginState extends StateTemplate implements FrameState {

    PasswordValidator pv = new PasswordValidator();

    LoginState(Program p) {
        super(p);
        state = State.LOGIN_STATE;
        lastState = State.INIT_STATE;

        setup();
    }

    private void setup() {
        standardSettings();

        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(width / 2 - size * 2, height / 2 - size * 2, 100, 25);
        this.add(lEmail);

        JTextPane tEmail = new JTextPane();
        tEmail.setBounds(width / 2 - size * 2, ((height / 2) - size * 2) + 25, size*4, 25);
        this.add(tEmail);

        JLabel lPassword = new JLabel("Password");
        lPassword.setBounds(width / 2 - size * 2, ((height / 2) - size * 2) + size, 100, 25);
        this.add(lPassword);

        JPasswordField pPassword = new JPasswordField();
        pPassword.setBounds(width / 2 - size * 2, ((height / 2) - size * 2) + size + 25, size*4, 25);
        this.add(pPassword);

        JLabel lMessage = new JLabel();
        lMessage.setBounds(width / 2 - size * 2, ((height / 2) - size * 2) + size * 3, 400, 25);
        this.add(lMessage);

        JButton b1 = new JButton("Login");
        b1.setBounds(width / 2 - size * 2, ((height / 2) - size * 2) + size * 2, 75, 25);
        b1.addActionListener(e->{

            pv.validatePassword((array -> {
                StringBuilder s = new StringBuilder();
                for (char c :
                        array) {
                    s.append(c);
                }
                return String.valueOf(String.valueOf(s).hashCode());
            }), pPassword.getPassword());

            if(tEmail.getText().length() < 1){
                lMessage.setText("Email required");
                pPassword.setText(null);
            } else if (pv.getError() != null){
                lMessage.setText(pv.getError());
            } else{
                Customer customer = p.getDh().getCustomerByLogin(tEmail.getText(), pv.getResult());
                if (customer == null){
                   lMessage.setText("Invalid information");
                } else {
                    p.getCh().setCustomer(customer);
                    p.getSh().changeState(State.CUSTOMER_STATE);
                    p.getSh().getCurrentState().welcomeMessage();
                }
            }
            pv.reset();
            pPassword.setText(null);
        });
        this.add(b1);

        JButton b2 = new JButton("Cancel");
        b2.setBounds(width / 2 + size + 20, ((height / 2) - size * 2) + size *2, 75, 25);
        b2.addActionListener(e -> p.getSh().changeState(p.getSh().getCurrentState().lastState));
        this.add(b2);

        JButton b3 = new JButton("Sign Up");
        b3.setBounds(width / 2 - 50, height / 2 + size, 100, 50);
        b3.addActionListener(e -> p.getSh().changeState(State.CREATE_ACCOUNT_STATE));
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

