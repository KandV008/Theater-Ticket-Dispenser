/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package theaterapp;

import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KandV008
 */
public class TheaterStateTest {
    
    public TheaterStateTest() {
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
     * Test of getDate method, of class TheaterState.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Theater theater = new Theater();
        LocalDateTime date = LocalDateTime.now();
        TheaterState instance = new TheaterState(theater, date);
        LocalDateTime expResult = LocalDateTime.now();
        LocalDateTime result = instance.getDATE();
        System.out.println(expResult.toString());
        System.out.println(result.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumAreas method, of class TheaterState.
     */
    @Test
    public void testGetNumAreas() {
        System.out.println("getNumAreas");
        Theater theater = new Theater();
        LocalDateTime date = LocalDateTime.now();
        TheaterState instance = new TheaterState(theater, date);
        int expResult = theater.getNumAreas();
        int result = instance.getNumAreas();
        assertEquals(expResult, result);
  
    }

    /**
     * Test of getArea method, of class TheaterState.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        int pos = 0;
        Theater theater = new Theater();
        LocalDateTime date = LocalDateTime.now();
        TheaterState instance = new TheaterState(theater, date);
        TheaterAreaState expResult = null;
        TheaterAreaState result = instance.getArea(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
