/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Screen that manage all the operative about the language selection
 *
 * @author KandV008
 */
public class IdiomSelectionScreen extends Screen {

    private HashMap<Character, String> optionsMap;
    private TranslatorManager translator;

    /**
     * Initialize the Screen with the information alocated in the Translator
     * Manager
     *
     * @param mode
     * @param manager
     * @param translator
     */
    public IdiomSelectionScreen(ScreenMode mode, DispenserManager manager,
            TranslatorManager translator) {
        super(StringManager.getTITLE_IDIOM_SELECTION_SCREEN(), mode, manager);
        this.setDispenserManager(manager);
        this.setMode(mode);
        this.translator = translator;
    }

    /**
     * Prepare the screen before the begining
     *
     * @param hardware
     * @return ScreenResult.continueScreen
     */
    @Override
    public ScreenResult begin(DispenserHardware hardware) {
        this.generateOptions();
        return ScreenResult.continueScreen;
    }

    /**
     * Generate the options that will be showed at the screen
     */
    private void generateOptions() {
        List<String> options = new ArrayList<>();
        Map<String, Translator> languages = this.translator.getTranslatorMap();
        char indexChar = 'A';
        this.optionsMap = new HashMap<>();

        for (String languageName : languages.keySet()) {
            options.add(languageName);
            this.optionsMap.put(indexChar, languageName);
            indexChar++;
        }

        String english = StringManager.getTEXT_DEFAULT_LANGUAGE();
        options.add(english);
        this.optionsMap.put(indexChar, english);

        String cancel = StringManager.getTEXT_CANCEL();
        options.add(cancel);
        this.setOptions(options);
    }

    /**
     * Do the correspondient operation depending of the button pressed
     *
     * @param hardware
     * @param option
     * @return ScreenResult.exitScreen
     */
    @Override
    public ScreenResult optionButtonPressed(
            DispenserHardware hardware, char option) {
        boolean isLanguageSelected = !(option == '0' || option == 'F');

        if (!isLanguageSelected) {
            return ScreenResult.exitScreen;
        }

        String idiomSelected = this.optionsMap.get(option);
        this.translator.setActiveIdiom(idiomSelected);

        return ScreenResult.exitScreen;
    }
}
