/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store all the esential information about an specific theater area
 *
 * @author KandV008
 */
public class TheaterArea {

    private int rows;
    private int cols;
    private String name;
    private List<List<SeatState>> seats;
    private int price;

    /**
     * Initialize all the attributes using a text file
     *
     * @param fileName where is the matrix of seats
     * @param price of the theater area per seat
     * @param name of the theater area
     */
    public TheaterArea(String fileName, int price, String name) {
        this.price = price;
        this.name = name;
        this.cols = 0;
        this.seats = new ArrayList<>();

        String dirPath = FilePath.getPATH_DIR_AREA_THEATER_FILES();
        String pathFile = dirPath.concat(fileName);

        try {
            this.read(pathFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheaterArea.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo '" + fileName + ".txt' no accesible.");
        }

        this.rows = this.seats.size();
    }

    /**
     * Read the file with the seats
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    private void read(String fileName) throws FileNotFoundException {
        File theatherAreaFile = new File(fileName);
        Scanner scanner = new Scanner(theatherAreaFile);

        while (scanner.hasNextLine()) {
            String seatLine = scanner.nextLine();
            this.analizeSeatLine(seatLine);
        }
        
        scanner.close();
    }

    /**
     * Analize the current line to store the distribution of seats
     *
     * @param seatLine
     */
    private void analizeSeatLine(String seatLine) {
        List<SeatState> row = new ArrayList<>();
        SeatState currentSeat;
        int index = 0;

        for (; index < seatLine.length(); index++) {
            currentSeat = analizePosition(seatLine.charAt(index));
            row.add(currentSeat);
        }

        this.cols = Math.max(this.cols, index);
        this.seats.add(row);
    }

    /**
     * Analize the character to determine if it is a seat, or not.
     *
     * @param charAt
     * @return SeatState
     */
    private SeatState analizePosition(char charAt) {
        if ('*' == charAt) {
            return SeatState.free;
        }

        return null;
    }

    /**
     * @param row
     * @param col
     * @return the seat in that position
     */
    public SeatState getSeat(int row, int col) {
        boolean isOutOfBounds = this.seats.get(col).size() <= row;

        if (isOutOfBounds) {
            return null;
        }

        SeatState currentSeat;
        currentSeat = this.seats.get(col).get(row);

        return currentSeat;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }
}
