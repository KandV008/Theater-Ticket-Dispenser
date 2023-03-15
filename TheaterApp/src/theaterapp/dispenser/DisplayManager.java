/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 * Class that manage all the relative to display information in the Screen
 *
 * @author KandV008
 */
public class DisplayManager {

    private final TheaterTicketDispenser DISPENSER;
    private final int NUM_OPTIONS = 6;
    private final TranslatorManager TRANSLATOR;

    /**
     * Initialize the object with the same TheaterTicketDispenser and
     * TransaltorManager than the DispenserManager
     *
     * @param dispenser
     * @param translator
     */
    public DisplayManager(TheaterTicketDispenser dispenser,
            TranslatorManager translator) {
        this.DISPENSER = dispenser;
        this.TRANSLATOR = translator;
    }

    /**
     * Show all the option available at the screen
     *
     * @param screen
     */
    private void showOptions(Screen screen) {
        List<String> showOptionList = screen.getOptions();
        int optionNumber = 0;

        for (String showOption : showOptionList) {
            showOption = this.analizeOptionText(showOption);
            DISPENSER.setOption(optionNumber, showOption);
            optionNumber++;
        }
    }

    /**
     * Translate the option text with the active language
     *
     * @param textOption
     * @return the string translated
     */
    private String analizeOptionText(String textOption) {
        String[] split = textOption.split(", ");

        String part1 = split[0];
        part1 = this.TRANSLATOR.translate(part1);

        if (split.length == 1) {
            return part1;
        }

        String formatedOption;

        String part2 = split[1];

        part2 = this.TRANSLATOR.translate(part2);

        formatedOption = part1.concat(", " + part2);
        return formatedOption;

    }

    /**
     * Show all the seats available of a theater area
     *
     * @param areaState
     */
    private void showSeats(TheaterAreaState areaState) {
        int rows = areaState.getRows();
        int cols = areaState.getCols();
        this.DISPENSER.setTheaterMode(rows, cols);
    }

    /**
     * Show the title of the screen
     *
     * @param screen
     */
    public void showTitle(Screen screen) {
        String screenTitle = screen.getTitle();
        screenTitle = this.TRANSLATOR.translate(screenTitle);
        DISPENSER.setTitle(screenTitle);
    }

    /**
     * Show the description of the screen
     *
     * @param screen
     */
    private void showDescription(Screen screen) {
        String screenDescription = screen.getDescription();
        screenDescription = this.TRANSLATOR.translate(screenDescription);
        DISPENSER.setDescription(screenDescription);
    }

    /**
     * Show the image of the screen
     *
     * @param screen
     */
    private void showImage(Screen screen) {
        String screenImage = screen.getImage();
        DISPENSER.setImage(screenImage);
    }

    /**
     * Display all the information about the screen that uses the Menu Mode
     *
     * @param screen
     */
    public void displayMenuInformation(Screen screen) {
        this.DISPENSER.setMenuMode();
        this.showTitle(screen);
        this.showDescription(screen);
        this.showImage(screen);
        this.showOptions(screen);
    }

    /**
     * Display all the information about the screen that uses the Theater Mode
     *
     * @param screen
     * @param areaState Theater Area Selected
     */
    public void displayAreaPlane(Screen screen, TheaterAreaState areaState) {
        this.showSeats(areaState);
        this.showTitle(screen);
        this.showDescription(screen);
        this.drawArea(areaState);
        this.showOptions(screen);
    }

    /**
     * Print the distribution of seat and their availability of the theater area
     *
     * @param areaState Theater Area Selected
     */
    private void drawArea(TheaterAreaState areaState) {
        int numCols = areaState.getCols();
        int numRows = areaState.getRows();

        for (int row = 1; row <= numRows; row++) {

            for (int col = 1; col <= numCols; col++) {
                reloadSeatButton(row, col, areaState);
            }

        }
    }

    /**
     * Reload the button pressed to mark o desmark the button
     *
     * @param row
     * @param col
     * @param areaState Theater Area Selected
     */
    public void reloadSeatButton(int row, int col,
            TheaterAreaState areaState) {
        SeatState seat = areaState.getSeat(row, col);
        int state = this.analizeSeat(seat);
        this.DISPENSER.markSeat(row, col, state);
    }

    /**
     * Analize the seat state to determine what will be the next state of the
     * seat
     *
     * @param seat pressed
     * @return the next state for the seat
     */
    private int analizeSeat(SeatState seat) {
        if (SeatState.free == seat) {
            return 2;
        }

        if (SeatState.occupied == seat) {
            return 1;
        }

        return 0;
    }

    /**
     * Displat all the information about the screen that uses the Message Mode
     *
     * @param screen
     */
    public void displayMessageInformation(Screen screen) {
        this.showTitle(screen);
        this.showDescription(screen);
        this.showOptions(screen);
    }

    /**
     * Clean the options of the screen to avoid buttons without input
     */
    public void cleanOptions() {
        for (int index = 0; index < NUM_OPTIONS; index++) {
            this.DISPENSER.setOption(index, null);
        }
    }
}
