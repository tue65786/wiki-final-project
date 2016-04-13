/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.parser;

import edu.temple.cis3238.wiki.vo.TopicVO;
import java.lang.reflect.Field;
import java.util.*;
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
public class TagsFromContentParserTest {

   public TagsFromContentParserTest() {
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
    * Test of create method, of class TagsFromContentParser.
    */
   @Test
   public void testExtract_String() {
	  System.out.println("create");
	  String _topicContent = "Hello {{there}} how are {{you}}";
	  String tagCSVActual;
	  String tagCSVExpected = "there,you";
	  TagsFromContentParser expResult = null;
	  TagsFromContentParser extractActualResult = TagsFromContentParser.create(_topicContent);
	  tagCSVActual = extractActualResult.getTagNameCSV();
	  System.out.println(
			  "edu.temple.cis3238.wiki.parser.ExtractFromWikiMarkupTest.testCreate_String()actual:\t" + tagCSVActual);
	  assertEquals(tagCSVExpected, tagCSVActual);
	  // TODO review the generated test code and remove the default call to fail.

   }

   /**
    * Test of extract set method, of class TagsFromContentParser.
    */
   @Test
   public void testExtract_Set() {
	  System.out.println("Extract set");
	  String _topicContent = "Hello {{there}} how are {{you}}";
	  TreeSet<String> expectedSet = new TreeSet<String>();
	  expectedSet.add("there");
	  expectedSet.add("you");
	  String[] items = new String[expectedSet.size()];
	  expectedSet.toArray(items);
	  System.out.println(
			  "edu.temple.cis3238.wiki.parser.ExtractFromWikiMarkupTest.testExtract_Set() Expected Set:");
	  for (int i = 0; i < expectedSet.size(); i++) {
		 System.out.print(items[i]);
		 if (i < expectedSet.size() - 1) {
			System.out.print(",");
		 }
	  }
	  System.out.println();
	  TagsFromContentParser extractActualResult = TagsFromContentParser.create(_topicContent);
	  System.out.println("Actual: "+ extractActualResult.getTagNameCSV()+"\n======================================\n");
	  assertEquals(extractActualResult.getTagNameSet(), expectedSet);
	  
   }

}
