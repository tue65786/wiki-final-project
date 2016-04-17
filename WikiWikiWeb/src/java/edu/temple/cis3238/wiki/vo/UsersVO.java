/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import edu.temple.cis3238.wiki.utils.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * User Entity 
 * @author (c)2016 Doreen, Dan, Christian
 */
public class UsersVO implements Serializable {
private static final Logger LOG = Logger.getLogger( UsersVO.class.getName() );

private static final long serialVersionUID = -6129441578079281747L;
private String emailAddress;
private String password;
private int userID;
private String userName;
private String userRole;

	/**
	 *
	 * @param _vo
	 * @return
	 */
	public static UsersVO newInstance(UsersVO _vo) {
   return new UsersVOBuilder().setUserID( _vo.getUserID() ).setUserName( _vo.getUserName() ).setPassword( _vo.getPassword() ).setUserRole( _vo.getUserRole() ).setEmailAddress( _vo.getEmailAddress() ).build();
}

   @Override
   public boolean equals(Object obj) {
	  if ( this == obj ) {
		 return true;
	  }
	  if ( obj == null ) {
		 return false;
	  }
	  if ( getClass() != obj.getClass() ) {
		 return false;
	  }
	  final UsersVO other = (UsersVO) obj;
	  if ( this.getUserID() != other.getUserID() ) {
		 return false;
	  }
	  if ( !Objects.equals( this.password, other.password ) ) {
		 return false;
	  }
	  if ( !Objects.equals( this.userName, other.userName ) ) {
		 return false;
	  }
	  return true;
   }

/**
 * @return the emailAddress
 */
public String getEmailAddress() {
   return StringUtils.toS(emailAddress);
}

/**
 * @param emailAddress the emailAddress to set
 */
public void setEmailAddress(String emailAddress) {
   this.emailAddress = emailAddress;
}

/**
 * @return the password
 */
public String getPassword() {
   return StringUtils.toS(password);
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
   this.password = password;
}

/**
 * @return the userID
 */
public int getUserID() {
   return userID;
}

/**
 * @param userID the userID to set
 */
public void setUserID(int userID) {
   this.userID = userID;
}

/**
 * @return the userName
 */
public String getUserName() {
   return StringUtils.toS(userName);
}

/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
   this.userName = userName;
}

/**
 * @return the userRole
 */
public String getUserRole() {
   return StringUtils.toS(userRole);
}

/**
 * @param userRole the userRole to set
 */
public void setUserRole(String userRole) {
   this.userRole = userRole;
}

   @Override
   public int hashCode() {
	  int hash = 5;
	  hash = 59 * hash + Objects.hashCode(this.getPassword());
	  hash = 59 * hash + this.getUserID();
	  hash = 59 * hash + Objects.hashCode(this.getUserName());
	  return hash;
   }

   @Override
   public String toString() {
	  return "UsersVO{" + "emailAddress=" + getEmailAddress() + ", password=" + getPassword() + ", userID=" + getUserID() + ", userName=" + getUserName() + ", userRole=" + getUserRole() + '}';
   }

	/**
	 *
	 * @param _userID
	 * @param _userName
	 * @param _password
	 * @param _userRole
	 * @param _emailAddress
	 */
	public UsersVO(int _userID, String _userName, String _password, String _userRole, String _emailAddress) {
   this.userID = _userID;
   this.userName = StringUtils.toS(_userName );
   this.password =StringUtils.toS( _password );
   this.userRole = StringUtils.toS( _userRole ) ;
   this.emailAddress = StringUtils.toS(_emailAddress );
}

	/**
	 *
	 * @param _userName
	 * @param _password
	 */
	public UsersVO(String _userName, String _password) {
   this.userName = _userName;
   this.password = _password;
}

}
