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
 * Arrange all the operation to translate a text to another language. The main
 * idiom is English.
 *
 * @author KandV008
 */
public class TranslatorManager {

    private Map<String, Translator> translatorMap;
    private String activeIdiom;

    /**
     * Initialize all by reading a text file.
     *
     */
    public TranslatorManager() {
        this.activeIdiom = StringManager.getTEXT_DEFAULT_LANGUAGE();
        this.translatorMap = new HashMap<>();
        String pathFile = FilePath.getPATH_TRADUCTOR_FILE();

        try {
            this.read(pathFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TranslatorManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo 'Traductor.txt' no accesible.");
        }
    }

    /**
     * Read the file with all the information about the languages.
     *
     * @param pathFile Path to the text file that is going to read
     * @throws FileNotFoundException
     */
    private void read(String pathFile) throws FileNotFoundException {
        File translationFile = new File(pathFile);
        Scanner scanner = new Scanner(translationFile);

        while (scanner.hasNextLine()) {
            String translatorLine = scanner.nextLine();
            this.analizeTranslatorLine(translatorLine);
        }
        
        scanner.close();
    }

    /**
     * Translate the msg depending of the language selected
     *
     * @param msg String that will be translate
     * @return String translated
     */
    public String translate(String msg) {
        String defaultLanguage = StringManager.getTEXT_DEFAULT_LANGUAGE();
        if (defaultLanguage.equals(activeIdiom)) {
            return msg;
        }

        if (!this.hasMessage(msg)) {
            return msg;
        }

        Translator activeTranslator = translatorMap.get(activeIdiom);
        String translatedMSG = activeTranslator.translate(msg);

        return translatedMSG;
    }

    /**
     * Check if exist the text in the translator to avoid errors
     *
     * @param text to analize
     * @return boolean
     */
    boolean hasMessage(String text) {
        String defaultLanguage = StringManager.getTEXT_DEFAULT_LANGUAGE();
        if (defaultLanguage.equals(this.activeIdiom)) {
            return true;
        }

        Translator translator = this.translatorMap.get(this.activeIdiom);
        return translator.hasText(text);
    }

    /**
     * Analize the line to create the translator and store it
     *
     * @param translatorLine That line contains all the infomation of the
     * current language
     * @throws FileNotFoundException
     */
    private void analizeTranslatorLine(String translatorLine) throws FileNotFoundException {
        String[] stringSplitted = translatorLine.split(":");
        Translator newTranslator = new Translator(stringSplitted[1]);
        this.translatorMap.put(stringSplitted[0], newTranslator);
    }

    /**
     * Change the current activeIdiom to a new one
     *
     * @param activeIdiom The idiom selected
     */
    public void setActiveIdiom(String activeIdiom) {
        this.activeIdiom = activeIdiom;
    }

    /**
     * Get the translatorMap from the class
     *
     * @return Map with all the translator
     */
    public Map<String, Translator> getTranslatorMap() {
        return this.translatorMap;
    }
}
