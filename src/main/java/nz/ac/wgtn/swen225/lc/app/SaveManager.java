package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class SaveManager {
    private final Gui gui;
    public String fileToLoad; // When the game is closed store the save file automatically and load on relaunch
    private final File saveDir;

    SaveManager(Gui gui){
        this.gui = gui;
        saveDir = new File("levels"+File.separator+"saves");
        saveDir.mkdirs();
    }

    // saves using date and time instead of file picker
    public void autoSave(){

        gui.pauseGame(false);
        Gui.game.setSecondsLeft(gui.levelTimer.getRemainingSeconds());
        LocalDateTime time = LocalDateTime.now();
        String fileName = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        fileName = "save_" + fileName + ".json";
        try {
            Persistency.saveGame(Gui.game, saveDir+File.separator+fileName);
            fileToLoad = fileName;
            //writeConfig();
        } catch (IOException e) {
            throw new RuntimeException("Error saving the game",e);
        }
    }

    protected void writeConfig(String fileName) {
        File configFile = new File(saveDir,"previousSave.txt");
        try {
            // Create the file if it doesn't exist
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            // Use try-with-resources to ensure the writer is closed automatically
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile, false))) {
                // Write an empty string if previousSave is null
                writer.write(Objects.requireNonNullElse(fileName, ""));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to previousSave file", e);
        }
    }
    protected void readConfig() {
        File configFile = new File(saveDir,"previousSave.txt");
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Unable to generate previousSave file",e);
        }
        try {
            Scanner sc = new Scanner(configFile);
            if(sc.hasNextLine()){
                fileToLoad = sc.nextLine();
            }
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Unable to find previousSave file",e);
        }
    }

    public void exitAndSave(){
        autoSave();
        writeConfig(saveDir+File.separator+fileToLoad);
        System.exit(0);
    }
    public void exitWithoutSaving(){
        writeConfig("levels/level"+gui.currentLevel+".json"); // Loads the level the player quit on
        System.exit(0);
    }
    public void loadSaveFilePicker() {
        gui.pauseGame(false);
        int returnVal = gui.fileChooser.showOpenDialog(gui);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = gui.fileChooser.getSelectedFile().getAbsolutePath();
            gui.createGame(fileName);
            gui.resumeGame();
            gui.pauseGame(true);

        } else {
            JOptionPane.showMessageDialog(gui, "Loading Canceled");
        }
    }
    public void resetLevel(){
        gui.levelTimer.stop();
        gui.createGame("levels/level"+gui.currentLevel+".json");
        gui.resumeGame();
        gui.pauseGame(true);
    }
}
