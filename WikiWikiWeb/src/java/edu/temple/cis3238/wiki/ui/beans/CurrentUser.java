package edu.temple.cis3238.wiki.ui.beans;

import edu.temple.cis3238.wiki.vo.UsersVO;
import java.io.Serializable;

/**
 * 
 * JavaBean: In-memory session storage for user information.
 * @see UsersVO
 */
public class CurrentUser implements Serializable {

	private static final long serialVersionUID = -212259253899375852L;

	/**
	 * Username of current user
	 */
	public String username;

	/**
	 *
	 */
	public CurrentUser() {
	}
	
	/**
	 *
	 * @return
	 */
	public boolean isLoggedIn(){
		return username != null && username.length() > 1;
	}

	/**
	 * Resets current user to null.
	 */
	public void logout(){
		username = "";
	}

	/**
	 * Constructs current user with specified name.
	 * @param un
	 */
	public CurrentUser(String un) {
		if (un != null && un.length() > 1) {
			this.username = un;
		}
	}

	/**
	 *
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param user
	 */
	public void setUsername(String user) {
		username = user;
	}
}

