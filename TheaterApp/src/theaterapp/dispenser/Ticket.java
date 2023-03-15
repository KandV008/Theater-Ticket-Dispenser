/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.LinkedList;
import java.util.List;

/**
 * Store a list of string as ticlet
 *
 * @author KandV008
 */
public class Ticket {

    private final List<String> TICKET = new LinkedList<>();
    private final String SEPARATOR;

    /**
     * Initialize the TICKET with an especific separator
     */
    public Ticket() {
        this.SEPARATOR = "======================";
    }

    /**
     * Add an String to the TICKET
     *
     * @param information to add to the TICKET
     */
    public void addInformation(String information) {
        this.TICKET.add("        " + information);
    }

    /**
     * Add a SEPARATOR in the TICKET
     */
    public void addSeparator() {
        addInformation(this.SEPARATOR);
    }

    /**
     * Return the TICKET with all the information
     *
     * @return list of String
     */
    public List<String> getTICKET() {
        return this.TICKET;
    }
}
