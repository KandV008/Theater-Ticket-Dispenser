/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store the state of the theater in a specific day
 *
 * @author KandV008
 */
public class TheaterState implements Serializable {

    private final LocalDateTime DATE;
    private List<TheaterAreaState> theaterAreas;
    private int multiplicator;

    /**
     * Initialize the attributes with the theater and the day selected
     *
     * @param theater
     * @param date selected
     */
    public TheaterState(Theater theater, LocalDateTime date) {
        this.DATE = date;
        this.theaterAreas = new ArrayList<>();
        boolean isHolidays = this.checkHolidays();

        if (isHolidays) {
            this.multiplicator = theater.getHolidaysMultiplicator();
        } else {
            this.multiplicator = 1;
        }

        this.loadData(theater);
    }

    /**
     * Check if the day is a holiday
     *
     * @return
     */
    private boolean checkHolidays() {
        DayOfWeek today = this.DATE.getDayOfWeek();
        boolean isFriday = (DayOfWeek.FRIDAY == today);
        boolean isSaturday = (DayOfWeek.SATURDAY == today);
        boolean isSunday = (DayOfWeek.SUNDAY == today);

        return isFriday || isSaturday || isSunday;
    }

    /**
     * Load the information at the file, if it doesn't exit, it creates a new
     * file with the information at the theater
     *
     * @param theater
     */
    private void loadData(Theater theater) {
        String dirName = createNameDir();
        String dirPath = FilePath.getPATH_DIR_DAY_SALES_FILES();
        String pathFile = dirPath + dirName;

        File theaterStateFile = new File(pathFile);
        boolean existDateFile = theaterStateFile.exists();

        if (!existDateFile) {
            this.createDateDir(theater, theaterStateFile);
            return;
        }

        try {
            this.readDateFile(theaterStateFile);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TheaterState.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo '" + dirName + ".bin' no accesible.");
        }
    }

    /**
     * Create the name of the file where will be store the theater state
     *
     * @return String with the name of the file
     */
    private String createNameDir() {
        int dayOfMonth = this.DATE.getDayOfMonth();
        Month month = this.DATE.getMonth();
        int year = this.DATE.getYear();
        String dirName = dayOfMonth + "-" + month + "-" + year;

        return dirName;
    }

    /**
     * Create the file with where will be stored the TheaterState
     *
     * @param theater
     * @param theaterStateFile
     */
    private void createDateDir(Theater theater, File theaterStateFile) {
        try {
            theaterStateFile.createNewFile();
            this.inicialiteDateFile(theater, theaterStateFile);
        } catch (IOException ex) {
            Logger.getLogger(TheaterState.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido crear el nuevo archivo 'date.bin'.");
        }
    }

    /**
     * Initialize the theaterState with the information of the theater
     *
     * @param theater
     * @param theaterStateFile
     * @throws IOException
     */
    private void inicialiteDateFile(Theater theater, File theaterStateFile) throws IOException {
        int numAreas = theater.getNumAreas();

        for (int pos = 0; pos < numAreas; pos++) {
            TheaterArea currentTheaterArea = theater.getArea(pos);
            TheaterAreaState newTheaterAreaState
                    = new TheaterAreaState(currentTheaterArea);
            this.theaterAreas.add(newTheaterAreaState);
        }

        this.writeInformationToFile(theaterStateFile, this.theaterAreas);
    }

    /**
     * Read the file with the TheaterState information
     *
     * @param theaterStateFile to read
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readDateFile(File theaterStateFile)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(theaterStateFile);
        ObjectInputStream input = new ObjectInputStream(fileInput);
        this.theaterAreas = (List<TheaterAreaState>) input.readObject();
        input.close();
    }

    /**
     * Write the information in the correspondient file
     *
     * @param theaterStateFile to store it
     * @param listTheaterAreasState to be stored
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void writeInformationToFile(File theaterStateFile,
            List<TheaterAreaState> listTheaterAreasState)
            throws FileNotFoundException, IOException {
        FileOutputStream fileOutput = new FileOutputStream(theaterStateFile);
        ObjectOutputStream output = new ObjectOutputStream(fileOutput);
        output.writeObject(listTheaterAreasState);
        output.close();
    }

    /**
     * Update the file that containts the TheaterState
     *
     * @param newAreaState
     */
    public void updateTheaterState(TheaterAreaState newAreaState) {
        this.updateTheaterAreas(newAreaState);

        String fileName = this.createNameDir();
        String pathFile = FilePath.getPATH_DIR_DAY_SALES_FILES() + fileName;
        File fileDir = new File(pathFile);

        try {
            this.writeInformationToFile(fileDir, this.theaterAreas);
        } catch (IOException ex) {
            Logger.getLogger(TheaterState.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido actualizar el archivo " + fileName + ".bin.");
        }
    }

    /**
     * Update the TheaterAreaState selected with the new information
     *
     * @param newAreaState
     */
    private void updateTheaterAreas(TheaterAreaState newAreaState) {
        boolean stateUpdated = false;
        int index = 0;

        while ((index < this.theaterAreas.size()) && !stateUpdated) {
            stateUpdated = this.checkAreas(this.theaterAreas.get(index),
                    newAreaState);
            index++;
        }
    }

    /**
     * Check if the theater areas are the same using the name of the area
     *
     * @param currentAreaState
     * @param newAreaState
     * @return true if area equal, false if not
     */
    private boolean checkAreas(TheaterAreaState currentAreaState,
            TheaterAreaState newAreaState) {
        String nameCurrentArea = currentAreaState.getName();
        String nameNewArea = newAreaState.getName();

        if (nameCurrentArea.equals(nameNewArea)) {
            currentAreaState = newAreaState;
            return true;
        }

        return false;
    }

    /**
     * @return the DATE of the theater state
     */
    public LocalDateTime getDATE() {
        return this.DATE;
    }

    /**
     * @return the num of areas
     */
    public int getNumAreas() {
        return this.theaterAreas.size();
    }

    /**
     * @param pos of the theater area state
     * @return the theater area state
     */
    public TheaterAreaState getArea(int pos) {
        return this.theaterAreas.get(pos);
    }

    /**
     * @return the multiplicator
     */
    public int getMultiplicator() {
        return multiplicator;
    }

}
