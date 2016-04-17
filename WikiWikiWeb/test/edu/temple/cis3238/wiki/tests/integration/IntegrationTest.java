/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.tests.integration;

import edu.temple.cis3238.wiki.dao.GeneralDAO;
import edu.temple.cis3238.wiki.sql.DbConnection;
import edu.temple.cis3238.constants.QUERY_PARAMS;
import edu.temple.cis3238.wiki.vo.*;
import  edu.temple.cis3238.parser.Parser;
import edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicate;
import edu.temple.cis3238.wiki.utils.CollectionsUtilities;
import static edu.temple.cis3238.wiki.utils.CollectionsUtilities.setToCSV;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Integrated tests
 * @author d
 * @see GeneralDAO
 * @see GeneralDAO#getTopicByName(java.lang.String)
 * @see GeneralDAO#addTopic(edu.temple.cis3238.wiki.vo.TopicVO) 
 * @see GeneralDAO#deleteTopic(edu.temple.cis3238.wiki.vo.TopicVO) 
 * @see Parser
 * @see Parser#parseAndAnnotate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
 * @see TopicVO
 * @see TopicVOBuilder
 * @see TagsVO
 * @see DbConnection
 * 
 */
public class IntegrationTest {
	DbConnection dbc;
	GeneralDAO dao;
	public IntegrationTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	/**
	 * Instantiate DbConnection and DAO
	 */
	@Before
	public void setUp() {
		dbc = new DbConnection();
		dao = new GeneralDAO(dbc);
	}
	/**
	 * Close DbConnection
	 */
	
