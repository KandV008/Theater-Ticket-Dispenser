/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Screen that manage all the operative about the selection of Theater Area
 *
 * @author KandV008
 */
public class WellcomeScreen extends Screen {

    private Play play;
    private List<Screen> options;
    private Theater theater;

    /**
     * Screem that manage all the operative about the Main Menu
     *
     * @param theater
     * @param mode
     * @param manager
     */
    public WellcomeScreen(Theater theater,
            ScreenMode mode, DispenserManager manager) {
        super(mode, manager);
        this.theater = theater;
        this.play = new Play();
        this.fillScreenProperties();
    }

    /**
     * Fill the main attributes of the Screen
     */
    private void fillScreenProperties() {
        String titleScreen = play.getTitle();
        this.setTitle(titleScreen);

        String descriptionScreen = play.getDescription();
        this.setDescription(descriptionScreen);

        String imageScreen = play.getImage();
        this.setImage(imageScreen);
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
     * Sets the WellcomeScreen in an infinite loop until the hardware shuts down
     * @param hardware
     * @return ScreenResult.continueScreen
     */
    @Override
    public ScreenResult end(DispenserHardware hardware) {
        return ScreenResult.continueScreen;
    }

    /**
     * Do the correspondient operation depending of the button pressed
     *
     * @param hardware
     * @param option
     * @return
     */
    @Override
    public ScreenResult optionButtonPressed(
            DispenserHardware hardware, char option) {

        DispenserManager manager = this.getDispenserManager();
        Screen nextScreen = this.getNextScreen(hardware, option);
        int time = 30;

        if (nextScreen == null) {
            return ScreenResult.exitScreen;
        }

        manager.showScreen(time, nextScreen);
        return ScreenResult.continueScreen;
    }

    /**
     * Obtain the next screen depending in the option selected
     *
     * @param hardware
     * @param option
     * @return the next screen
     */
    private Screen getNextScreen(DispenserHardware hardware, char option) {
        Screen nextScreen;
        Ticket ticket = hardware.getTicket();
        String ticketType;

        if ('A' == option) {
            String namePlay = this.play.getTitle();
            ticketType = this.generateTicketText(hardware, namePlay);
            this.storeInformation(ticket, ticketType);
            nextScreen = this.options.get(0);
            return nextScreen;
        }

        if ('B' == option) {
            nextScreen = this.options.get(1);
            return nextScreen;
        }

        return null;
    }

    /**
     * @return the options that will be showed at the screen
     */
    @Override
    public List<String> getOptions() {
        this.generateOptions();
        List<String> textsList = new ArrayList<>();

        for (Screen screen : this.options) {
            String textOption = screen.getTitle();
            textsList.add(textOption);
        }

        return textsList;
    }

    /**
     * Generates the options available
     */
    private void generateOptions() {
        DispenserManager manager = this.getDispenserManager();
        ScreenMode mode = this.getMode();
        this.options = new ArrayList<>();

        Screen dateSelector
                = new DateSelectionScreen(this.theater, mode, manager);
        this.options.add(dateSelector);

        TranslatorManager translator = manager.getTranslatorManager();
        Screen idiomSelector = new IdiomSelectionScreen(mode, manager, translator);
        this.options.add(idiomSelector);
    }

    /**
     * Generate the String with the information of the selection
     *
     * @param hardware
     * @return the string with the information
     */
    private String generateTicketText(DispenserHardware hardware, String product) {
        product = hardware.translateTicket(product);

        String ticket = StringManager.getTEXT_TICKET();
        ticket = hardware.translateTicket(ticket);

        String ticketType = ticket + ":" + product;

        return ticketType;
    }
}
