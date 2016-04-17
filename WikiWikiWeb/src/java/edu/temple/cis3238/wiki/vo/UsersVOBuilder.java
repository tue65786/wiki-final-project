/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.vo;

/**
 * Purpose: Build UsersVO Entity
 * 
 */
public class UsersVOBuilder {

   private int _userID;
   private String _userName;
   private String _password;
   private String _userRole;
   private String _emailAddress;

	/**
	 *
	 */
	public UsersVOBuilder() {
   }

	/**
	 *
	 * @param _userID
	 * @return
	 */
	public UsersVOBuilder setUserID(int _userID) {
	  this._userID = _userID;
	  return this;
   }

	/**
	 *
	 * @param _userName
	 * @return
	 */
	public UsersVOBuilder setUserName(String _userName) {
	  this._userName = _userName;
	  return this;
   }

	/**
	 *
	 * @param _password
	 * @return
	 */
	public UsersVOBuilder setPassword(String _password) {
	  this._password = _password;
	  return this;
   }

	/**
	 *
	 * @param _userRole
	 * @return
	 */
	public UsersVOBuilder setUserRole(String _userRole) {
	  this._userRole = _userRole;
	  return this;
   }

	/**
	 *
	 * @param _emailAddress
	 * @return
	 */
	public UsersVOBuilder setEmailAddress(String _emailAddress) {
	  this._emailAddress = _emailAddress;
	  return this;
   }
/**
 * 
 * @return  {@linkplain UsersVO} Object
 */
   public UsersVO build() {
	  return new UsersVO( _userID, _userName, _password, _userRole, _emailAddress );
	  
   }

}
