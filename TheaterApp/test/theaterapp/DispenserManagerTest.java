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
import urjc.UrjcBankServer;

/**
 *
 * @author KandV008
 */
public class DispenserManagerTest {
    
    public DispenserManagerTest() {
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
     * Test of showScreen method, of class DispenserManager.
     */
    @Test
    public void testShowScreen() {
        System.out.println("showScreen");
        int time = 30;
        
        Theater theater = new Theater();
        ScreenMode mode = ScreenMode.optionsMode;
        
        DispenserManager instance = new DispenserManager();
        Screen screen = new WellcomeScreen(theater, mode, instance);
        
        /*
        UrjcBankServer bank = new UrjcBankServer();
        Screen screen = new ErrorScreen(bank);
        */
        instance.showScreen(time, screen);
    }

    /**
     * Test of getTranslatorManager method, of class DispenserManager.
     */
    @Test
    public void testGetTranslatorManager() {
        System.out.println("getTranslatorManager");
        DispenserManager instance = new DispenserManager();
        TranslatorManager expResult = null;
        TranslatorManager result = instance.getTranslatorManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
