/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import java.io.*;
import java.util.logging.*;

/**
 * Includes<ol><li>Safe toString methods</li><li>Placeholder filler methods</li></ol>
 *
 * @author Christian, Doreen, Dan
 */
public class StringUtils {

private static final Logger LOG = Logger.getLogger( StringUtils.class.getName() );
private static final String PH_LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

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

public static void main(String[] args) {
   for ( int i = 0; i < 1000; i++ ) {
	  System.out.println( "IDX= " + i + " RAND= " + getRandomString( "randTest", 20 + ( i % 20 ) ) );
	  System.out.println( getLoremWords( i % 100) );
   }
}
/**
 * Random semi-unique string
 * @param prefix
 * @param length
 * @return 
 */
public static final String getRandomString(String prefix, int length) {
   Integer r = (int) ( Math.random() * 1000000.0 );
   if ( length < StringUtils.toS( prefix ).length() + 6 ) {
	  length = StringUtils.toS( prefix ).length() + 6;
   }
   Integer hash = r.hashCode();
   Integer random = ( 11 * r ) + ( hash * 11 );
   int end = Math.abs( length - StringUtils.toS( prefix ).length() ) + 1;
   return prefix + random.toString().substring( 0, end < random.toString().length()
												? end
												: random.toString().length() - 1 );

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

/**
 * Safe toString
 *
 * @param source
 * @return
 */
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

/**
 * Paragraph placeholder text
 *
 * @param paragraphs
 * @return
 */
public static String getLoremParagraphs(int paragraphs) {
   StringBuilder l = new StringBuilder();

   for ( int i = 0; i < paragraphs; i++ ) {
	  l.append( PH_LOREM_IPSUM );

	  if ( i < paragraphs - 1 ) {
		 l.append( "<br/>" );
	  }
   }

   return org.apache.commons.lang3.StringUtils.removeEnd( l.toString(), "." ) + "...";
}

/**
 * Short placeholder text
 *
 * @param words Number of words
 * @return
 */
public static String getLoremWords(int words) {
   String[] lArr = PH_LOREM_IPSUM.split( "\\s" );

   int idx = lArr.length-1;
   StringBuilder l = new StringBuilder();

   for ( int i = 0; i < words; i++ ) {
	  if ( idx == lArr.length - 1 ) {
		 idx = 0;
	  }
	  l.append( lArr[idx] );
	  if ( i < words - 1 ) {
		 l.append( ' ' );
	  }
	  idx++;
   }
   return org.apache.commons.lang3.StringUtils.removeEnd( l.toString(), "." ) + ".";
}
}
