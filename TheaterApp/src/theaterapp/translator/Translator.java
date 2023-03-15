/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store all the vocabulary and phrases that are going to be used in the
 * program.
 *
 * @author KandV008
 */
public class Translator {

    private Map<String, String> messages;

    /**
     * Initialize the class loading all the information of the Translator file
     *
     * @param translatorFile String with the name of file with all the phrases
     */
    public Translator(String translatorFile) {
        this.messages = new HashMap<>();
        String dirPath = FilePath.getPATH_DIR_TRADUCTION_FILES();
        String pathFile = dirPath + translatorFile;

        try {
            this.read(pathFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo '" + translatorFile + ".txt' no accesible.");
        }
    }

    ;
    /**
     * Get the equivalent of the argument string from the language
     * 
     * @param msg String to be translated
     * @return String with the translated msg
     */
    public String translate(String msg) {
        return this.messages.get(msg);
    }

    ;
    /**
     * Read the text file
     * 
     * @param pathFile Path to the text file
     * @throws FileNotFoundException 
     */
    private void read(String pathFile) throws FileNotFoundException {
        File translationFile = new File(pathFile);
        Scanner scanner = new Scanner(translationFile);

        while (scanner.hasNextLine()) {
            String traductionLine = scanner.nextLine();
            this.analizeTraductionLine(traductionLine);
        }
        
        scanner.close();
    }

    /**
     * Store the phrase and the translation
     *
     * @param traductionLine Line that contains a phrase and the translation
     */
    private void analizeTraductionLine(String traductionLine) {
        String[] stringSplitted = traductionLine.split(":");
        this.messages.put(stringSplitted[0], stringSplitted[1]);
    }

    /**
     * Check if a text is stored or not to avoid errors
     *
     * @param text to analize
     * @return boolean
     */
    boolean hasText(String text) {
        return this.messages.containsKey(text);
    }
}
