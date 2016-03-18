/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.sql;

import java.sql.*;

/**
 *
 * @author (c)2016 Dan
 */
public interface IDbConnection {

/**
 *
 * @return Connection to database
 */
public Connection getConn();

/**
 * 
 * @return Connection error message 
 */
public String getError();

/**
 * Close connection wrapper
 */
public void close();
}
