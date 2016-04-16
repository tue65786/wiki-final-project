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
 * @author (c)2016 Doreen, Dan, Christian
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
   
   // TopicID = 1
   // TagLove,ShowMeTags,TagYoureIt

}

@Test
public void testTopicCRUD() {
   System.out.println( "\n\nWorking: testTopicCrud\n"
					   + "-----------------------" );
   GeneralDAO instance = new GeneralDAO( dbc );
   TopicVO actual;
   TopicVO expected = new TopicVOBuilder().setTopicName( "KKKKKKtestTopic" ).setTopicContent( "HellFDKSFLK;JDLDJGFDKJDLKJo world test" ).setRevisions( 0 ).build();
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
   TopicVO expectedTopic = new TopicVOBuilder().setTopicName( "JJJJtestTopic" ).setTopicContent( "Hello  {{TAG}} world test" ).setRevisions( 0 ).build();
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

   TagsVO expectedTag = new TagsVO( 0, "JJJJtestTag", 0 );
   tagID = instance.addTag( expectedTag );
   System.out.println( "Added Tag ID = " + tagID );
   expectedTag.setTagID( tagID );

   actualTag = instance.getTagByID( tagID );
   System.out.println( "Selected Tag By ID = " + actualTag.toString() );
   assertEquals( expectedTag, actualTag );
   tagsVOList.add( TagsVO.newInstance( actualTag ) );

   expectedTag = new TagsVO( 0, "JJJanothKKKer TestTag", 0 );
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
@Test
public void testUsersCRUD(){
   System.out.println( "\n\nWorking: testUsersCrud\n"
					   + "-----------------------" );
   GeneralDAO instance = new GeneralDAO( dbc );
   UsersVO actual;
   UsersVO expected = new UsersVO("kaTestKKKUser","kaTestKKKKPassword");
   boolean expectedDelete;
   boolean success;
   int userId;
   
   userId = instance.addUser(expected );
   System.out.println( "Added User ID = " + userId );
   expected.setUserID(userId );

   actual = instance.getUserByID( userId );
   System.out.println( "Selected User By ID = " + actual.toString() );
   assertEquals( expected, actual );

   actual = instance.findUserByName(expected.getUserName());
   System.out.println( "Selected User by UserName = " + actual.toString() );
   assertEquals( expected, actual );

   actual = instance.findUserByUserNameAndPassword(expected.getUserName(),expected.getPassword());
   System.out.println( "Selected User by UserName and Password = " + actual.toString() );
   assertEquals( expected, actual );
   
   System.out.println( "Updating User" );
   expected.setEmailAddress( "someone@somewhere.edu" );
   success = instance.updateUser(expected );
   assertEquals( success, true );
   actual = instance.getUserByID( userId );
   System.out.println( "Selected Updated User By ID = " + actual.toString() );
   assertEquals( expected, actual );

   System.out.println( "Deleting User" );
   expectedDelete = instance.deleteUser( expected );
   System.out.println( "Deleted User = " + actual.getUserName()+ " result = " + expectedDelete );
   assertEquals( expectedDelete, true );
}
}
