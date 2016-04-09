/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.parser;

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
        String inputWikiText = "";
        String expResult = "";
        String result = Parser.parseAndAnnotate(inputWikiText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseAndCategorize method, of class Parser.
     */
    @Test
    public void testParseAndCategorize() {
        System.out.println("parseAndCategorize");
        String inputWikiText = "";
        ArrayList[] expResult = null;
        ArrayList[] result = Parser.parseAndCategorize(inputWikiText);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
