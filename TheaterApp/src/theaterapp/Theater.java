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
 * Store all the relevant information about a theater
 *
 * @author KandV008
 */
public class Theater {

    private List<TheaterArea> theaterTheaterAreas;
    private String theaterImage;
    private String theaterName;
    private int holidaysMultiplicator;

    /**
     * Initialize all the information reading a file text
     */
    public Theater() {
        String pathFile = FilePath.getPATH_THEATER_FILE();
        theaterTheaterAreas = new ArrayList<>();

        try {
            this.read(pathFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo 'theater.txt' no accesible.");
        }
    }

    /**
     * Read a text file with the information about the theater
     *
     * @param theatreDir Path to the text file
     * @throws FileNotFoundException
     */
    private void read(String theatreDir) throws FileNotFoundException {
        File theaterFile = new File(theatreDir);
        Scanner scanner = new Scanner(theaterFile);

        while (scanner.hasNextLine()) {
            String lineFile = scanner.nextLine();
            this.analizeLineInformation(lineFile);
        }

        scanner.close();
    }

    /**
     * Analize the file to store the information
     *
     * @param lineFile Line to analize
     */
    private void analizeLineInformation(String lineFile) {
        String[] stringSplitted = lineFile.split(":");

        if ("TheaterName".equals(stringSplitted[0])) {
            this.theaterName = stringSplitted[1];
            return;
        }

        if ("TheaterPlaneImageFile".equals(stringSplitted[0])) {
            this.theaterImage = stringSplitted[1];
            return;
        }

        if ("Festivo".equals(stringSplitted[0])) {
            int valueMultiplicator = Integer.parseInt(stringSplitted[1]);
            this.holidaysMultiplicator = valueMultiplicator;
            return;
        }

        this.loadTheaterAreas(stringSplitted[1]);
    }

    /**
     * Create a new TheaterArea with the information at the String
     *
     * @param theaterAreaLine String with the information about the area
     */
    private void loadTheaterAreas(String theaterAreaLine) {
        String[] stringSplitted = theaterAreaLine.split(";");

        String theaterAreaFilePath = stringSplitted[2];
        String moneySimbol = StringManager.getSYMBOL_EURO();
        String numericValueOfPrice = stringSplitted[1].replace(moneySimbol, "");
        int priceTheaterArea = Integer.parseInt(numericValueOfPrice);
        String nameTheaterArea = stringSplitted[0];
        TheaterArea newTheaterArea;
        newTheaterArea
                = new TheaterArea(theaterAreaFilePath,
                        priceTheaterArea, nameTheaterArea);
        this.theaterTheaterAreas.add(newTheaterArea);
    }

    /**
     * @return number of areas from Theater
     */
    public int getNumAreas() {
        return this.theaterTheaterAreas.size();
    }

    /**
     * @param pos of the theater
     * @return theater area
     */
    public TheaterArea getArea(int pos) {
        return this.theaterTheaterAreas.get(pos);
    }

    /**
     * @return the holidaysMultiplicator
     */
    public int getHolidaysMultiplicator() {
        return holidaysMultiplicator;
    }

}
