package edu.temple.cis3238.wiki.ui.beans;

import java.io.Serializable;

public class CurrentUser implements Serializable {

    public String username;
    
    public CurrentUser() {
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String user) {
        username = user;
    }
}
