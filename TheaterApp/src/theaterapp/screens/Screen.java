/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.List;

/**
 * Abstrac class with all the primary operation to be implemented when it is
 * inherit, to be adapted depending on the objective of the child class
 *
 * @author KandV008
 */
public class Screen {

    private DispenserManager dispenserManager;
    private String title = "";
    private String description = "";
    private String image = "";
    private List<String> options;
    private ScreenMode mode;
    private boolean errorOccurred = false;

    /**
     * Initialize the Screen with a Screen Mode and a Dispenser Manager
     *
     * @param mode
     * @param manager
     */
    public Screen(ScreenMode mode, DispenserManager manager) {
        this.mode = mode;
        this.dispenserManager = manager;
    }

    /**
     * Initialize the Screen with the title
     *
     * @param title
     * @param mode
     * @param manager
     */
    public Screen(String title, ScreenMode mode, DispenserManager manager) {
        this.title = title;
        this.mode = mode;
        this.dispenserManager = manager;
    }

    /**
     * Initialize the Screen with the title and the description
     *
     * @param title
     * @param description
     */
    public Screen(String title, String description) {
        this.title = title;
        this.description = description;
    }


    /**
     * Method to prepare the class at the begining
     *
     * @param hardware
     * @return continueScreen
     */
    public ScreenResult begin(DispenserHardware hardware) {

        return ScreenResult.continueScreen;
    }

    /**
     * Method to finalize the class at the end
     *
     * @param hardware
     * @return exitScreen
     */
    public ScreenResult end(DispenserHardware hardware) {

        hardware.createNewTicket();
        return ScreenResult.exitScreen;
    }

    /**
     * Add to the ticket a text
     *
     * @param ticket
     * @param text to add
     */
    public void storeInformation(Ticket ticket, String text) {
        ticket.addInformation(text);
    }

    /**
     * Due an action depending of the button pressed
     *
     * @param hardware
     * @param option
     * @return ScreenResult to continue or end the current screen
     */
    public ScreenResult optionButtonPressed(
            DispenserHardware hardware, char option) {
        return ScreenResult.exitScreen;
    }

    /**
     * Mark o desmark a seat button when is pressed
     *
     * @param hardware
     * @param row
     * @param col
     * @return ContinueScreen
     */
    public ScreenResult seatButtonPressed(
            DispenserHardware hardware, int row, int col) {
        return ScreenResult.continueScreen;
    }

    /**
     * When detect the credit card, realize the correspondient operations
     *
     * @param hardware
     * @return
     */
    public ScreenResult creditCardDetected(DispenserHardware hardware) {
        return ScreenResult.continueScreen;
    }

    /**
     * @return the area state
     */
    public TheaterAreaState getAreaState() {
        return null;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * @return the mode
     */
    public ScreenMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(ScreenMode mode) {
        this.mode = mode;
    }

    /**
     * @return the dispenserManager
     */
    public DispenserManager getDispenserManager() {
        return dispenserManager;
    }

    /**
     * @param dispenserManager the dispenserManager to set
     */
    public void setDispenserManager(DispenserManager dispenserManager) {
        this.dispenserManager = dispenserManager;
    }

    /**
     * @return if an error had occurred
     */
    public boolean isAnErrorScreen() {
        return this.errorOccurred;
    }

    /**
     * Set if it is an error screen
     *
     * @param isAnErrorScreen
     */
    public void setErrorScreen(boolean isAnErrorScreen) {
        this.errorOccurred = isAnErrorScreen;
    }
}
