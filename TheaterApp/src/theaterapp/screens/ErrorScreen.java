/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;
import urjc.UrjcBankServer;

/**
 * Screen that manage a payment error
 *
 * @author KandV008
 */
public class ErrorScreen extends Screen {

    private final UrjcBankServer BANK;

    /**
     * Initialize the screen with the BANK used to do the payment
     *
     * @param bank
     */
    public ErrorScreen(UrjcBankServer bank) {
        super(StringManager.getTITLE_ERROR_SCREEN(), 
                StringManager.getDESCRIPTION_ERROR_SCREEN());
        this.BANK = bank;
        this.setErrorScreen(true);
        ScreenMode mode = ScreenMode.messageMode;
        this.setMode(mode);
    }

    /**
     * Method to finalize the class at the end
     *
     * @param hardware
     * @return exitScreen
     */
    public ScreenResult end(DispenserHardware hardware) {

        boolean isErrorSolved = BANK.comunicationAvaiable();

        if (isErrorSolved) {
            return ScreenResult.exitScreen;
        }

        return ScreenResult.continueScreen;
    }

    /**
     * @return a list of strings with options
     */
    @Override
    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        return options;
    }

}
