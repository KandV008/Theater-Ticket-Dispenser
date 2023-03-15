/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import urjc.UrjcBankServer;

/**
 * Screen that manage all the operative about the payment of the ticket
 *
 * @author KandV008
 */
public class PaymentScreen extends Screen {

    private UpdaterState updaterState;
    private UrjcBankServer bank;
    private int price;

    /**
     * Initialize the Screen with the price of the product that is selling
     *
     * @param amount
     * @param mode
     * @param manager
     * @param updaterState
     */
    public PaymentScreen(int amount, ScreenMode mode, DispenserManager manager,
            UpdaterState updaterState) {
        super(StringManager.getTITLE_PAYMENT_SCREEN(), mode, manager);
        this.price = amount;
        this.bank = new UrjcBankServer();
        this.updaterState = updaterState;
    }

    /**
     * Do all the preparations before the begin the screen
     *
     * @param hardware
     * @return ScreenResult depending of the comunication availibility
     */
    @Override
    public ScreenResult begin(DispenserHardware hardware) {
        boolean comunicationAvaiable = this.bank.comunicationAvaiable();

        if (comunicationAvaiable) {
            this.generateDescription(hardware);
            return ScreenResult.continueScreen;
        }

        return ScreenResult.exitScreen;
    }

    /**
     * Do all the operative when the Credit Card is detected
     *
     * @param hardware
     * @return ScreenResult.exitScreen
     */
    @Override
    public ScreenResult creditCardDetected(DispenserHardware hardware) {
        hardware.retainCreditCard(false);
        long numberCreditCard = hardware.getCardNumber();
        this.updaterState.updateState();
        Ticket ticket = hardware.getTicket();
        String priceString = this.getDescription();
        this.storeInformation(ticket, priceString);

        try {
            this.payTicket(numberCreditCard, hardware);

            return ScreenResult.exitScreen;
        } catch (CommunicationException ex) {
            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido realizar "
                    + "la operacion con precio de " + this.price);
            this.manageError();

            return ScreenResult.continueScreen;
        }
    }

    /**
     * @return the possible options
     */
    @Override
    public List<String> getOptions() {
        List<String> seatOption = new ArrayList<>();

        String cancel = StringManager.getTEXT_CANCEL();
        seatOption.add(cancel);

        return seatOption;
    }

    /**
     * Generate and traslated the description text
     *
     * @param hardware
     */
    private void generateDescription(DispenserHardware hardware) {
        String priceText = StringManager.getTEXT_PRICE();
        priceText = hardware.translateTicket(priceText);
        String simbolPrice = StringManager.getSYMBOL_EURO();
        String numericPrice = this.price + simbolPrice;

        String priceString = priceText.concat(":" + numericPrice);

        this.setDescription(priceString);
    }

    /**
     * Change to the Error Screen if an error had happened
     */
    private void manageError() {
        Screen errorScreen = new ErrorScreen(this.bank);
        DispenserManager manager = this.getDispenserManager();
        int time = 10;
        manager.showScreen(time, errorScreen);
    }

    /**
     * Do the payment using the credit card detected
     *
     * @param numberCreditCard
     * @param hardware
     * @throws CommunicationException
     */
    private void payTicket(long numberCreditCard, DispenserHardware hardware)
            throws CommunicationException {
        this.bank.doOperation(numberCreditCard, this.price);
        int time = 30;
        hardware.printTicket();
        hardware.expelCreditCard(time);
    }
}
