/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.security;

/**
 *
 * @author CAP
 */
public class Password {
    
    public static boolean isValidPassword(String password){
        //return true if a valid password
        //1. have at least 8 characaters
        //2. consists of only letters and numbers
        //3. must contain at least 2 digits
        //4. must contain at least 1 special character
        if(password.length() < 8 ){
            return false;
            
        } else {
            char c;
            int countNum = 0;
            int countSpec = 0;
            for (int i = 0 ; i < password.length(); i++){
                c = password.charAt(i);
                if(!Character.isLetterOrDigit(c) || !isSpecialChar(c)){
                    return false;
                } else if (Character.isDigit(c)){
                    countNum++;
                } else if (isSpecialChar(c)){
                    countSpec++;
                }
            }
            if (countNum <= 1 || countSpec == 0){
                return false;
            }
            
        }
        return true;
    }
    
    private static boolean isSpecialChar(char c){
        StringBuilder stb = new StringBuilder();
        stb.append(c);
        String specialChars = "/*!@#$%^&*()\"{}[]_|\\?/<>,.";
        if(specialChars.contains(stb.toString())){
            return true;
        }
        return false;
    }
    
}
