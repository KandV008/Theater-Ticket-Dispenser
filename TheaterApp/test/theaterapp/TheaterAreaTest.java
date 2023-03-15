/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package theaterapp;

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
public class TheaterAreaTest {
    
    public TheaterAreaTest() {
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
     * Test of getRows method, of class TheaterArea.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows"); // TODO review the generated test code and remove the default call to fail.
        String file = "palco1.txt";
        int price = 30;
        String name = "Palco1";
        TheaterArea instance = new TheaterArea(file, price, name);
        int expResult = 6;
        int result = instance.getRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCols method, of class TheaterArea.
     */
    @Test
    public void testGetCols() {
        System.out.println("getCols"); // TODO review the generated test code and remove the default call to fail.
        String file = "palco1.txt";
        int price = 30;
        String name = "Palco1";
        TheaterArea instance = new TheaterArea(file, price, name);
        int expResult = 9;
        int result = instance.getCols();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class TheaterArea.
     */
    @Test
    public void testGetName() {
        System.out.println("getName"); // TODO review the generated test code and remove the default call to fail.
        String file = "palco1.txt";
        int price = 30;
        String name = "Palco1";
        TheaterArea instance = new TheaterArea(file, price, name);
        String expResult = "Palco1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class TheaterArea.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice"); // TODO review the generated test code and remove the default call to fail.
        String file = "palco1.txt";
        int price = 30;
        String name = "Palco1";
        TheaterArea instance = new TheaterArea(file, price, name);
        int expResult = 30;
        int result = instance.getPrice();
        assertEquals(expResult, result);
    }
    
}
