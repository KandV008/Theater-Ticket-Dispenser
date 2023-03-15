/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Store all the information about the current play
 *
 * @author KandV008
 */
public class Play {

    private String title;
    private String image;
    private String description;

    /**
     * Initialize all the attributes by reading a text file
     *
     * @throws FileNotFoundException
     */
    public Play() {
        String pathFile = FilePath.getPATH_PLAY_FILE();

        try {
            this.read(pathFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Archivo 'play.txt' no accesible.");
        }
    }

    /**
     * Read the file that is refered in the path file
     *
     * @param pathFile String with the path to the file
     * @throws FileNotFoundException
     */
    private void read(String pathFile) throws FileNotFoundException {
        File playFile = new File(pathFile);
        Scanner scanner = new Scanner(playFile);

        while (scanner.hasNextLine()) {
            String playLine = scanner.nextLine();
            analizePlayLine(playLine);
        }
        
        scanner.close();
    }

    /**
     * Analize the current line to store it
     *
     * @param playLine String with the information to analize
     */
    private void analizePlayLine(String playLine) {
        String[] stringSplitted = playLine.split(":");

        if ("play_name".equals(stringSplitted[0])) {
            this.title = stringSplitted[1];
            return;
        }

        if ("play_poster".equals(stringSplitted[0])) {
            String pathFile = FilePath.getPATH_DIR_CONFIG_FILES();
            this.image = pathFile.concat(stringSplitted[1]);
            return;
        }

        this.description = stringSplitted[1];
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
