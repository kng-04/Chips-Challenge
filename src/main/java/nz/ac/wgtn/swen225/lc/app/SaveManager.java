package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SaveManager {
    private final Gui gui;
    public String fileToLoad; // When the game is closed store the save file automatically and load on relaunch

    SaveManager(Gui gui){
        this.gui = gui;
    }

    // saves using date and time instead of file picker
    public void autoSave(){
        gui.pauseGame();
        LocalDateTime time = LocalDateTime.now();
        String fileName = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        fileName = "save_" + fileName + ".json";
        try {
            Persistency.saveGame(Gui.game, "levels/saves/"+fileName);
            fileToLoad = fileName;
            //writeConfig();
        } catch (IOException e) {
            throw new RuntimeException("Error saving the game",e);
        }
    }

    protected void writeConfig(String fileName) {
        File configFile = new File("config.txt");
        try {
            // Create the file if it doesn't exist
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            // Use try-with-resources to ensure the writer is closed automatically
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile, false))) {
                if (fileName != null) {
                    writer.write(fileName);
                } else {
                    writer.write("");  // Write an empty string if previousSave is null
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to config file", e);
        }
    }
    protected void readConfig() {
        File configFile = new File("config.txt");
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Unable to generate config file",e);
        }
        try {
            Scanner sc = new Scanner(configFile);
            if(sc.hasNextLine()){
                fileToLoad = sc.nextLine();
            }
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Unable to find config file",e);
        }
    }

    public void exitAndSave(){
        autoSave();
        writeConfig("levels/saves/"+fileToLoad);
        System.exit(0);
    }
    public void exitWithoutSaving(){
        writeConfig("levels/level"+gui.currentLevel+".json"); // Loads the level the player quit on
        System.exit(0);
    }
    public void loadSaveFilePicker() {
        gui.pauseGame();
        int returnVal = gui.fileChooser.showOpenDialog(gui);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = gui.fileChooser.getSelectedFile().getAbsolutePath();
            gui.createGame(fileName);
            gui.resumeGame();
            gui.pauseGame();

        } else {
            JOptionPane.showMessageDialog(gui, "Loading Canceled");
        }
    }
}
