/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package theaterapp;

/**
 * Project from the assignature Object Oriented Programming The objective is
 * emulate a Ticket Dispenser of a theather
 *
 * @author KandV008
 */
public class TheaterApp {

    /**
     * Starts the program
     *
     * @param args
     */
    public static void main(String[] args) {
        TheaterManager automaticTicketDispenser = new TheaterManager();
        automaticTicketDispenser.run();
    }
}
