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
public class PasswordTest {
    
    public PasswordTest() {
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
     * Test of isValidPassword method, of class Password.
     */
    @Test
    public void testIsValidPassword() {
        System.out.println("isValidPassword");
        String password = "lj2kls /KJav7";
        boolean expResult = false;
        boolean result = Password.isValidPassword(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of isSpecialChar method, of class Password.
     */
    @Test
    public void testIsSpecialChar() {
        System.out.println("isSpecialChar");
        char c = '$';
        boolean expResult = true;
        boolean result = Password.isSpecialChar(c);
        assertEquals(expResult, result);
 
    }
    
    /**
     * Test of isValidUsername method, of class Password.
     */
    @Test
    public void testIsValidUsername() {
        System.out.println("isValidUsername");
        String username = "%lsdk";
        boolean expResult = false;
        boolean result = Password.isValidUsername(username);
        assertEquals(expResult, result);
 
    }
    
}
