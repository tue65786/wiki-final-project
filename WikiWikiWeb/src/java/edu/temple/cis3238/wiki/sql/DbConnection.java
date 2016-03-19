/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.sql;

import edu.temple.cis3238.wiki.utils.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class DbConnection implements IDbConnection {
private static final boolean DEBUG = true;
private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
private static final Logger LOG = Logger.getLogger( DbConnection.class.getName() );
private static Properties dbProps = null;
private final String PROPS_FILE = "database.properties";
private Connection conn;
private String connString;
private String connectionMessages;
private String error;

@Override
public void close() {
   if ( conn != null ) {
	  try {
		 if ( !conn.isClosed() ) {
			conn.close();
			if (DEBUG){
			   LOG.info( "Connection Closed -- edu.temple.cis3238.wiki.sql.DbConnection.close()" );  
			}
		 }
	  }
	  catch ( Exception e ) {
		 error = "Error closing connection in DbConn: "
					+ e.getMessage();
		 LOG.warning("error closing connection: " + error);
		 LOG.log(Level.SEVERE, null, e);
		 e.printStackTrace();
	  } // catch
   } // if
   else {
	  LOG.warning("Connection null ");
	  
   }
}
@Override
public Connection getConn() {
   return this.conn;
}

   /**
    * @return the connectionMessages
    */
   public String getConnectionMessages() {
	  return connectionMessages;
   }

   /**
    * @param connectionMessages the connectionMessages to set
    */
   public void setConnectionMessages(String connectionMessages) {
	  this.connectionMessages = connectionMessages;
   }
   public final String getConnectionStringFromProps() {
	  StringBuilder sb = new StringBuilder( "" );  
	 try{
	  dbProps = PropertyUtils.getFromClassPath( PROPS_FILE );
	  sb.append( "jdbc:sqlserver://" );
	  sb.append( PropertyUtils.getValue( dbProps, "dbServer" ) );
	  sb.append( ";database=" );
	  sb.append( PropertyUtils.getValue( dbProps, "dbName" ) );
	  sb.append( ";user=" );
	  sb.append( PropertyUtils.getValue( dbProps, "dbUser" ) );
	  sb.append( ";password=" );
	  sb.append( PropertyUtils.getValue( dbProps, "dbPassword" ) );
	  connString = sb.toString();
	 }
	 catch (Exception e){
		LOG.log( Level.SEVERE, "Prop loader", e );
	 }
	  return connString;
   }


@Override
public String getError() {
   return error == null ? "" : error;
}
public DbConnection(){
   try {
	  this.connectionMessages += "ready..";
	  Class.forName(JDBC_DRIVER).newInstance();
	  conn = DriverManager.getConnection(getConnectionStringFromProps());
	  this.connectionMessages += "\ngot driver...";
	  try {
	  }
	  catch ( Exception e ) { // cant get the connection
		 this.connectionMessages += "problem getting connection:" + e.getMessage() + "<br/>";
		 this.error = "problem getting connection:" + e.getMessage();
		 LOG.log( Level.SEVERE, error, e );
	  }
   }
   catch ( Exception e ) { // cant get the driver...
	  this.connectionMessages += "\nproblem getting driver:" + e.getMessage();
	  this.error = "problem getting driver:" + e.getMessage();
	  LOG.log( Level.SEVERE, error, e );
	  
   }
}
}
