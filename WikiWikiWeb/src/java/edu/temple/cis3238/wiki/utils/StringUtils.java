/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import static edu.temple.cis3238.constants.STRINGS.REGEX_HTML_MARKUP_CHARS;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;

/**
 * Includes<ol><li>Safe toString methods</li><li>Placeholder filler methods</li></ol>
 *
 * @author Christian, Doreen, Dan
 */
public class StringUtils {

   public static final String DEFAULT_SQL_DATETIME_FMT = "yyyy-MM-dd HH:mm:ss.SSS";
   public static final String DEFAULT_DISPLAY_DATETIME_FMT = "yyyy-MM-dd HH:mm:ss";
   private static final Logger LOG = Logger.getLogger(StringUtils.class.getName());
   private static final String PH_LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

   public static String formatDate(String dateStr) {
	  return formatDate(dateStr, DEFAULT_DISPLAY_DATETIME_FMT);
   }

   public static String formatDate(String dateStr, String formattedDateOut) {
	  DateFormat dateformat = new SimpleDateFormat(DEFAULT_SQL_DATETIME_FMT, Locale.ENGLISH);
	  DateFormat dateformatOut = new SimpleDateFormat(formattedDateOut, Locale.ENGLISH);
	  try {
		 return dateformatOut.format(dateformat.parse(dateStr));
	  } catch (Exception ex) {
		 Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);

	  }
	  return dateStr;
   }

   public static String coalesce(Object... items) {
	  if (items == null) {
		 return "";
	  }
	  for (Object item : items) {
		 if (item != null && ((String) item).length() > 0) {
			return item.toString();
		 }
	  }
	  return "";
   }

   /**
	* Converts words to CamelCase single word.
	* 
	* @param in  (ex.) Hello World 
	* @return (ex.) HelloWorld
	*/
   public static String toCamelCase(String in){
	  return org.apache.commons.lang3.text.WordUtils.capitalizeFully(toS(in)).replace(" ","");
   }
   
   /**
	* Converts words from CamelCase to spaced words.
	* 
	* @param in  (ex.) HelloWorld 
	* @return (ex.) Hello World
	*/
   public static String fromCamelCase(String in){
	  return org.apache.commons.lang3.StringUtils.join(org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(
			  toS(in))," ");
   }
   /**
    * Random semi-unique string
    *
    * @param prefix
    * @param length
    * @return
    */
   public static final String getRandomString(String prefix, int length) {
	  Integer r = (int) (Math.random() * 1000000.0);
	  if (length < StringUtils.toS(prefix).length() + 6) {
		 length = StringUtils.toS(prefix).length() + 6;
	  }
	  Integer hash = r.hashCode();
	  Integer random = (11 * r) + (hash * 11);
	  int end = Math.abs(length - StringUtils.toS(prefix).length()) + 1;
	  return prefix + random.toString().substring(0, end < random.toString().length()
			  ? end
			  : random.toString().length() - 1);

   }
/**
 * Stores stack track as String
 * @param exception Thrown 
 * @return String containing stack trace.
 */
   public static final String throwableStackTraceToString(final Throwable exception) {
	  if (exception != null) {

		 final StringWriter stringWriter = new StringWriter();
		 exception.printStackTrace(new PrintWriter(stringWriter));
		 return stringWriter.toString();
	  } else {
		 LOG.warning("Exception null printing stack track");
		 return "";
	  }
   }

   public static final String stackStraceAsStringDetails(final Throwable e) {
	  StringBuilder sb = new StringBuilder("");
	  if (e != null) {
		 final StringWriter stringWriter = new StringWriter();
		 try {
			e.printStackTrace(new PrintWriter(stringWriter));
			sb.append(stringWriter.toString());
			sb.append("\nCause:");
			sb.append(e);
			Throwable t = e.getCause();
			while (t != null) {
			   sb.append(t);
			   sb.append("\n");
			   t = t.getCause();
			}
		 } catch (Exception exc) {
			sb.append(e.getMessage());
		 } finally {
			return StringUtils.toS(sb.toString(), "Sorry.. full stack trace not available");
		 }

	  } else {
		 LOG.warning("Exception null printing stack track");
		 return "Null exception";
	  }
   }

   /**
    * Safe toString method that converts nulls to empty strings.
    *
    * @param source
    * @return source or ""
    */
   public static String toS(final Object source) {
	  return source == null ? "" : source.toString().trim();
   }
