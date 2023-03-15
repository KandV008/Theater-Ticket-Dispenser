/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Store the state of a Theater Area
 *
 * @author KandV008
 */
public class TheaterAreaState implements Serializable {

    private List<List<SeatState>> seatsState;
    private String name;
    private int cols;
    private int rows;
    private int price;

    /**
     * Initialize the with the information of the current TheaterArea
     *
     * @param currentTheaterArea
     */
    public TheaterAreaState(TheaterArea currentTheaterArea) {
        this.seatsState = new ArrayList<>();
        this.copyTheaterAreaAttributes(currentTheaterArea);
    }

    /**
     * Copy all the information of the current TheaterArea
     *
     * @param currentTheaterArea
     */
    private void copyTheaterAreaAttributes(TheaterArea currentTheaterArea) {
        this.name = currentTheaterArea.getName();
        this.cols = currentTheaterArea.getCols();
        this.rows = currentTheaterArea.getRows();
        this.price = currentTheaterArea.getPrice();
        this.fillMatrixSeatState(currentTheaterArea);
    }

    /**
     * Clone the matrix with the distribution of seats from the theater area
     *
     * @param theaterArea
     */
    private void fillMatrixSeatState(TheaterArea theaterArea) {
        SeatState currentState;
        List<SeatState> auxList;

        for (int i = 0; i < this.rows; i++) {
            auxList = new ArrayList<>();
            this.seatsState.add(auxList);

            for (int j = 0; j < this.cols; j++) {
                currentState = theaterArea.getSeat(j, i);
                this.seatsState.get(i).add(currentState);
            }
        }
    }

    /**
     * @param row
     * @param col
     * @return the seatState of the seat at the row and col
     */
    public SeatState getSeat(int row, int col) {
        row--;
        col--;

        if (col > this.cols) {
            return null;
        }

        SeatState seat = this.seatsState.get(row).get(col);

        return seat;
    }

    /**
     * Set the seatStaet of the seat at the row and col
     *
     * @param row
     * @param col
     * @param state
     */
    public void setSeat(int row, int col, SeatState state) {
        col--;
        row--;
        this.seatsState.get(row).set(col, state);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the price of the theater area
     */
    int getPrice() {
        return this.price;
    }

}
