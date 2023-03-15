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
public class TranslatorTest {
    
    public TranslatorTest() {
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
     * Test of translate method, of class Translator.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        String msg = "Select the day";
        String pathFile =
                "EnglishToSpanish.txt";
        Translator instance = new Translator(pathFile);
        String expResult = "Seleccione el día";
        String result = instance.translate(msg);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
