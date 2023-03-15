/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package theaterapp;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author KandV008
 */
public class DispenserHardwareTest {
    
    public DispenserHardwareTest() {
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
     * Test of printTicket method, of class DispenserHardware.
     */
    @Test
    public void testPrintTicket() {
        System.out.println("printTicket");
        TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
        TranslatorManager translator = new TranslatorManager();
        DispenserHardware instance = new DispenserHardware(dispenser, translator);
        instance.printTicket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retainCreditCard method, of class DispenserHardware.
     */
    @Test
    public void testRetainCreditCard() {
        System.out.println("retainCreditCard");
        boolean doIt = false;
        DispenserHardware instance = null;
        instance.retainCreditCard(doIt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expelCreditCard method, of class DispenserHardware.
     */
    @Test
    public void testExpelCreditCard() {
        System.out.println("expelCreditCard");
        int time = 0;
        DispenserHardware instance = null;
        instance.expelCreditCard(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCardNumber method, of class DispenserHardware.
     */
    @Test
    public void testGetCardNumber() {
        System.out.println("getCardNumber");
        DispenserHardware instance = null;
        long expResult = 0L;
        long result = instance.getCardNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
