/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.herts.cs.sep.slyther.util;

import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author comqdhb
 */
public class Sha256Test {
    
    public Sha256Test() {
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
     * Test of hashString method, of class Sha256.
     */
    @Test
    public void testHashString() throws NoSuchAlgorithmException {
        System.out.println("hashString");
        String text = "hello world";
        String expResult = "b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9";
        String result = Sha256.hashString(text);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hashString method, of class Sha256.
     */
    @Test
    public void testHashString2() throws NoSuchAlgorithmException {
        System.out.println("hashString2");
        String text = "hello world!";
        String expResult = "7509e5bda0c762d2bac7f90d758b5b2263fa01ccbc542ab5e3df163be08e6ca9";
        String result = Sha256.hashString(text);
        assertEquals(expResult, result);
    }
}
