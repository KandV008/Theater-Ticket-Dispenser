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
public class SeatStateTest {
    
    public SeatStateTest() {
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
     * Test of values method, of class SeatState.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        SeatState[] expResult = null;
        SeatState[] result = SeatState.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class SeatState.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String string = "";
        SeatState expResult = null;
        SeatState result = SeatState.valueOf(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SeatState.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SeatState prueba = SeatState.free;
        String expResult = "free";
        String result = prueba.toString();
        assertEquals(expResult, result);
        System.out.println(prueba);
    }
    
}
