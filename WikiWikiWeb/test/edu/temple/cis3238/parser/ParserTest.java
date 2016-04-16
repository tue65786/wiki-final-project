/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.parser;

import edu.temple.cis3238.constants.QUERY_PARAMS;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CAP
 */
public class ParserTest {
    
    public ParserTest() {
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
     * Test of parseAndAnnotate method, of class Parser.
     */
    @Test
    public void testParseAndAnnotate() {
        System.out.println("parseAndAnnotate");
        String inputWikiText = "The {{dog}} ate the [[cat]]";
        String expResult = "The <a href=\"View.jsp?pTagID=dog\">dog</a> ate the <a style='font-weight:bold' href=\"View.jsp?pTopicID=cat\">cat</a>";
        String result = Parser.parseAndAnnotate(inputWikiText,"View.jsp",QUERY_PARAMS.TOPIC_NAME, "View.jsp",
						QUERY_PARAMS.TAG_NAME);
		
		//The <a href="[tag.jsp?id=dog">dog</a> ate the <a style='font-weight:bold' href="wiki.jsp?id=cat">cat</a>]
        assertEquals(expResult, result);
       
    }

    /**
     * Test of parseAndCategorize method, of class Parser.
     */
    @Test
    public void testParseAndCategorize() {
        System.out.println("parseAndCategorize");
        String inputWikiText = "The {{dog}} ate the [[cat]]";
        ArrayList<String> topics = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        topics.add("cat");
        tags.add("dog");
        ArrayList[] expResult = {topics, tags};
        ArrayList[] result = Parser.parseAndCategorize(inputWikiText);
        assertArrayEquals(expResult, result);
      
    }
    
}
