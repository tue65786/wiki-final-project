/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import edu.temple.cis3238.wiki.sql.*;
import edu.temple.cis3238.wiki.vo.*;
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
public class GeneralDAOTest {

DbConnection dbc;
TopicVO topicVO;
TagsVO tagsVO;
ArrayList<TagsVO> tagsVOList;
UsersVO usersVO;

public GeneralDAOTest() {
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
   tagsVOList = new ArrayList<>();

}

@After
public void tearDown() {
   dbc.close();
}

@Test
public void testTagCRUD() {
   System.out.println( "Working: testTagCrud\n"
					   + "--------------------" );
   GeneralDAO instance = new GeneralDAO( dbc );
   TagsVO actual;
   boolean expectedDelete;
   TagsVO expected = new TagsVO( 0, "testTag", 0 );
   int tagID = instance.addTag( expected );
   System.out.println( "Added Tag ID = " + tagID );
   expected.setTagID( tagID );

   actual = instance.getTagByID( tagID );
   System.out.println( "Selected Tag By ID = " + actual.toString() );
   assertEquals( expected, actual );

   actual = instance.getTagByName( expected.getTagName() );
   System.out.println( "Selected Tag by Name = " + actual.toString() );
   assertEquals( expected, actual );
   expectedDelete = instance.deleteTag( actual );
   System.out.println( "Deleted Tag = " + actual.getTagID() + " result = " + expectedDelete );
   assertEquals( expectedDelete, true );

}

@Test
public void testTopicCRUD() {
   System.out.println( "\n\nWorking: testTopicCrud\n"
					   + "-----------------------" );
   GeneralDAO instance = new GeneralDAO( dbc );
   TopicVO actual;
   TopicVO expected = new TopicVOBuilder().setTopicName( "testTopic" ).setTopicContent( "Hello world test" ).setRevisions( 0 ).build();
   int topicId = instance.addTopic( expected );
   System.out.println( "Added Topic ID = " + topicId );
   expected.setTopicID( topicId );

   actual = instance.getTopicByID( topicId );
   System.out.println( "Selected Topic By ID = " + actual.toString() );
   assertEquals( expected, actual );

   actual = instance.getTopicByName( expected.getTopicName() );
   System.out.println( "Selected Topic by Name = " + actual.toString() );
   assertEquals( expected, actual );

   System.out.println( "Updating Topic" );
   expected.setTopicContent( "Hello world updated test" );
   boolean success = instance.updateTopic( expected );
   assertEquals( success, true );
   actual = instance.getTopicByID( topicId );
   System.out.println( "Selected Updated Topic By ID = " + actual.toString() );
   assertEquals( expected, actual );

   System.out.println( "Deleting Topic" );
   boolean expectedDelete;
   expectedDelete = instance.deleteTopic( expected );
   System.out.println( "Deleted Topic = " + actual.getTopicName() + " result = " + expectedDelete );
   assertEquals( expectedDelete, true );
}

@Test
public void testTopicAssignUnassignTags() {
   System.out.println( "\n\nWorking: testTopicAssignUnassignTags\n"
					   + "------------------------------------" );
   GeneralDAO instance = new GeneralDAO( dbc );
   TopicVO actualTopic;
   TopicVO expectedTopic = new TopicVOBuilder().setTopicName( "testTopic" ).setTopicContent( "Hello world test" ).setRevisions( 0 ).build();
   ArrayList<TagsVO> actualTagsVOList;
   boolean expectedDelete;
   TagsVO actualTag;
   int tagID;
   int topicID;
   boolean bindingSuccess;
   topicID = instance.addTopic( expectedTopic );
   System.out.println( "Added Topic ID = " + topicID );
   expectedTopic.setTopicID( topicID );

   actualTopic = instance.getTopicByID( topicID );
   System.out.println( "Selected Topic By ID = " + actualTopic.toString() );
   assertEquals( expectedTopic, actualTopic );

   TagsVO expectedTag = new TagsVO( 0, "testTag", 0 );
   tagID = instance.addTag( expectedTag );
   System.out.println( "Added Tag ID = " + tagID );
   expectedTag.setTagID( tagID );

   actualTag = instance.getTagByID( tagID );
   System.out.println( "Selected Tag By ID = " + actualTag.toString() );
   assertEquals( expectedTag, actualTag );
   tagsVOList.add( TagsVO.newInstance( actualTag ) );

   expectedTag = new TagsVO( 0, "another TestTag", 0 );
   tagID = instance.addTag( expectedTag );
   System.out.println( "Added Tag ID = " + tagID );
   expectedTag.setTagID( tagID );
   actualTag = instance.getTagByID( tagID );
   System.out.println( "Selected Another Tag By ID = " + actualTag.toString() );
   assertEquals( expectedTag, actualTag );
   tagsVOList.add( TagsVO.newInstance( actualTag ) );

   System.out.println( "Check Tags array size" );
   assertEquals( tagsVOList.size(), 2 );
   System.out.println( "Assign Tags to topic" );
   bindingSuccess = instance.assignTopicTags( actualTopic, tagsVOList );
   assertEquals( bindingSuccess, true );

   actualTagsVOList = instance.getTagsByTopicID( topicID );
   System.out.println( "Assigned Tags Count = " + actualTagsVOList.size() );
   assertEquals( tagsVOList, actualTagsVOList );

   System.out.println( "Unassign Tags from Topic" );
   bindingSuccess = instance.unassignTopicTags( actualTopic, tagsVOList );
   assertEquals( bindingSuccess, true );

   System.out.println( "Deleting Tags (loop)" );
   for ( TagsVO aVO : tagsVOList ) {
	  expectedDelete = instance.deleteTag( aVO );
	  assertEquals( expectedDelete, true );
	  System.out.println( "Deleted Tag = " + aVO.getTagID() + " result = " + expectedDelete );
   }

   System.out.println( "Deleting Topic" );
   expectedDelete = instance.deleteTopic( expectedTopic );
   System.out.println( "Deleted Topic = " + actualTopic.getTopicName() + " result = " + expectedDelete );
   assertEquals( expectedDelete, true );

}
//
///**
// * Test of addTag method, of class GeneralDAO.
// */
//@Test
//public void testAddTag() {
//   System.out.println( "addTag" );
//   TagsVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   int expResult = 0;
//   int result = instance.addTag( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
//
//
///**
// * Test of addTopic method, of class GeneralDAO.
// */
//@Test
//public void testAddTopic() {
//   System.out.println( "addTopic" );
//   TopicVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   int expResult = 0;
//   int result = instance.addTopic( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of addUser method, of class GeneralDAO.
// */
//@Test
//public void testAddUser() {
//   System.out.println( "addUser" );
//   UsersVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   int expResult = 0;
//   int result = instance.addUser( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of assignTopicTags method, of class GeneralDAO.
// */
//@Test
//public void testAssignTopicTags() {
//   System.out.println( "assignTopicTags" );
//   TopicVO _topicVO = null;
//   ArrayList<TagsVO> _tagsVO = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   boolean expResult = false;
//   boolean result = instance.assignTopicTags( _topicVO, _tagsVO );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of findUserByUserNameAndPassword method, of class GeneralDAO.
// */
//@Test
//public void testFindUserByUserNameAndPassword() {
//   System.out.println( "findUserByUserNameAndPassword" );
//   String _username = "";
//   String _password = "";
//   GeneralDAO instance = new GeneralDAO( dbc );
//   UsersVO expResult = null;
//   UsersVO result = instance.findUserByUserNameAndPassword( _username, _password );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTagByID method, of class GeneralDAO.
// */
//@Test
//public void testGetTagByID() {
//   System.out.println( "getTagByID" );
//   int _id = 0;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   TagsVO expResult = null;
//   TagsVO result = instance.getTagByID( _id );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTags method, of class GeneralDAO.
// */
//@Test
//public void testGetTags() {
//   System.out.println( "getTags" );
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TagsVO> expResult = null;
//   ArrayList<TagsVO> result = instance.getTags();
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTagsByTopicID method, of class GeneralDAO.
// */
//@Test
//public void testGetTagsByTopicID() {
//   System.out.println( "getTagsByTopicID" );
//   int _topicid = 0;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TagsVO> expResult = null;
//   ArrayList<TagsVO> result = instance.getTagsByTopicID( _topicid );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicByID method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicByID() {
//   System.out.println( "getTopicByID" );
//   int _id = 0;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   TopicVO expResult = null;
//   TopicVO result = instance.getTopicByID( _id );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicByName method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicByName() {
//   System.out.println( "getTopicByName" );
//   String _name = "";
//   GeneralDAO instance = new GeneralDAO( dbc );
//   TopicVO expResult = null;
//   TopicVO result = instance.getTopicByName( _name );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicHistoryByTopicID method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicHistoryByTopicID() {
//   System.out.println( "getTopicHistoryByTopicID" );
//   int _id = 0;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicHistoryVO> expResult = null;
//   ArrayList<TopicHistoryVO> result = instance.getTopicHistoryByTopicID( _id );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicHistoryByTopicName method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicHistoryByTopicName() {
//   System.out.println( "getTopicHistoryByTopicName" );
//   String _name = "";
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicHistoryVO> expResult = null;
//   ArrayList<TopicHistoryVO> result = instance.getTopicHistoryByTopicName( _name );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopics method, of class GeneralDAO.
// */
//@Test
//public void testGetTopics() {
//   System.out.println( "getTopics" );
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicVO> expResult = null;
//   ArrayList<TopicVO> result = instance.getTopics();
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicsByTagID method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicsByTagID() {
//   System.out.println( "getTopicsByTagID" );
//   int _id = 0;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicVO> expResult = null;
//   ArrayList<TopicVO> result = instance.getTopicsByTagID( _id );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTopicsByTagName method, of class GeneralDAO.
// */
//@Test
//public void testGetTopicsByTagName() {
//   System.out.println( "getTopicsByTagName" );
//   String _name = "";
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicVO> expResult = null;
//   ArrayList<TopicVO> result = instance.getTopicsByTagName( _name );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getUsers method, of class GeneralDAO.
// */
//@Test
//public void testGetUsers() {
//   System.out.println( "getUsers" );
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<UsersVO> expResult = null;
//   ArrayList<UsersVO> result = instance.getUsers();
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of revertTopicFromHistory method, of class GeneralDAO.
// */
//@Test
//public void testRevertTopicFromHistory() {
//   System.out.println( "revertTopicFromHistory" );
//   TopicHistoryVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   TopicVO expResult = null;
//   TopicVO result = instance.revertTopicFromHistory( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of searchTopic method, of class GeneralDAO.
// */
//@Test
//public void testSearchTopic() {
//   System.out.println( "searchTopic" );
//   String _query = "";
//   GeneralDAO instance = new GeneralDAO( dbc );
//   ArrayList<TopicVO> expResult = null;
//   ArrayList<TopicVO> result = instance.searchTopic( _query );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of unassignTopicTags method, of class GeneralDAO.
// */
//@Test
//public void testUnassignTopicTags() {
//   System.out.println( "unassignTopicTags" );
//   TopicVO _topicVO = null;
//   ArrayList<TagsVO> _tagsVO = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   boolean expResult = false;
//   boolean result = instance.unassignTopicTags( _topicVO, _tagsVO );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of updateTopic method, of class GeneralDAO.
// */
//@Test
//public void testUpdateTopic() {
//   System.out.println( "updateTopic" );
//   TopicVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   boolean expResult = false;
//   boolean result = instance.updateTopic( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of updateUser method, of class GeneralDAO.
// */
//@Test
//public void testUpdateUser() {
//   System.out.println( "updateUser" );
//   UsersVO _vo = null;
//   GeneralDAO instance = new GeneralDAO( dbc );
//   boolean expResult = false;
//   boolean result = instance.updateUser( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of getTagByName method, of class GeneralDAO.
// */
//@Test
//public void testGetTagByName() {
//   System.out.println( "getTagByName" );
//   String _name = "";
//   GeneralDAO instance = null;
//   TagsVO expResult = null;
//   TagsVO result = instance.getTagByName( _name );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of deleteTag method, of class GeneralDAO.
// */
//@Test
//public void testDeleteTag() {
//   System.out.println( "deleteTag" );
//   TagsVO _vo = null;
//   GeneralDAO instance = null;
//   boolean expResult = false;
//   boolean result = instance.deleteTag( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}
//
///**
// * Test of deleteTopic method, of class GeneralDAO.
// */
//@Test
//public void testDeleteTopic() {
//   System.out.println( "deleteTopic" );
//   TopicVO _vo = null;
//   GeneralDAO instance = null;
//   boolean expResult = false;
//   boolean result = instance.deleteTopic( _vo );
//   assertEquals( expResult, result );
//   // TODO review the generated test code and remove the default call to fail.
//   fail( "The test case is a prototype." );
//}

}
