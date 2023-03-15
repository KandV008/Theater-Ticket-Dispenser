/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.time.LocalDateTime;
import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 * Class that manage all the hardware of the ticket DISPENSER
 *
 * @author KandV008
 */
public class DispenserHardware {

    private final TheaterTicketDispenser DISPENSER;
    private Ticket ticket;
    private final TranslatorManager TRANSLATOR;

    /**
     * Initializa all the properties
     *
     * @param dispenser
     */
    public DispenserHardware(TheaterTicketDispenser dispenser,
            TranslatorManager translator) {
        this.DISPENSER = dispenser;
        this.TRANSLATOR = translator;
        this.createNewTicket();
    }

    /**
     * Create a new ticket
     */
    public void createNewTicket() {
        this.ticket = new Ticket();
        this.generateHeader();
    }

    /**
     * Print the ticket with all the information about the sell
     */
    public void printTicket() {
        this.generateFooter();
        List<String> newTicket = this.ticket.getTICKET();
        this.DISPENSER.print(newTicket);
    }

    /**
     * Retain the credit card
     *
     * @param doIt boolean that decides if is reatained the credit card
     */
    public void retainCreditCard(boolean doIt) {
        this.DISPENSER.retainCreditCard(doIt);
    }

    /**
     * Expel the credit card with a time limit to pick up
     *
     * @param time limit to pick up
     */
    public void expelCreditCard(int time) {
        this.DISPENSER.expelCreditCard(time);
    }

    /**
     * Generate a header for the ticket
     */
    private void generateHeader() {
        String theatre = StringManager.getTEXT_THEATRE();
        theatre = this.translateTicket(theatre);
        String namePlace = StringManager.getTHEATER_NAME();
        String header = theatre + " " + namePlace;

        this.ticket.addInformation(header);
        this.ticket.addSeparator();
    }

    /**
     * Generate a footer for the ticket
     */
    private void generateFooter() {
        this.ticket.addSeparator();
        LocalDateTime date = LocalDateTime.now();
        String moment = date.toString();
        this.ticket.addInformation(moment);
    }

    /**
     * Translate the fragment before to be added to the ticket
     *
     * @param msg to translate
     * @return msg translated
     */
    public String translateTicket(String msg) {
        msg = this.TRANSLATOR.translate(msg);

        return msg;
    }

    /**
     * Obtain the credit card number
     *
     * @return the credit card number
     */
    public long getCardNumber() {
        long creditCardNumber = this.DISPENSER.getCardNumber();
        return creditCardNumber;
    }

    /**
     * @return ticket
     */
    public Ticket getTicket() {
        return this.ticket;
    }
}
