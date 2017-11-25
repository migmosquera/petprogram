/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author migmosquera
 */
public class ValidateData {
    
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public Boolean comparePass(String pass, String passRepeat)
    {
        return pass.equals(passRepeat);
    }
    
    public Boolean validEmail(String email)
    {
        
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validPhone(String phone) {
        System.out.println(phone.matches("[0-9]*"));
       return phone.matches("[0-9]*");
    }
    
}