/**
 * Safe toString method with default value.
 * @param source input
 * @param defaultVal value to return if source is null
 * @return input or default value if input is null
 */
   public static String toS(final Object source, String defaultVal) {
	  try {
		 return (source == null || source.toString().isEmpty())
				 ? toS(defaultVal) : toS(source.toString()).trim();
	  } catch (Exception e) {
		 return defaultVal;
	  }
   }
/**
 * Converts int to String (if value is 0, returns empty string)
 * @param source
 * @return 
 */
   public static String toS(final int source) {
	  return source == 0 ? "" : String.valueOf(source);
   }

   /**
    * Paragraph placeholder text
    *
    * @param paragraphs
    * @return
    */
   public static String getLoremParagraphs(int paragraphs) {
	  StringBuilder l = new StringBuilder();

	  for (int i = 0; i < paragraphs; i++) {
		 l.append(PH_LOREM_IPSUM);

		 if (i < paragraphs - 1) {
			l.append("<br/>");
		 }
	  }

	  return org.apache.commons.lang3.StringUtils.removeEnd(l.toString(), ".") + "...";
   }

   /**
    * Short placeholder text
    *
    * @param words Number of words
    * @return
    */
   public static String getLoremWords(int words) {
	  String[] lArr = PH_LOREM_IPSUM.split("\\s");

	  int idx = lArr.length - 1;
	  StringBuilder l = new StringBuilder();

	  for (int i = 0; i < words; i++) {
		 if (idx == lArr.length - 1) {
			idx = 0;
		 }
		 l.append(lArr[idx]);
		 if (i < words - 1) {
			l.append(' ');
		 }
		 idx++;
	  }
	  return org.apache.commons.lang3.StringUtils.removeEnd(l.toString(), ".") + ".";
   }
/**
 * Returns first part of string upto specified length (breaks at word, greedy)
 * @param input
 * @param length character limit
 * @return 
 */
   public static String truncateAtWord(String input, int length) {
	  int offset;
	  offset = 2;
	  if (input == null || input.length() < (length - offset)) {
		 return input;
	  }
	  int iNextSpace;
	  iNextSpace = input.lastIndexOf(" ", length);
	  String trunc = input;
	  try {
		 trunc = String.format(input
				 .substring(0, (iNextSpace > 0) ? iNextSpace : (length - offset))
				 .trim());
		 return trunc + " ...";
	  } catch (Exception e) {
		 return trunc;
	  }
   }

   /**
    * Removes:
    * <ol>
    * <li>[[[ and ]]]</li>
    * <li>&quot; and apostrophe</li>
    * <li>&amp; ampersand and gt / lt signs &gt; + &lt;</li>
    * <li>semicolon</li>
    * </ol>
    *
    * @param in
    * @return
    */
   public static String stripInvalidChars(String in) {
	  Pattern pattern = Pattern.compile("(\\[\\[\\[|\\]\\]\\]|\"|'|&|<|>|;)");
	  Matcher matcher = pattern.matcher(StringUtils.toS(in));
	  try {
		 in = matcher.replaceAll("");
	  } catch (Exception e) {
	  }
	  return StringUtils.toS(in);
   }
/**
 * Removes all html tags from string
 * @param val
 * @return 
 */
   public static String removeHtmlMarkups(String val) {
	  String clean = "";
	  try {
		 Pattern pattern = Pattern.compile(REGEX_HTML_MARKUP_CHARS,
				 Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		 Matcher matcher = pattern.matcher(val);
		 try {
			clean = matcher.replaceAll("");
		 } catch (IllegalArgumentException ex) {
		 } catch (IndexOutOfBoundsException ex) {
		 }
	  } catch (PatternSyntaxException ex) { 
	  }//
	  return toS(clean);
   }
/**
 * HTML Encodes markup characters.
 * @param src
 * @return 
 */
   public static String replaceEntities(String src) {
	  src = src.replace("&", "&amp;");
	  src = src.replace("<", "&lt;");
	  src = src.replace(">", "&gt;");
	  src = src.replace("\"", "&quot;");

	  return src;
   }
}
