package utility;

import interfaces.MyInterface;

import javax.swing.*;
import java.util.Arrays;

public class PasswordValidator {

    private String result;
    private String error;

    public void validatePassword(MyInterface d, char[] s){
        if (s.length < 3){
            error = "Password to short";
        } else if (s.length > 200){
            error = "Password to long";
        } else{
            result = d.arrayHandler(s);
        }
    }
    public boolean match(JPasswordField j1, JPasswordField j2){
        return Arrays.equals(j1.getPassword(), j2.getPassword());
    }
    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public void reset(){
        result = null;
        error = null;
    }
}
