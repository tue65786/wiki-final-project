/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.vo.TopicVO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Test filter of Topic list by topicID
 */
public class TopicByTopicIDPredicateTest {
	
	public TopicByTopicIDPredicateTest() {
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
	 * Test of create method, of class TopicByTopicIDPredicate.
	 */
	@Test
	public void testCreateAndApply() {
		System.out.println("Test: create and apply Topics Predicate");
		ArrayList<TopicVO> actualVOList = new ArrayList<>(),expResultVOList;
		TopicVO expVO,actualVO;
		int theTopicID;
		TopicByTopicIDPredicate resultPredicate;
		actualVOList.add(new TopicVO(1, "Topic 1", "One", "", "", 0));
		actualVOList.add(new TopicVO(2, "Topic 2", "Two", "", "", 0));
		actualVOList.add(new TopicVO(11, "Topic 11", "Eleven", "", "", 0));// < -- searching for
		actualVOList.add(new TopicVO(122, "Topic 122", "One hundred and twenty two", "", "", 0));
		actualVOList.add(new TopicVO(71, "Topic 71", "Seventy one", "", "", 0));
		theTopicID = 11;
		expResultVOList = new ArrayList<TopicVO>();
		expVO = new TopicVO(11, "11", "11", "", "", 0);
		expResultVOList.add(new TopicVO(11, "11", "11", "", "", 0));
		resultPredicate = TopicByTopicIDPredicate.create(actualVOList, theTopicID);
		actualVO =resultPredicate.apply();
		System.out.println(
				"edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicateTest.testCreateAndApply()\n" +
						"-------------------------------------------------------------------------------------\n" +
						"Expected: "+ expVO.toString()
				+"\nActual: "+ actualVO.toString()+"\n---------------\n");
		assertEquals(11, actualVO.getTopicID());
		assertEquals(expVO, actualVO);
		
	}	
}
