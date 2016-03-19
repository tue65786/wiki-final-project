/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.vo;

import edu.temple.cis3238.wiki.utils.*;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class UsersVO implements Serializable {

private static final long serialVersionUID = -6129441578079281747L;
private String emailAddress;
private String password;
private int userID;
private String userName;
private String userRole;

public static UsersVO newInstance(UsersVO _vo) {
   return new UsersVO( _vo.getUserID(), _vo.getUserName(), _vo.getPassword(), _vo.getUserRole(), _vo.getEmailAddress() );
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
   return password;
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
   return userName;
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

public UsersVO(int _userID, String _userName, String _password, String _userRole, String _emailAddress) {
   this.userID = _userID;
   this.userName = _userName;
   this.password = _password;
   this.userRole = _userRole;
   this.emailAddress = _emailAddress;
}

public UsersVO(String _userName, String _password) {
   this.userName = _userName;
   this.password = _password;
}
   private static final Logger LOG = Logger.getLogger( UsersVO.class.getName() );

}
