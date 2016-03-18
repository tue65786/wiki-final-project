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
dbc = new DbConnection();}

@After
public void tearDown() {
dbc.close();
}

   /**
    * Test of addTag method, of class GeneralDAO.
    */
   @Test
   public void testAddTag() {
	  System.out.println( "addTag" );
	  TagsVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  int expResult = 0;
	  int result = instance.addTag( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of addTopic method, of class GeneralDAO.
    */
   @Test
   public void testAddTopic() {
	  System.out.println( "addTopic" );
	  TopicVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  int expResult = 0;
	  int result = instance.addTopic( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of addUser method, of class GeneralDAO.
    */
   @Test
   public void testAddUser() {
	  System.out.println( "addUser" );
	  UsersVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  int expResult = 0;
	  int result = instance.addUser( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of assignTopicTags method, of class GeneralDAO.
    */
   @Test
   public void testAssignTopicTags() {
	  System.out.println( "assignTopicTags" );
	  TopicVO _topicVO = null;
	  ArrayList<TagsVO> _tagsVO = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  boolean expResult = false;
	  boolean result = instance.assignTopicTags( _topicVO, _tagsVO );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of findUserByUserNameAndPassword method, of class GeneralDAO.
    */
   @Test
   public void testFindUserByUserNameAndPassword() {
	  System.out.println( "findUserByUserNameAndPassword" );
	  String _username = "";
	  String _password = "";
	  GeneralDAO instance = new GeneralDAO(dbc);
	  UsersVO expResult = null;
	  UsersVO result = instance.findUserByUserNameAndPassword( _username, _password );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTagByID method, of class GeneralDAO.
    */
   @Test
   public void testGetTagByID() {
	  System.out.println( "getTagByID" );
	  int _id = 0;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  TagsVO expResult = null;
	  TagsVO result = instance.getTagByID( _id );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTags method, of class GeneralDAO.
    */
   @Test
   public void testGetTags() {
	  System.out.println( "getTags" );
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TagsVO> expResult = null;
	  ArrayList<TagsVO> result = instance.getTags();
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTagsByTopicID method, of class GeneralDAO.
    */
   @Test
   public void testGetTagsByTopicID() {
	  System.out.println( "getTagsByTopicID" );
	  int _topicid = 0;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TagsVO> expResult = null;
	  ArrayList<TagsVO> result = instance.getTagsByTopicID( _topicid );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicByID method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicByID() {
	  System.out.println( "getTopicByID" );
	  int _id = 0;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  TopicVO expResult = null;
	  TopicVO result = instance.getTopicByID( _id );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicByName method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicByName() {
	  System.out.println( "getTopicByName" );
	  String _name = "";
	  GeneralDAO instance = new GeneralDAO(dbc);
	  TopicVO expResult = null;
	  TopicVO result = instance.getTopicByName( _name );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicHistoryByTopicID method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicHistoryByTopicID() {
	  System.out.println( "getTopicHistoryByTopicID" );
	  int _id = 0;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicHistoryVO> expResult = null;
	  ArrayList<TopicHistoryVO> result = instance.getTopicHistoryByTopicID( _id );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicHistoryByTopicName method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicHistoryByTopicName() {
	  System.out.println( "getTopicHistoryByTopicName" );
	  String _name = "";
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicHistoryVO> expResult = null;
	  ArrayList<TopicHistoryVO> result = instance.getTopicHistoryByTopicName( _name );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopics method, of class GeneralDAO.
    */
   @Test
   public void testGetTopics() {
	  System.out.println( "getTopics" );
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicVO> expResult = null;
	  ArrayList<TopicVO> result = instance.getTopics();
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicsByTagID method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicsByTagID() {
	  System.out.println( "getTopicsByTagID" );
	  int _id = 0;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicVO> expResult = null;
	  ArrayList<TopicVO> result = instance.getTopicsByTagID( _id );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getTopicsByTagName method, of class GeneralDAO.
    */
   @Test
   public void testGetTopicsByTagName() {
	  System.out.println( "getTopicsByTagName" );
	  String _name = "";
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicVO> expResult = null;
	  ArrayList<TopicVO> result = instance.getTopicsByTagName( _name );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of getUsers method, of class GeneralDAO.
    */
   @Test
   public void testGetUsers() {
	  System.out.println( "getUsers" );
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<UsersVO> expResult = null;
	  ArrayList<UsersVO> result = instance.getUsers();
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of revertTopicFromHistory method, of class GeneralDAO.
    */
   @Test
   public void testRevertTopicFromHistory() {
	  System.out.println( "revertTopicFromHistory" );
	  TopicHistoryVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  TopicVO expResult = null;
	  TopicVO result = instance.revertTopicFromHistory( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of searchTopic method, of class GeneralDAO.
    */
   @Test
   public void testSearchTopic() {
	  System.out.println( "searchTopic" );
	  String _query = "";
	  GeneralDAO instance = new GeneralDAO(dbc);
	  ArrayList<TopicVO> expResult = null;
	  ArrayList<TopicVO> result = instance.searchTopic( _query );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of unassignTopicTags method, of class GeneralDAO.
    */
   @Test
   public void testUnassignTopicTags() {
	  System.out.println( "unassignTopicTags" );
	  TopicVO _topicVO = null;
	  ArrayList<TagsVO> _tagsVO = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  boolean expResult = false;
	  boolean result = instance.unassignTopicTags( _topicVO, _tagsVO );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of updateTopic method, of class GeneralDAO.
    */
   @Test
   public void testUpdateTopic() {
	  System.out.println( "updateTopic" );
	  TopicVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  boolean expResult = false;
	  boolean result = instance.updateTopic( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

   /**
    * Test of updateUser method, of class GeneralDAO.
    */
   @Test
   public void testUpdateUser() {
	  System.out.println( "updateUser" );
	  UsersVO _vo = null;
	  GeneralDAO instance = new GeneralDAO(dbc);
	  boolean expResult = false;
	  boolean result = instance.updateUser( _vo );
	  assertEquals( expResult, result );
	  // TODO review the generated test code and remove the default call to fail.
	  fail( "The test case is a prototype." );
   }

}
