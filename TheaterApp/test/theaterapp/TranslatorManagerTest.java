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
public class TranslatorManagerTest {
    
    public TranslatorManagerTest() {
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
     * Test of translate method, of class TranslatorManager.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        String msg = "Select the day";
        TranslatorManager instance;
        instance = new TranslatorManager();
        String activeIdiom = "Spanish";
        instance.setActiveIdiom(activeIdiom);
        String expResult = "Seleccione el día";
        String result = instance.translate(msg);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of setActiveIdiom method, of class TranslatorManager.
     */
    @Test
    public void testSetActiveIdiom() {
        System.out.println("setActiveIdiom"); // TODO review the generated test code and remove the default call to fail.
        String activeIdiom = "Spanish";
        TranslatorManager instance = new TranslatorManager();
        instance.setActiveIdiom(activeIdiom);
    }
    
}
