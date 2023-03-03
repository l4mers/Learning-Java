package state;

import components.Customer;
import enums.State;
import utility.EmailValidator;
import utility.PasswordValidator;
import main.Program;

import javax.swing.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class ApplyState extends StateTemplate {

    PasswordValidator pv = new PasswordValidator();
    EmailValidator ev = new EmailValidator();
    ApplyState(Program p) {
        super(p);

        state = State.CREATE_ACCOUNT_STATE;
        lastState = State.INIT_STATE;

        setup();

    }
    private void setup(){
        standardSettings();

        JLabel lFirstName = new JLabel("First Name");
        lFirstName.setBounds(width / 2 - size * 2, size / 2, 500, 25);
        this.add(lFirstName);

        JTextPane jFirstName = new JTextPane();
        jFirstName.setBounds(width / 2 - size * 2, size / 2 + 25, size*4, 25);
        this.add(jFirstName);

        JLabel lLastName = new JLabel("Last Name");
        lLastName.setBounds(width / 2 - size * 2, size + size / 2, 500, 25);
        this.add(lLastName);

        JTextPane jLastName = new JTextPane();
        jLastName.setBounds(width / 2 - size * 2, size + size / 2 + 25, size*4, 25);
        this.add(jLastName);

        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(width / 2 - size * 2, size * 2 + size / 2, size*4, 25);
        this.add(lEmail);

        JTextPane jEmail = new JTextPane();
        jEmail.setBounds(width / 2 - size * 2, size * 2 + size / 2 + 25, size*4, 25);
        this.add(jEmail);

        JLabel lrEmail = new JLabel("Repeat Email");
        lrEmail.setBounds(width / 2 - size * 2, size * 3 + size / 2, 500, 25);
        this.add(lrEmail);

        JTextPane jrEmail = new JTextPane();
        jrEmail.setBounds(width / 2 - size * 2, size * 3 + size / 2 + 25, size*4, 25);
        this.add(jrEmail);

        JLabel lPassword = new JLabel("Password");
        lPassword.setBounds(width / 2 - size * 2, size * 4 + size / 2, 500, 25);
        this.add(lPassword);

        JPasswordField jPassword = new JPasswordField();
        jPassword.setBounds(width / 2 - size * 2, size * 4 + size / 2 + 25, size*4, 25);
        this.add(jPassword);

        JLabel lrjPassword = new JLabel("Repeat Password");
        lrjPassword.setBounds(width / 2 - size * 2, size * 5 + size / 2, 500, 25);
        this.add(lrjPassword);

        JPasswordField jrPassword = new JPasswordField();
        jrPassword.setBounds(width / 2 - size * 2, size * 5 + size / 2 + 25, size*4, 25);
        this.add(jrPassword);

        JLabel message = new JLabel();
        message.setBounds(width / 2 - 200, size * 6 + 10, 400, 25);
        this.add(message);

        JLabel lCounty = new JLabel("County");
        lCounty.setBounds(width / 2 - size * 2, size * 6 + size / 2 - 25, 50, 25);
        this.add(lCounty);

        JComboBox<String> county = new JComboBox<>(p.getDh().getAllCounty().toArray(new String[0]));
        county.setBounds(width / 2 - size * 2, size * 6 + size / 2, 100, 25);
        this.add(county);

        JButton b1 = new JButton("Create");
        b1.setBounds(width / 2 - 40, size * 6 + size / 2, 75, 25);
        b1.addActionListener(e->{

            if (jFirstName.getText().isEmpty() ||
            jLastName.getText().isEmpty() ||
            jEmail.getText().isEmpty() ||
            jPassword.getPassword().length < 1){
                message.setText("All fields are required");
            } else {
                pv.validatePassword((array -> {
                    StringBuilder s = new StringBuilder();
                    for (char c :
                            array) {
                        s.append(c);
                    }
                    return String.valueOf(String.valueOf(s).hashCode());
                }), jPassword.getPassword());

                ev.validateEmail((email -> Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                        .matcher(email).matches()), jEmail.getText());

                if (ev.getError() != null){
                    message.setText(ev.getError());
                } else if (!ev.getResult().equals(jrEmail.getText())){
                    message.setText("Email don't match");
                } else if (pv.getError() != null){
                    message.setText(pv.getError());
                } else if (!pv.match(jPassword, jrPassword)) {
                    message.setText("Password don't match");
                } else {
                    if(p.getDh().emailDoesNotExist(ev.getResult())){
                        p.getDh().createAccount(ev.getResult(),
                                pv.getResult(),
                                jFirstName.getText(), jLastName.getText(),
                                Objects.requireNonNull(county.getSelectedItem()).toString());

                        Customer customer = p.getDh().getCustomerByLogin(ev.getResult(), pv.getResult());
                        if(customer != null){
                            p.getCh().setCustomer(customer);
                            p.getSh().changeState(State.CUSTOMER_STATE);
                            p.getSh().getCurrentState().welcomeMessage();
                        } else {
                            message.setText("Something wrong on our end, try again later");
                        }
                    } else {
                        message.setText("Email does already exist");
                        jPassword.setText("");
                        jrPassword.setText("");
                    }
                }
            }
            resetPasswordFields(jPassword, jrPassword);
            resetTextFields(jEmail, jrEmail);
            resetValidators();
        });
        this.add(b1);

        JButton b2 = new JButton("Cancel");
        b2.setBounds(width / 2 + size + 20, size * 6 + size / 2, 75, 25);
        b2.addActionListener(e -> p.getSh().changeState(State.INIT_STATE));
        this.add(b2);
    }
    private void resetValidators(){
        pv.reset();
        ev.reset();
    }
    private void resetPasswordFields(JPasswordField p1, JPasswordField p2){
        p1.setText("");
        p2.setText("");
    }
    private void resetTextFields(JTextPane p1, JTextPane p2){
        p1.setText("");
        p2.setText("");
    }
}
