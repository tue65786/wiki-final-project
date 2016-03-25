/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.vo;


public class UsersVOBuilder {

   private int _userID;
   private String _userName;
   private String _password;
   private String _userRole;
   private String _emailAddress;

   public UsersVOBuilder() {
   }

   public UsersVOBuilder setUserID(int _userID) {
	  this._userID = _userID;
	  return this;
   }

   public UsersVOBuilder setUserName(String _userName) {
	  this._userName = _userName;
	  return this;
   }

   public UsersVOBuilder setPassword(String _password) {
	  this._password = _password;
	  return this;
   }

   public UsersVOBuilder setUserRole(String _userRole) {
	  this._userRole = _userRole;
	  return this;
   }

   public UsersVOBuilder setEmailAddress(String _emailAddress) {
	  this._emailAddress = _emailAddress;
	  return this;
   }

   public UsersVO build() {
	  return new UsersVO( _userID, _userName, _password, _userRole, _emailAddress );
	  
   }

}
