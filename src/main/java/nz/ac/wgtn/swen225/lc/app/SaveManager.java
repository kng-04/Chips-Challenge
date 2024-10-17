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
    protected void autoSave(){
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
    /*protected void saveGame() {
        gui.pauseGame();
        int returnVal = gui.fileChooser.showSaveDialog(gui);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = gui.fileChooser.getSelectedFile().toString();
            if (!fileName.endsWith(".json")){
                fileName+=".json";
            }
            try {
                Persistency.saveGame(Gui.game, fileName);
                previousSave = fileName;
            } catch (IOException e) {
                throw new RuntimeException("Error saving the game",e);
            }
        } else {
            JOptionPane.showMessageDialog(gui, "Saving Canceled");
        }
    }*/
    protected void loadGame() {
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

    // Tries to load the latest save stored in the save folder
    /*protected void loadLatestSave(){
        File saveDir = new File("levels/saves");
        if(!saveDir.exists() || !saveDir.isDirectory()) {
            // Makes save folder
            if(saveDir.mkdirs()) {
                System.out.println("making save dir");
            } else {
                JOptionPane.showMessageDialog(gui, "Failed to create a save folder at levels/saves try to create it manually. Game will now exit");
                throw new RuntimeException("Failed to create a save folder at levels/saves try to create it manually. Game will now exit");
            }
            return;
        }
        File[] saveFiles = saveDir.listFiles((dir, name) -> name.endsWith(".json"));
        if(saveFiles == null || saveFiles.length == 0){
            // No saves found
            System.out.println("no saves found");
            return;
        }
        File latestSave = Arrays.stream(saveFiles)
                .max(Comparator.comparing(File::lastModified))
                .orElse(null);
        if(latestSave.isFile()) {
            fileToLoad = latestSave.getAbsolutePath();
            System.out.println("previous save");
        }
    }*/

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
                    writer.write("levels/saves/"+fileName);
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
}
