/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Screen that manage all the operative about the date selection
 *
 * @author KandV008
 */
public class DateSelectionScreen extends Screen {

    private final Map<LocalDateTime, TheaterState> SCHEDULE;
    private Screen areasScreen;
    private final int MAX_POSSIBLE_DAYS = 5;
    private final Theater THEATER;

    /**
     * Initialize the Screen with the default values
     *
     * @param theater
     * @param mode
     * @param manager
     */
    public DateSelectionScreen(Theater theater,
            ScreenMode mode, DispenserManager manager) {
        super(StringManager.getTITLE_DATE_SELECTION_SCREEN(), mode, manager);
        this.THEATER = theater;
        this.SCHEDULE = new TreeMap<>();
        this.setMode(mode);
        this.setDispenserManager(manager);
    }

    /**
     * Do all the preparative of the screen before launch it
     *
     * @param hardware
     * @return ScreenResult.continue
     */
    @Override
    public ScreenResult begin(DispenserHardware hardware) {
        this.loadStateFiles();
        return ScreenResult.continueScreen;
    }

    /**
     * Get the List of Dates Available
     *
     * @return
     */
    private List<LocalDateTime> getDatesFromToday() {
        List<LocalDateTime> dates = new ArrayList<>();

        for (LocalDateTime date : this.SCHEDULE.keySet()) {
            dates.add(date);
        }

        return dates;
    }

    /**
     * Load the file with the date THEATER state
     */
    private void loadStateFiles() {
        LocalDateTime dayOfReference = LocalDateTime.now();
        int counterDays = 0;

        while ((counterDays < MAX_POSSIBLE_DAYS)) {
            LocalDateTime nextDay = dayOfReference;

            if (DayOfWeek.MONDAY != nextDay.getDayOfWeek()) {
                counterDays++;
                TheaterState todayTheaterState
                        = new TheaterState(this.THEATER, nextDay);
                this.SCHEDULE.put(nextDay, todayTheaterState);
            }

            dayOfReference = dayOfReference.plusDays(1);
        }
    }

    /**
     * @return the options that will be showed at the screen
     */
    @Override
    public List<String> getOptions() {
        List<LocalDateTime> dates = this.getDatesFromToday();
        List<String> stringDates = new ArrayList<>();

        for (LocalDateTime date : dates) {
            String currentDayString = this.convertDateToString(date);
            stringDates.add(currentDayString);
        }

        String cancel = StringManager.getTEXT_CANCEL();
        stringDates.add(cancel);

        return stringDates;
    }

    /**
     * Convert the LocalDateInformation into a more readable String
     *
     * @param date to convert
     * @return the date converted
     */
    private String convertDateToString(LocalDateTime date) {
        String dateText;
        DayOfWeek day = date.getDayOfWeek();
        int numericDay = date.getDayOfMonth();
        int numericMonth = date.getMonthValue();
        dateText = day.toString() + ", " + numericDay + "-" + numericMonth;

        return dateText;
    }

    /**
     * Do the correspondient operation depending of the button pressed
     *
     * @param hardware
     * @param option
     * @return ScreenResult.exitScreen
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware hardware, char option) {
        List<LocalDateTime> dates = this.getDatesFromToday();
        ScreenMode mode = this.getMode();
        DispenserManager manager = this.getDispenserManager();
        LocalDateTime dateSelected = this.getDateSelected(dates, option);
        int time = 30;

        if (dateSelected == null) {
            return ScreenResult.exitScreen;
        }

        String text = this.generateTicketText(hardware, dateSelected);
        Ticket ticket = hardware.getTicket();
        this.storeInformation(ticket, text);
        TheaterState stateOfDay = this.SCHEDULE.get(dateSelected);
        this.areasScreen = new AreaSelectionScreen(stateOfDay, mode, manager);
        manager.showScreen(time, areasScreen);

        return ScreenResult.exitScreen;
    }

    /**
     * @param dates available
     * @param option choosed
     * @return the date selected
     */
    private LocalDateTime getDateSelected(List<LocalDateTime> dates, char option) {
        if ('A' == option) {
            LocalDateTime dateSelected = dates.get(0);
            return dateSelected;
        }

        if ('B' == option) {
            LocalDateTime dateSelected = dates.get(1);
            return dateSelected;
        }

        if ('C' == option) {
            LocalDateTime dateSelected = dates.get(2);
            return dateSelected;
        }

        if ('D' == option) {
            LocalDateTime dateSelected = dates.get(3);
            return dateSelected;
        }

        if ('E' == option) {
            LocalDateTime dateSelected = dates.get(4);
            return dateSelected;
        }

        return null;
    }

    /**
     * Generate the information about the date selected to store it in the
     * ticket
     *
     * @param hardware
     * @param dateSelected
     * @return String with the ticket information
     */
    private String generateTicketText(DispenserHardware hardware,
            LocalDateTime dateSelected) {
        String date = this.convertDateToString(dateSelected);
        String[] split = date.split(", ");
        String dayOfWeek = split[0];
        String dayNumeric = split[1];

        dayOfWeek = hardware.translateTicket(dayOfWeek);

        date = dayOfWeek + ", " + dayNumeric;
        return date;
    }
}
