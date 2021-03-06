/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.security;

/**
 * Password Requirements
 * @author CAP
 */
public class Password {
    
	/**
	 *
	 * @param password
	 * @return
	 */
	public static boolean isValidPassword(String password){
        //return true if a valid password
        //1. have at least 8 characaters
        //2. consists of only letters and numbers
        //3. must contain at least 2 digits
        //4. must contain at least 1 special character
        if(password.length() < 8  || password.length() > 25){
            return false;
            
        } else {
            char c;
            int countNum = 0;
            int countSpec = 0;
            for (int i = 0 ; i < password.length(); i++){
                c = password.charAt(i);
                if(!Character.isLetterOrDigit(c) && !isSpecialChar(c)){
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
    
	/**
	 *
	 * @param c
	 * @return
	 */
	public static boolean isSpecialChar(char c){
        String specialChars = "/*!@#$%^&*()\"{}[]_|\\?/<>,.";
        return specialChars.indexOf(c) >= 0;
    }
    
	/**
	 *
	 * @param username
	 * @return
	 */
	public static boolean isValidUsername(String username){
        if(username.length() < 4 || username.length() > 25 ){
            return false;
        } 
        for (int i = 0 ; i < username.length(); i++){
            char c = username.charAt(i);
            if(!Character.isLetterOrDigit(c)){
                return false;
            }
            
        }
        
        return true;
        
    }
}
