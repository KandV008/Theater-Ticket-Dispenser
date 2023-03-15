/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

/**
 * Store the TheaterState and the TheaterAreaState selected to update them
 *
 * @author KandV008
 */
public class UpdaterTheaterState extends UpdaterState {

    /**
     * @return the DATE_THEATER_STATE
     */
    public TheaterState getDATE_THEATER_STATE() {
        return DATE_THEATER_STATE;
    }

    private final TheaterState DATE_THEATER_STATE;
    private final TheaterAreaState AREA_SELECTED;

    /**
     * Initialize the updater with the theater state selected and the area
     *
     * @param dayTheaterState
     * @param areaSelected
     */
    public UpdaterTheaterState(TheaterState dayTheaterState,
            TheaterAreaState areaSelected) {
        this.DATE_THEATER_STATE = dayTheaterState;
        this.AREA_SELECTED = areaSelected;
    }

    /**
     * Update the files with the new information
     */
    @Override
    public void updateState() {
        this.getDATE_THEATER_STATE().updateTheaterState(this.getAREA_SELECTED());
    }

    /**
     * @return the AREA_SELECTED
     */
    public TheaterAreaState getAREA_SELECTED() {
        return AREA_SELECTED;
    }
}
