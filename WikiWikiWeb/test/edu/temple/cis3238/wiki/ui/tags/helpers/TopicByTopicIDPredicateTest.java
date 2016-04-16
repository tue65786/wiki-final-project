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
 * 
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
		System.out.println("create");
		ArrayList<TopicVO> _voList = new ArrayList<>();
		_voList.add(new TopicVO(1, "1", "1", "", "", 0));
		_voList.add(new TopicVO(2, "2", "2", "", "", 0));
		_voList.add(new TopicVO(11, "11", "11", "", "", 0));
		_voList.add(new TopicVO(122, "122", "122", "", "", 0));
		_voList.add(new TopicVO(71, "71", "71", "", "", 0));
		int _topicID = 11;
		ArrayList<TopicVO> expResult = new ArrayList<TopicVO>();
		TopicVO expVO = new TopicVO(11, "11", "11", "", "", 0),actualVO;
		expResult.add(new TopicVO(11, "11", "11", "", "", 0));
		TopicByTopicIDPredicate result = TopicByTopicIDPredicate.create(_voList, _topicID);
		actualVO =result.apply();
		assertEquals(11, actualVO.getTopicID());
		assertEquals(expVO, actualVO);
		
	}	
}
