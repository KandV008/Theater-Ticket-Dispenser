/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

/**
 * Manage all the software of the theater
 *
 * @author KandV008
 */
public class TheaterManager {

    /**
     * Run the software
     */
    public void run() {
        DispenserManager dispenserManager = new DispenserManager();
        ScreenMode mode = ScreenMode.optionsMode;
        Theater myTheater = new Theater();
        WellcomeScreen wellcomeScreen
                = new WellcomeScreen(myTheater, mode, dispenserManager);
        int time = 30;
        dispenserManager.showScreen(time, wellcomeScreen);
    }

}
