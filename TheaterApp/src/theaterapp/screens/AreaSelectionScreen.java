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
public class AreaSelectionScreen extends Screen {

    private Screen seatsScreen;
    private final TheaterState THEATER_STATE;

    /**
     * Initilize the screen with the state of the theater at the dat choosed
     *
     * @param stateOfDay TheaterState
     * @param mode of the Screen
     * @param manager
     */
    public AreaSelectionScreen(TheaterState stateOfDay, ScreenMode mode,
            DispenserManager manager) {
        super(StringManager.getTITLE_AREA_SELECTION_SCREEN(), mode, manager);
        this.THEATER_STATE = stateOfDay;
        String imageScreen = FilePath.getPATH_PLANE_IMAGE_FILE();
        this.setImage(imageScreen);
    }

    /**
     * @return the options that will be showed at the screen
     */
    @Override
    public List<String> getOptions() {
        int numAreas = THEATER_STATE.getNumAreas();
        List<String> areaOptions = new ArrayList<>();
        char areaReference = 'A';
        String moneySymbol = StringManager.getSYMBOL_EURO();
        int multiplicator = this.THEATER_STATE.getMultiplicator();

        for (int index = 0; index < numAreas; index++) {
            TheaterAreaState currentAreaState = THEATER_STATE.getArea(index);
            String nameArea = currentAreaState.getName();
            int priceArea = currentAreaState.getPrice();
            int finalPrice = priceArea * multiplicator;

            String newOption = areaReference + ", " + nameArea
                    + " (" + finalPrice + moneySymbol + ")";
            areaOptions.add(newOption);
            areaReference++;
        }

        String cancel = StringManager.getTEXT_CANCEL();
        areaOptions.add(cancel);

        return areaOptions;
    }

    /**
     * Do the correspondient operation depending of the button pressed
     *
     * @param hardware
     * @param option pressed
     * @return ScreenResult
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware hardware, char option) {
        ScreenMode mode = ScreenMode.theatherMode;
        DispenserManager manager = this.getDispenserManager();
        TheaterAreaState areaSelected = this.getAreaState(option);
        int time = 30;

        if (areaSelected == null) {
            return ScreenResult.exitScreen;
        }

        String text = this.generateTicketText(hardware, areaSelected);
        Ticket ticket = hardware.getTicket();
        this.storeInformation(ticket, text);
        UpdaterTheaterState updaterTheaterState
                = new UpdaterTheaterState(this.THEATER_STATE, areaSelected);
        this.seatsScreen
                = new SeatSelectionScreen(updaterTheaterState, mode, manager);
        manager.showScreen(time, seatsScreen);

        return ScreenResult.exitScreen;
    }

    /**
     * Obtain the area selected
     *
     * @param option pressed
     * @return TheaterAreaState Selected
     */
    private TheaterAreaState getAreaState(char option) {
        TheaterAreaState areaSelected;

        if ('A' == option) {
            areaSelected = this.THEATER_STATE.getArea(0);
            return areaSelected;
        }

        if ('B' == option) {
            areaSelected = this.THEATER_STATE.getArea(1);
            return areaSelected;
        }

        if ('C' == option) {
            areaSelected = this.THEATER_STATE.getArea(2);
            return areaSelected;
        }

        if ('D' == option) {
            areaSelected = this.THEATER_STATE.getArea(3);
            return areaSelected;
        }

        if ('E' == option) {
            areaSelected = this.THEATER_STATE.getArea(4);
            return areaSelected;
        }

        return null;
    }

    /**
     * Produce the information that will be stored in the ticket
     *
     * @param hardware
     * @param areaSelected
     * @return the name of the area selected
     */
    private String generateTicketText(DispenserHardware hardware, TheaterAreaState areaSelected) {
        String nameArea = areaSelected.getName();
        nameArea = hardware.translateTicket(nameArea);

        return nameArea;
    }
}
