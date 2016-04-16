/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class StringUtilsTest {

   public StringUtilsTest() {
   }

   @BeforeClass
   public static void setUpClass() {
   }

   @AfterClass
   public static void tearDownClass() {
   }

   @Before
   public void setUp() {
   }

   @After
   public void tearDown() {
   }

   /**
    * Test of toCamelCase method, of class StringUtils.
    */
   @Test
   public void testToCamelCase() {
	  System.out.println("TO CamelCase");
	  String in = "Hello World how ARE you";
	  String expResult = "HelloWorldHowAreYou";
	  String result = StringUtils.toCamelCase(in);
	  System.out.println("edu.temple.cis3238.wiki.utils.StringUtilsTest.testToCamelCase()\n"
			  + "--------------------------------------------------------------\n"
			  + "Output..: "
			  + result + "\n"
			  + "Expected: " + expResult
			  + "\n------------------------- END ----------------------------------\n");
	  assertEquals(expResult, result);
	  // TODO review the generated test code and remove the default call to fail.

   }

   /**
    * Test of fromCamelCase method, of class StringUtils.
    */
   @Test
   public void testFromCamelCase() {
	  System.out.println("fromCamelCase");
	  String in = "HelloWorldHowAreYou";
	  String expResult = "Hello World How Are You";
	  String result = StringUtils.fromCamelCase(in);
	  System.out.println("\n(1 of 2) - edu.temple.cis3238.wiki.utils.StringUtilsTest.testFromCamelCase()\n"
			  + "--------------------------------------------------------------\n"
			  + "Output..: "
			  + result + "\n"
			  + "Expected: " + expResult
			  + "\n-  - -  - -  -  - -  - -  -  - -  - -  -  - -  - -  -  - -  -\n");
	  assertEquals(expResult, result);
	  
//	  in = "Hello World How Are You";
//	   result = StringUtils.fromCamelCase(in);
//	  System.out.println("\n(2 of 2) - edu.temple.cis3238.wiki.utils.StringUtilsTest.testFromCamelCase()\n"
//			  + "--------------------------------------------------------------\n"
//			  + "Output..: "
//			  + result + "\n"
//			  + "Expected: " + expResult
//			  + "\n--------------------- END ------------------------------------\n");
	  assertEquals(expResult, result);
	  // TODO review the generated test code and remove the default call to fail.

   }

   /**
    * Test of formatDate method, of class StringUtils.
    */
   @Test
   public void testFormatDate_String() {
	  System.out.println("formatDate yyyy-MM-dd HH:mm:ss");
	  String dateStr = "2016-04-01 16:04:00:00.0000";
	  String expResult = "2016-04-01 16:04:00:00.0000";
	  String result = StringUtils.formatDate(dateStr);
	  assertEquals(expResult, result);
   }

//   /**
//    * Test of formatDate method, of class StringUtils.
//    */
//   @Test
//   public void testFormatDate_String_String() {
//	  System.out.println("formatDate");
//	  String dateStr = "";
//	  String formattedDateOut = "";
//	  String expResult = "";
//	  String result = StringUtils.formatDate(dateStr, formattedDateOut);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	 
//   }

   /**
    * Test of coalesce method, of class StringUtils.
    */
   @Test
   public void testCoalesce() {
	  System.out.println("coalesce");
	  Object[] items = {null,"","good!"};
	  String expResult = "good!";
	  String result = StringUtils.coalesce(items);
	  assertEquals(expResult, result);
	  
	  
   }

   /**
    * Test of getRandomString method, of class StringUtils.
    */
   @Test
   public void testGetRandomString() {
	  System.out.println("getRandomString: len 59");
	  String prefix = "prefix";
	  int length = 59;
	  String result = StringUtils.getRandomString(prefix, length);
	  assertNotNull(result);
	  System.out.println("1/3 edu.temple.cis3238.wiki.utils.StringUtilsTest.testGetRandomString()"
			    + "--------------------------------------------------------------\n"
			  + "Output..: "
			  + result + "\n"
			  + "Actual Length: \t"  
			  + result.length()  
			  +"\n" 
			  + "Expected Length:\t" 
			  + length 
			  + "\n------------------- END -----------------\n");
			  
	  assertEquals(result.substring(0, 6),prefix);
	  assertEquals(result.length(), 59);
	  
   }

   /**
    * Test of throwableStackTraceToString method, of class StringUtils.
    */
//   @Test
//   public void testThrowableStackTraceToString() {
//	  System.out.println("throwableStackTraceToString");
//	  Throwable exception = null;
//	  String expResult = "";
//	  String result = StringUtils.throwableStackTraceToString(exception);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of stackStraceAsStringDetails method, of class StringUtils.
//    */
//   @Test
//   public void testStackStraceAsStringDetails() {
//	  System.out.println("stackStraceAsStringDetails");
//	  Throwable e = null;
//	  String expResult = "";
//	  String result = StringUtils.stackStraceAsStringDetails(e);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }

   /**
    * Test of toS method, of class StringUtils.
    */
   @Test
   public void testToS_Object() {
	  System.out.println("toS");
	  Object source = null;
	  String expResult = "";
	  String result = StringUtils.toS(source);
	  assertEquals(expResult, result);
	  
   }

   /**
    * Test of toS method, of class StringUtils.
    */
   @Test
   public void testToS_Object_String() {
	  System.out.println("toS");
	  Object source = null;
	  String defaultVal = "ok";
	  String expResult = "ok";
	  String result = StringUtils.toS(source, defaultVal);
	  assertEquals(expResult, result);
   }

   /**
    * Test of toS method, of class StringUtils.
    */
   @Test
   public void testToS_int() {
	  System.out.println("toS");
	  int source = 1;
	  String expResult = "1";
	  String result = StringUtils.toS(source);
	  assertEquals(expResult, result);
	  
   }
//
//   /**
//    * Test of getLoremParagraphs method, of class StringUtils.
//    */
//   @Test
//   public void testGetLoremParagraphs() {
//	  System.out.println("getLoremParagraphs");
//	  int paragraphs = 0;
//	  String expResult = "";
//	  String result = StringUtils.getLoremParagraphs(paragraphs);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of getLoremWords method, of class StringUtils.
//    */
//   @Test
//   public void testGetLoremWords() {
//	  System.out.println("getLoremWords");
//	  int words = 0;
//	  String expResult = "";
//	  String result = StringUtils.getLoremWords(words);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of truncateAtWord method, of class StringUtils.
//    */
//   @Test
//   public void testTruncateAtWord() {
//	  System.out.println("truncateAtWord");
//	  String input = "";
//	  int length = 0;
//	  String expResult = "";
//	  String result = StringUtils.truncateAtWord(input, length);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of stripInvalidChars method, of class StringUtils.
//    */
//   @Test
//   public void testStripInvalidChars() {
//	  System.out.println("stripInvalidChars");
//	  String in = "";
//	  String expResult = "";
//	  String result = StringUtils.stripInvalidChars(in);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of removeHtmlMarkups method, of class StringUtils.
//    */
//   @Test
//   public void testRemoveHtmlMarkups() {
//	  System.out.println("removeHtmlMarkups");
//	  String val = "";
//	  String expResult = "";
//	  String result = StringUtils.removeHtmlMarkups(val);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }
//
//   /**
//    * Test of replaceEntities method, of class StringUtils.
//    */
//   @Test
//   public void testReplaceEntities() {
//	  System.out.println("replaceEntities");
//	  String src = "";
//	  String expResult = "";
//	  String result = StringUtils.replaceEntities(src);
//	  assertEquals(expResult, result);
//	  // TODO review the generated test code and remove the default call to fail.
//	  fail("The test case is a prototype.");
//   }

}
