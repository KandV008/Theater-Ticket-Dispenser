/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 * Class that manage all the relative to the software of the Ticket Dispenser
 *
 * @author KandV008
 */
public class DispenserManager {

    private final TranslatorManager TRANSLATOR;
    private final TheaterTicketDispenser DISPENSER;
    private final DispenserHardware HARDWARE;
    private final DisplayManager DISPLAYER;

    /**
     * Initialize all the attributes by default
     */
    public DispenserManager() {
        this.TRANSLATOR = new TranslatorManager();
        this.DISPENSER = new TheaterTicketDispenser();
        this.HARDWARE = new DispenserHardware(this.DISPENSER, this.TRANSLATOR);
        this.DISPLAYER = new DisplayManager(this.DISPENSER, this.TRANSLATOR);
    }

    /**
     * Show all the attributes of the current screen
     *
     * @param time Screen display time
     * @param screen
     */
    public void showScreen(int time, Screen screen) {
        this.DISPLAYER.cleanOptions();
        ScreenResult canContinue = screen.begin(HARDWARE);

        if (canContinue == ScreenResult.exitScreen) {
            return;
        }

        do {
            ScreenMode nextMode = screen.getMode();

            if (nextMode == ScreenMode.optionsMode) {
                this.operationAtMenuMode(screen, time);
            }

            if (nextMode == ScreenMode.theatherMode) {
                this.operationAtTheaterMode(screen, time);
            }

            if (nextMode == ScreenMode.messageMode) {
                this.operationAtMessageMode(screen, time);
            }

            canContinue = screen.end(HARDWARE);
        } while (canContinue == ScreenResult.continueScreen);

    }

    /**
     * Operate the screen that uses the Menu Mode
     *
     * @param screen
     * @param time Screen Time Display
     */
    private void operationAtMenuMode(Screen screen, int time) {
        ScreenResult willContinue = ScreenResult.continueScreen;

        do {
            this.DISPLAYER.cleanOptions();
            this.DISPLAYER.displayMenuInformation(screen);
            char nextEvent = DISPENSER.waitEvent(time);

            if ('1' != nextEvent) {
                willContinue = screen.optionButtonPressed(HARDWARE, nextEvent);
            }

        } while (willContinue == ScreenResult.continueScreen);
    }

    /**
     * Operate the Screen that uses the Theater Mode
     *
     * @param screen
     * @param time Screen Time Display
     */
    private void operationAtTheaterMode(Screen screen, int time) {
        ScreenResult willContinue = ScreenResult.continueScreen;
        TheaterAreaState areaState = screen.getAreaState();
        this.DISPLAYER.displayAreaPlane(screen, areaState);

        do {
            char nextEvent = this.DISPENSER.waitEvent(time);

            if (this.isSeatButtonPressed(nextEvent)) {
                byte cols = (byte) (nextEvent & 0xFF);
                byte rows = (byte) ((nextEvent & 0xFF00) >> 8);
                willContinue = screen.seatButtonPressed(HARDWARE, rows, cols);
                this.DISPLAYER.reloadSeatButton(rows, cols, areaState);
                this.DISPLAYER.showTitle(screen);
            } else if ('1' != nextEvent) {
                willContinue = screen.optionButtonPressed(HARDWARE, nextEvent);
            }

        } while (ScreenResult.continueScreen == willContinue);
    }

    /**
     * Check if the input is from the seats or not
     *
     * @param optionPressed
     * @return if the seat button was pressed
     */
    private boolean isSeatButtonPressed(char optionPressed) {
        boolean isCancelButtonPressed = (optionPressed == '0');
        boolean isCreditCard = (optionPressed == '1');
        boolean isNotButtonPressed = isCancelButtonPressed || isCreditCard;

        boolean isButtonPressed = ('A' <= optionPressed && optionPressed <= 'F');
        boolean isSeatButtonPressed = !(isNotButtonPressed || isButtonPressed);

        return isSeatButtonPressed;
    }

    /**
     * Operate the Screen that uses the Message Mode
     *
     * @param screen
     * @param time Screen Time Display
     */
    private void operationAtMessageMode(Screen screen, int time) {
        this.DISPENSER.setMessageMode();
        this.DISPLAYER.displayMessageInformation(screen);
        ScreenResult nextAction = ScreenResult.exitScreen;

        do {
            char nextEvent = this.DISPENSER.waitEvent(time);
            boolean errorOccurred = screen.isAnErrorScreen();

            if ('1' == nextEvent && !errorOccurred) {
                this.DISPENSER.setOption(0, null);
                screen.creditCardDetected(HARDWARE);
            }

        } while (nextAction == ScreenResult.continueScreen);

    }

    /**
     * @return the TRANSLATOR manager
     */
    public TranslatorManager getTranslatorManager() {
        return this.TRANSLATOR;
    }

}
