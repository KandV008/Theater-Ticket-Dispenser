/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Screen that manage all the operative about the seat selection of Theater Area
 * 
 * @author KandV008
 */
public class SeatSelectionScreen extends Screen {
    private UpdaterTheaterState updaterTheaterState;
    private TheaterAreaState selectArea;
    private Screen paymentScreen;
    private Set<String> seatPressedSet;
    private final int MAX_SEATS_PRESSED = 4;
    
    /**
     * Initialize the screen with the theater area selected
     * @param updaterTheaterState
     * @param mode
     * @param manager 
     */
    public SeatSelectionScreen(UpdaterTheaterState updaterTheaterState, 
            ScreenMode mode, DispenserManager manager) {
        super(StringManager.getTITLE_SEAT_SELECTION_SCREEN(), mode, manager);
        this.seatPressedSet = new HashSet<>();
        this.updaterTheaterState = updaterTheaterState;
        this.selectArea = updaterTheaterState.getAREA_SELECTED();
    }
    
    /**
     * Update the title when a seat button is pressed, to had a feedback about
     * the seat selected and the maximum number of buttons that can be pressed
     */
    private void updateTitle() {
        String titleScreen = this.seatPressedSet.size() + "/" 
                + this.MAX_SEATS_PRESSED + " seats selected";
        this.setTitle(titleScreen);    
    }
    
    /**
     * Do all the operative of the option selected
     * @param hardware
     * @param option
     * @return ScreenResult
     */
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware hardware, 
            char option){
        boolean anySeatButtonPressed = (this.seatPressedSet.isEmpty());
        boolean cancelPressed = ('B' != option);
        
        if(cancelPressed){
            return ScreenResult.exitScreen;  
        }
        
        if(anySeatButtonPressed){
            return ScreenResult.continueScreen;
        }

        int amount = this.computePrice();
        DispenserManager manager = this.getDispenserManager();
        this.generateResume(hardware);
        ScreenMode mode = ScreenMode.messageMode;
        this.paymentScreen 
                = new PaymentScreen(amount, mode, manager, this.updaterTheaterState);
        int time = 30;
        manager.showScreen(time, paymentScreen);
        
        return ScreenResult.exitScreen;
    }
    
    /**
     * Change the state of the seat button pressed
     * @param hardware
     * @param row
     * @param col
     * @return ScreenResult.continueScreen
     */
    @Override
    public ScreenResult seatButtonPressed(DispenserHardware hardware, 
            int row, int col){
        
        boolean isPossibleAddMore 
                = !(this.seatPressedSet.size() >= MAX_SEATS_PRESSED);
        SeatState currentSeatState = this.selectArea.getSeat(row, col);
        
        String value = "Row: " + row + ", " + "Col: " + col;
        
        if(SeatState.free == currentSeatState && isPossibleAddMore){
            this.selectArea.setSeat(row, col, SeatState.occupied);
            
            this.seatPressedSet.add(value);
            this.updateTitle();
            return ScreenResult.continueScreen;
        }
        
        boolean wasOccuped = this.seatPressedSet.remove(value);
        
        if(wasOccuped){
            this.selectArea.setSeat(row, col, SeatState.free);
        }
        
        this.updateTitle();
        
        return ScreenResult.continueScreen;
    }
    
    /**
     * Generate the price to pay of all the seat selected
     * @return int with the price seatSelected
     */
    private int computePrice(){
        TheaterState theaterState = this.updaterTheaterState.getDATE_THEATER_STATE();
        int multiplicator = theaterState.getMultiplicator();
        int priceArea = this.selectArea.getPrice();
        int price = priceArea*multiplicator;
        int numberSeatPressed = this.seatPressedSet.size();
        int amount = numberSeatPressed*price;
        return amount;
    }
    
    /**
     * @return TheaterArea 
     */
    @Override
    public TheaterAreaState getAreaState(){
        return this.selectArea;
    }

    /**
     * Generate the resume to be added in the ticket with the seats 
     * selected
     * @param hardware 
     */
    private void generateResume(DispenserHardware hardware) { 
        Ticket ticket = hardware.getTicket();
        
        for(String seatSelected : this.seatPressedSet){
            seatSelected = this.generateTicketText(hardware, seatSelected);
            this.storeInformation(ticket, seatSelected);
        }
    }
    
    /**
     * Translate the information of the seat selected
     * @param hardware
     * @param seatInformation
     * @return 
     */
    private String generateTicketText(DispenserHardware hardware, 
            String seatInformation) {
        String[] splitPosition = seatInformation.split(", ");
        
        String[] splitRowText = splitPosition[0].split(": ");
        String row = splitRowText[0];
        row = hardware.translateTicket(row);
        String rowNumber = splitRowText[1];
        
        String[] splitColText = splitPosition[1].split(": ");
        String col = splitColText[0];
        col = hardware.translateTicket(col);
        String colNumber = splitColText[1];
        
        row = row.concat(": " + rowNumber);
        col = col.concat(": " + colNumber);
        
        seatInformation = row.concat(", " + col);
        return seatInformation;
    }
    
    /**
     * @return the options that will be show at the screen
     */
    @Override
    public List<String> getOptions() {
        List<String> seatOption = new ArrayList<>();
        
        String cancel = StringManager.getTEXT_CANCEL();
        seatOption.add(cancel);
        
        String cont = StringManager.getTEXT_CONTINUE();
        seatOption.add(cont);
        
        return seatOption;
    }

}
