package utility;

import interfaces.DoShitWithStrings;

public class EmailValidator {

    private String result;
    private String error;

    public void validateEmail(DoShitWithStrings d, String s){
        String email = s.trim();
        if (email.equals("")){
            error = "Empty Email";
        } else{
            if(d.check(email)){
                result = email;
            } else {
                error = "Invalid email format";
            }
        }
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
