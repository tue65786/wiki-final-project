/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import java.io.*;
import java.util.logging.*;

/**
 * Safe toString methods
 *
 * @author Christian, Doreen, Dan
 */
public class StringUtils {

public static String coalesce(Object... items) {
   if ( items == null ) {
	  return "";
   }
   for ( Object item : items ) {
	  if ( item != null && ( (String) item ).length() > 0 ) {
		 return item.toString();
	  }
   }
   return "";
}

public static String toS(final Object source) {
   return source == null ? "" : source.toString().trim();
}

public static String toS(final Object source, String defaultVal) {
   try {
	  return ( source == null || source.toString().isEmpty() )
			  ? toS( defaultVal ) : toS( source.toString() ).trim();
   } catch (Exception e) {
	  return defaultVal;
   }
}

public static String toS(final int source) {
   return source == 0 ? "" : String.valueOf( source );
}

public static final String stackStraceAsString(final Throwable exception) {
   if ( exception != null ) {

	  final StringWriter stringWriter = new StringWriter();
	  exception.printStackTrace( new PrintWriter( stringWriter ) );
	  return stringWriter.toString();
   } else {
	  LOG.warning( "Exception null printing stack track" );
	  return "";
   }
}

public static final String stackStraceAsStringDetails(final Throwable e) {
   StringBuilder sb = new StringBuilder( "" );
   if ( e != null ) {
	  final StringWriter stringWriter = new StringWriter();
	  try {
		 e.printStackTrace( new PrintWriter( stringWriter ) );
		 sb.append( stringWriter.toString() );
		 sb.append( "\nCause:" );
		 sb.append( e );
		 Throwable t = e.getCause();
		 while ( t != null ) {
			sb.append( t );
			sb.append( "<br/>" );
			t = t.getCause();
		 }
	  } catch (Exception exc) {
		 sb.append( e.getMessage() );
	  } finally {
		 return StringUtils.toS( sb.toString(), "Sorry.. full stack trace not available" );
	  }

   } else {
	  LOG.warning( "Exception null printing stack track" );
	  return "Null exception";
   }
}
private static final Logger LOG = Logger.getLogger( StringUtils.class.getName() );
}
