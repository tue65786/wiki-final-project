/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.parser.TagColumnPredicate;
import edu.temple.cis3238.wiki.sql.DbConnection;
import static edu.temple.cis3238.wiki.utils.CollectionsUtilities.setToCSV;
import edu.temple.cis3238.wiki.vo.TagsVO;
import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dan
 */
public class CollectionsUtilitiesTest {

   DbConnection dbc;

   public CollectionsUtilitiesTest() {
   }

   @BeforeClass
   public static void setUpClass() {
   }

   @AfterClass
   public static void tearDownClass() {
   }

   @Before
   public void setUp() {
	  dbc = new DbConnection();
   }

   @After
   public void tearDown() {
	  dbc.close();
   }

   /**
    * Test of filterList+ pluck methods of class CollectionsUtilities.
    */
   @Test
   public void testFilterList() {
	   Set<String> actualSet=new TreeSet<String>();;
	  System.out.println("pluck column from Object and return CSV"
			  + "\n=======================================\n\n");
	  GeneralDAO dao = new GeneralDAO(dbc);
	  Set expResult = null;
	  
	  ArrayList<TagsVO> tagVOLIst;
	  String expected = "";
	  String actual;

	  tagVOLIst = dao.getTags();
	  Collections.sort(tagVOLIst);
	  for (int i = 0; i < tagVOLIst.size(); i++) {
		 expected += tagVOLIst.get(i).getTagName();
		 if (i < tagVOLIst.size() - 1) {
			expected += ",";
		 }
	  }
	  Set<String> result = CollectionsUtilities.pluckList(tagVOLIst, "tagname");
	  actualSet.addAll(result);
	  
	  actual = setToCSV(result);
	  
	  System.out.println("====================\nactual"
			  + "\n------------------------------------\n"
			  + actual 
			  + "\n------------------------------------\n");

	  System.out.println("====================\nexpected"
			  + "\n------------------------------------\n"
			  + expected 
			  + "\n------------------------------------\n");

	  assertEquals(result, actualSet);
   }

}
