package edu.temple.cis3238.wiki.ui.beans;

import java.io.Serializable;

public class CurrentUser implements Serializable {

    public static String username;
    
    public CurrentUser() {
    }
    
    public static String getUsername() {
        return username;
    }
    
    public void setUsername(String user) {
        username = user;
    }
}
