/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.security;

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
public class HashTest {
    
    public HashTest() {
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
     * Test of sha1HashCredentials method, of class Hash.
     */
    @Test
    public void testSha1HashCredentials() {
        System.out.println("sha1HashCredentials");
        String username = "Person";
        String password = "person";
        String expResult = "ff47eea506f8a4620ed05f94377e438555bb9279";
        String result = Hash.sha1HashCredentials(username, password);
      
        assertEquals(expResult, result);
    }
    
}
