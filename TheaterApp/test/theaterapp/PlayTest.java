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
public class PlayTest {
    
    public PlayTest() {
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
     * Test of getTitle method, of class Play.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle"); // TODO review the generated test code and remove the default call to fail.
        Play instance = new Play();
        String expResult = "The King Lion";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImage method, of class Play.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage"); // TODO review the generated test code and remove the default call to fail.
        Play instance = new Play();
        String expResult = "../ConfigFiles/imgReyLeon.png";
        String result = instance.getImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Play.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription"); // TODO review the generated test code and remove the default call to fail.
        Play instance = new Play();
        String expResult = "Producido por Stage Entertainment, El Rey León es la mayor producción musical jamás representada en España. Apto para toda la familia, es una experiencia única que atrapa de principio a fin y que, tal y como ya han hecho más de 100 millones de personas en todo el mundo, hay que vivir, al menos, una vez en la vida. Por su espectacularidad, por su belleza, por la emoción que es capaz de transmitir...";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
