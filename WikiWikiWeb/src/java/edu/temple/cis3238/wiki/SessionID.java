/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Manages session ID to track online users.
 * @author 
 * @see WikiEventMonitor
 */
public class SessionID   {

	private static final long serialVersionUID = -3431817001276731179L;

	/**
	 *
	 */
	public String JSESSSIONID ="0";

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 971 * hash + Objects.hashCode(this.JSESSSIONID);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SessionID other = (SessionID) obj;
		if (!Objects.equals(this.JSESSSIONID, other.JSESSSIONID)) {
			return false;
		}
		return true;
	}
	

}
