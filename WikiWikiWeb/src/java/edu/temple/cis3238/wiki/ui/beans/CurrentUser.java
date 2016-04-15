package edu.temple.cis3238.wiki.ui.beans;

import java.io.Serializable;

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = -212259253899375852L;

	public String username;

	public CurrentUser() {
	}
	
	public boolean isLoggedIn(){
		return username != null && username.length() > 1;
	}
	public void logout(){
		username = "";
	}
	public CurrentUser(String un) {
		if (un != null && un.length() > 1) {
			this.username = un;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		username = user;
	}
}

