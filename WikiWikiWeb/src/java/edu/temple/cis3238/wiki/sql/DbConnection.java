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
 * Retrieves SQL Connection.
 * <br/><b>NOTE </b>:  Connections <u>must call</u> {@linkplain #close()} to prevent memory leaks. Call to  {@linkplain #close()} should immediately  <u>follow completion of  database work.</u> <i>(eg. ResultSet has no more results)</i>
 * @author (c)2016 Doreen, Dan, Christian
 */
public class DbConnection implements IDbConnection {

private static final boolean DEBUG = true;
private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
private static final Logger LOG = Logger.getLogger( DbConnection.class.getName() );
private static Properties dbProps = null;
private static int openConnections = 0;
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
			if ( DEBUG ) {
			   LOG.info( "Connection Closed -- edu.temple.cis3238.wiki.sql.DbConnection.close()" );
			   openConnections--;
			   if ( openConnections > 0 ) {
				  LOG.warning( "There are unclosed connections. Oepn connections are bad! Call dbc.close()" );
			   }
			}
		 }
	  } catch (Exception e) {
		 error = "Error closing connection in DbConn: "
				 + e.getMessage();
		 LOG.warning( "Error closing connection: " + error );
		 LOG.log( Level.SEVERE, null, e );
		 e.printStackTrace();
	  } // catch
   } // if
   else {
	  LOG.warning( "Connection null " );
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
/**
 * Retrieves server connection properties from database.properties file
 * @return  Connection string.
 * @see database.properties
 */
public final String getConnectionStringFromProps() {
   StringBuilder sb = new StringBuilder( "" );
   try {
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
   } catch (Exception e) {
	  LOG.log( Level.SEVERE, "Prop loader", e );
   }
   return connString;
}

@Override
public String getError() {
   return error == null ? "" : error;
}

public DbConnection() {
   try {
	  this.connectionMessages += "ready..";
	  Class.forName( JDBC_DRIVER ).newInstance();
	  conn = DriverManager.getConnection( getConnectionStringFromProps() );
	  this.connectionMessages += "\ngot driver...";
	  try {
	  } catch (Exception e) { // cant get the connection
		 this.connectionMessages += "problem getting connection:" + e.getMessage() + "<br/>";
		 this.error = "problem getting connection:" + e.getMessage();
		 LOG.log( Level.SEVERE, error, e );
	  }
   } catch (Exception e) { // cant get the driver...
	  this.connectionMessages += "\nproblem getting driver:" + e.getMessage();
	  this.error = "problem getting driver:" + e.getMessage();
	  LOG.log( Level.SEVERE, error, e );

   }
   if ( DEBUG && openConnections > 0 ) {
	  LOG.warning( "You have opened a connection without closing previous connection. Open connections are bad! Call dbc.close()" );
   }
   openConnections++;
}
}
