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
public class TheaterAreaStateTest {
    
    public TheaterAreaStateTest() {
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
     * Test of getSea method, of class TheaterAreaState.
     */
    @Test
    public void testGetSeat() {
        System.out.println("getSea");
        
        LocalDateTime now = LocalDateTime.now();
        Theater theater = new Theater();
        TheaterState state = new TheaterState(theater, now);
        TheaterAreaState instance = state.getArea(0);
        int cols = instance.getCols();
        int rows = instance.getRows();
        SeatState expResult = null;
        
        
        for(int col = 1; col <= cols; col++){
            for(int row = 1; row <= rows; row++){
                SeatState result = instance.getSeat(row, col);
                if(result != null){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        
    }

    /**
     * Test of setSeat method, of class TheaterAreaState.
     */
    @Test
    public void testSetSeat() {
        System.out.println("setSeat");
        int row = 0;
        int col = 0;
        SeatState state = null;
        TheaterAreaState instance = null;
    }

    /**
     * Test of getName method, of class TheaterAreaState.
     */
    @Test
    public void testGetName() {
        System.out.println("getName"); // TODO review the generated test code and remove the default call to fail.
        TheaterArea area = new TheaterArea("palco1.txt", 30, "palco1");
        TheaterAreaState instance = new TheaterAreaState(area);
        String expResult = area.getName();
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCols method, of class TheaterAreaState.
     */
    @Test
    public void testGetCols() {
        System.out.println("getCols"); // TODO review the generated test code and remove the default call to fail.
        TheaterArea area = new TheaterArea("palco2.txt", 30, "palco1");
        TheaterAreaState instance = new TheaterAreaState(area);
        int expResult = area.getCols();
        int result = instance.getCols();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRows method, of class TheaterAreaState.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows"); // TODO review the generated test code and remove the default call to fail.
        TheaterArea area = new TheaterArea("palco1.txt", 30, "palco1");
        TheaterAreaState instance = new TheaterAreaState(area);
        int expResult = area.getRows();
        int result = instance.getRows();
        assertEquals(expResult, result);  
    }

    /**
     * Test of getPrice method, of class TheaterAreaState.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice"); // TODO review the generated test code and remove the default call to fail.
        TheaterArea area = new TheaterArea("palco1.txt", 30, "palco1");
        TheaterAreaState instance = new TheaterAreaState(area);
        int expResult = 30;
        int result = instance.getPrice();
        assertEquals(expResult, result);
    }
    
}
