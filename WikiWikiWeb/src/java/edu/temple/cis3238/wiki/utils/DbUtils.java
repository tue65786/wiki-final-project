/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public class DbUtils {

/**
 * Process Data from a {@linkplain ResultSet} into a basic HTML table
 *
 * @param rs
 * @return
 */

private static final String EMBED_STYLES = "<style>"
										   + "th{background-color:black;color:white;}"
										   + "tr.odd-row td{}"
										   + "tr.even-row td{background-color:#FFFFFD;}"
										   + "td{color:black;border:1px solid grey;}"
										   + "tr{padding: 1px 0;}"
										   + "</style>";

/**
 * Process Data from a {@linkplain ResultSet} into a basic HTML table
 *
 * @param rs
 * @return
 * @return the EMBED_STYLES
 */
public static String getEMBED_STYLES() {
   return EMBED_STYLES;
}

public static String makeTableFromRS(ResultSet rs) {
   StringBuilder sb = new StringBuilder( "" );
   int columns;
   boolean odd = true;
   try {
	  ResultSetMetaData md = rs.getMetaData();
	  int columnCount = md.getColumnCount();
	  StringBuilder colb = null;
	  sb.append( "<tr class=\"head-row\">" );
	  colb = new StringBuilder( "" );
	  for ( int i = 1; i <= columnCount; i++ ) {
		 colb.append( makeCell( md.getColumnName( i ), true ) );
	  }
	  sb.append( colb );
	  sb.append( "</tr>" );
	  while ( rs.next() ) {
		 sb.append( "<tr class=\"" ).append( odd ? "odd-row" : "even-row" ).append( "\">" );
		 colb = new StringBuilder( "" );
		 for ( int i = 1; i <= columnCount; i++ ) {
			colb.append( makeCell( rs.getObject( i ), false ) );
		 }
		 sb.append( colb );
		 sb.append( "</tr>" );
		 odd = !odd;
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return "<table>" + sb.toString() + "</table>";
}

/**
 * Makes a table cell
 *
 * @param value
 * @param isHeader
 * @return
 */
private static String makeCell(Object value, boolean isHeader) {
   String s = null;
   if ( value == null ) {
	  s = "&nbsp;";
   } else {
	  try {
		 s = String.valueOf( value );
	  } catch (Exception ex) {
		 ex.printStackTrace();
		 LOG.log( Level.SEVERE, null, ex );
	  }
   }
   final String tda = isHeader ? "th class=\"table-cell\" >" : "td class=\"table-cell\">", tdb = isHeader ? "th>" : "td>";
   return "<" + tda + StringUtils.toS( s ) + "</" + tdb;
}

 public static String printBatchUpdateException(BatchUpdateException b) {
        
        String err = "";
        err += "\n----BatchUpdateException----";
        err += "\nSQLState:  " + b.getSQLState();
        err += "\nMessage:  " + b.getMessage();
        err += "\nVendor:  " + b.getErrorCode();
        err += "\nUpdate counts:  ";
        int[] updateCounts = b.getUpdateCounts();
        for (int i = 0; i < updateCounts.length; i++) {
            err += updateCounts[i] + ", ";
        }
        return err;
    }

    /**
     * @param ex
     * <p>
     * @return
     */
    public static String printSQLException(SQLException ex) {
        String stackTracedAsString;
        String err = "";
        for (Throwable e : ex) {
            String stackStr = StringUtils.stackStraceAsString(ex);
            if (e instanceof SQLException) {
                String state = ((SQLException) e).getSQLState();
                stackTracedAsString = state;
                    e.printStackTrace(System.err);
                    err += "\nSQLState: " + ((SQLException) e).getSQLState();
                    err += "\nError Code: " + ((SQLException) e).getErrorCode();
                    err += "\nMessage: " + e.getMessage();
                    Throwable t = ex.getCause();
                    while (t != null) {
                        err += "\nCause: " + t;
                        t = t.getCause();
                    }
                    if (state != null) {
                        if (((SQLException) e).getSQLState()
                                .equalsIgnoreCase("S1000")) {
                            err += "\nDetails: Cannot insert a record with that ID already exists."; 
                        } else if (((SQLException) e).getMessage()
                                .toLowerCase()
                                .contains("foreign key")) {
                            err += "\nDetails: Invalid foreign key reference.";
                        } else if (((SQLException) e).getMessage()
                                .toLowerCase()
                                .contains("duplicate entry")) {
                            err += "\nDetails: Duplicate entry (PK)."; 
                        } else if (((SQLException) e).getMessage()
                                .toLowerCase()
                                .contains("incorrect syntax")) {
                            err += "\nDetails: Syntax Error."; 
                        }
                    }
                
            }
        }
        return err;
    }
private static final Logger LOG = Logger.getLogger( DbUtils.class.getName() );
}
