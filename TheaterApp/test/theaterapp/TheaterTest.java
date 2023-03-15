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
public class TheaterTest {
    
    public TheaterTest() {
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
     * Test of getNumAreas method, of class Theater.
     */
    @Test
    public void testGetNumAreas() {
        System.out.println("getNumAreas");
        Theater instance = new Theater();
        int expResult = 5;
        int result = instance.getNumAreas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArea method, of class Theater.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        int pos = 1;
        Theater instance = new Theater();
        TheaterArea result = instance.getArea(pos);
        int expRow = 6;
        int expCol = 8;
        int expPrice = 30;
        String expName = "Palco2";
        System.out.println("Row");
        assertEquals(expRow, result.getRows());
        System.out.println("Col");
        assertEquals(expCol, result.getCols());
        System.out.println("Price");
        assertEquals(expPrice, result.getPrice());
        System.out.println("Name");
        assertEquals(expName, result.getName());
        
        for(int i = 0; i < expRow; i++){
            for(int j = 0; j < expCol; j++){
                if(result.getSeat(j, i) == SeatState.free){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
}