	@After
	public void tearDown() {
		dbc.close();
	}

@Test
public void testTopicIntegration() {
   System.out.println( "\nIntegrated tests: Create, select and parse topic\n"
					   + "---------------------------------------------------------" );
   
   TopicVO actual;
   String expResult = "Sample <a href=\"View.jsp?pTagID=tag\">tag</a> and <a style='font-weight:bold' href=\"View.jsp?pTopicID=topic\">topic</a>";
   TopicVO expected = new TopicVOBuilder().setTopicName( "IntegrationTestTopic" )
		   .setTopicContent( "Sample {{tag}} and [[topic]]" )
		   .setRevisions( 0 ).build();
   int topicId = dao.addTopic( expected );
   System.out.println( "Added Topic ID = " + topicId );
   expected.setTopicID( topicId );

   actual = dao.getTopicByID( topicId );
   System.out.println( "Selected Topic By ID = " + actual.toString() );
   assertEquals( expected, actual );

   actual = dao.getTopicByName( expected.getTopicName() );
   System.out.println( "Selected Topic by Name = " + actual.toString() );
   assertEquals( expected, actual );

   String result = Parser.parseAndAnnotate(actual.getTopicContent(),"View.jsp",QUERY_PARAMS.TOPIC_NAME, "View.jsp",
						QUERY_PARAMS.TAG_NAME);
   System.out.println( "Parsing selected Topic by Name\n------------------------ Expected: " + expResult +"\nActual: "+result+"\n-----------" );
assertEquals( result, expResult );
   System.out.println( "Deleting Topic" );
   boolean expectedDelete;
   expectedDelete = dao.deleteTopic( expected );
   System.out.println( "Deleted Topic = " + actual.getTopicName() + " result = " + expectedDelete );
   assertEquals( expectedDelete, true );
}

@Test
public void testTagIntegration() {
   System.out.println( "\nIntegrated tests: Create, select and parse topic tag\n"
					   + "-----------------------" );
   
   TopicVO actual;
   String expResult = "Sample <a href=\"View.jsp?pTagID=tag\">tag</a>";
   TopicVO expected = new TopicVOBuilder().setTopicName( "IntegrationTestTopicTag" )
		   .setTopicContent( "Sample {{tag}}" )
		   .setRevisions( 0 ).build();
   int topicId = dao.addTopic( expected );
   System.out.println( "Added Topic ID = " + topicId );
   expected.setTopicID( topicId );

   actual = dao.getTopicByID( topicId );
   System.out.println( "Selected Topic By ID = " + actual.toString() );
   assertEquals( expected, actual );

   actual = dao.getTopicByName( expected.getTopicName() );
   System.out.println( "Selected Topic by Name = " + actual.toString() );
   assertEquals( expected, actual );

   String result = Parser.parseAndAnnotate(actual.getTopicContent(),"View.jsp",QUERY_PARAMS.TOPIC_NAME, "View.jsp",
						QUERY_PARAMS.TAG_NAME);
   System.out.println( "Parsing selected Topic by Name\n------------------------ Expected: " + expResult +"\nActual: "+result+"\n-----------" );
assertEquals( result, expResult );
   System.out.println( "Deleting Topic" );
   boolean expectedDelete;
   expectedDelete = dao.deleteTopic( expected );
   System.out.println( "Deleted Topic = " + actual.getTopicName() + " result = " + expectedDelete +"--------------------------------------------\n\n");
   assertEquals( expectedDelete, true );
}

@Test
public void testTopicDAOandBuilder() {
   System.out.println( "\nIntegrated tests: Create, select topic from DB and compare: TopicVO vs TopicBuilder\n"
					   + "------------------------------------------------------------------------------" );
   TopicVO actual,expectedVO;
   expectedVO = new TopicVO("IntegrationTestTopicBuilder", "Sample topic builder");
   TopicVO expectedBuilder = new TopicVOBuilder().setTopicName( "IntegrationTestTopicBuilder" )
		   .setTopicContent( "Sample topic builder" )
		   .setRevisions( 0 ).build();
   int topicId = dao.addTopic( expectedBuilder );
   System.out.println( "Added Topic ID = " + topicId );
   expectedBuilder.setTopicID( topicId );

   actual = dao.getTopicByName( expectedBuilder.getTopicName() );
   System.out.println( "Selected Topic by Name = " + actual.toString() );
   assertEquals( expectedBuilder, actual );
   System.out.println("Expected: " + expectedVO.getTopicName() +"\t" + expectedVO.getTopicContent());
   System.out.println("Actual: " + actual.getTopicName() +"\t" + actual.getTopicContent());
	assertEquals(expectedVO.getTopicContent(), actual.getTopicContent());
	assertEquals(expectedVO.getTopicName(), actual.getTopicName());
   System.out.println( "Deleting Topic" );
   boolean expectedDelete;
   expectedDelete = dao.deleteTopic( expectedBuilder );
   System.out.println( "Deleted Topic = " + actual.getTopicName() + " result = " + expectedDelete );
   assertEquals( expectedDelete, true );
}

/**
	 * Integration test:
	 * <br/><ol><li>TopicVO: Topic create method<li>
	 * <li>TopicPredicate: Factory create TopicByTopicIDPredicate.</li>
	 * <li>Parse selected topic</li>
	 * </ol>
	 */
	@Test
	public void testCreateVOListApplyPredicateAndParse() {
   System.out.println( "\nIntegrated tests: Create Topic Collection, Preicate by TopicID and parse topic & tag\n"
					   + "-----------------------" );
		String expResult = "One <a href=\"View.jsp?pTagID=hundred\">hundred</a> and <a style='font-weight:bold' href=\"View.jsp?pTopicID=twenty\">twenty</a> two";
		ArrayList<TopicVO> actualVOList = new ArrayList<>(),expResultVOList;
		TopicVO expVO,actualVO;
		int theTopicID;
		TopicByTopicIDPredicate resultPredicate;
		actualVOList.add(new TopicVO(1, "Topic 1", "One", "", "", 0));
		actualVOList.add(new TopicVO(2, "Topic 2", "Two", "", "", 0));
		actualVOList.add(new TopicVO(11, "Topic 11", "Eleven", "", "", 0));
		actualVOList.add(new TopicVO(122, "Topic122", "One {{hundred}} and [[twenty]] two", "", "", 0));// < -- searching for
		actualVOList.add(new TopicVO(71, "Topic 71", "Seventy one", "", "", 0));
		theTopicID = 122;
		expResultVOList = new ArrayList<TopicVO>();
		expVO = new TopicVO(122, "Topic122", "One {{hundred}} and [[twenty]] two", "", "", 0);
		expResultVOList.add(expVO);
		resultPredicate = TopicByTopicIDPredicate.create(actualVOList, theTopicID);
		actualVO =resultPredicate.apply();
		System.out.println(
				"edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicateTest.testCreateAndApply()\n" +
						"-------------------------------------------------------------------------------------\n" +
						"Expected: "+ expVO.toString()
				+"\nActual: "+ actualVO.toString()+"\n---------------\n");
		assertEquals(theTopicID, actualVO.getTopicID());
		assertEquals(expVO, actualVO);
		String result = Parser.parseAndAnnotate(actualVO.getTopicContent(),"View.jsp",QUERY_PARAMS.TOPIC_NAME, "View.jsp",
						QUERY_PARAMS.TAG_NAME);
   System.out.println( "Parsing selected Topic by Name\n------------------------ Expected: " + expResult +"\nActual: "+result+"\n-----------\n\n" );	
		
	}
	
	 @Test
   public void testSelectTagsListAndFilterTagNamesToSet() {
	   Set<String> actualSet=new TreeSet<String>();;
	  System.out.println("\nIntegrated tests: Select Tags, Iterate TagNames to Set and translate set to CSV format."
			  + "\n--------------------------------------------------------------------------------\n");
	  
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
	  System.out.println("Tag names CSV\n====================\nActual"
			  + "\n------------------------------------\n"
			  + actual 
			  + "\n------------------------------------\n");

	  System.out.println("====================\nExpected"
			  + "\n------------------------------------\n"
			  + expected 
			  + "\n------------------------------------\n");

	  assertEquals(result, actualSet);
   }

}
