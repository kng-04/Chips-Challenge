package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controller {
    private final Gui gui;
    private final InputMap inputMap;
    private final ActionMap actionMap;
    private final SaveManager saveManager;

    public Controller(Gui gui, SaveManager saveManager) {
        this.gui = gui;
        this.saveManager = saveManager;
        JPanel panel = (JPanel) gui.getContentPane();
        inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = panel.getActionMap();
        setupInterfaceControls();
        setupPlayerControls();
    }

    // Player controls get disabled when the game is paused
    public void setupPlayerControls(){
        // UP, DOWN, LEFT, RIGHT Arrows move chap around maze
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUP");
        actionMap.put("moveUP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui.game.getCharacters().get(0).move(0,-1,Gui.game);
                Gui.renderPanel.repaint();

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLEFT");
        actionMap.put("moveLEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui.game.getCharacters().get(0).move(-1,0,Gui.game);
                Gui.renderPanel.repaint();

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDOWN");
        actionMap.put("moveDOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui.game.getCharacters().get(0).move(0,1,Gui.game);
                Gui.renderPanel.repaint();

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRIGHT");
        actionMap.put("moveRIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui.game.getCharacters().get(0).move(1,0,Gui.game);
                Gui.renderPanel.repaint();
            }
        });
    }

    // Interface controls are always available
    public void setupInterfaceControls() {
        // CTRL-X exit game on restart go back to last unfinished level
        inputMap.put(KeyStroke.getKeyStroke("control X"), "exitWithoutSaving");
        actionMap.put("exitWithoutSaving", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveManager.exitWithoutSaving();
            }
        });

        // CTRL-S save game state then exit game on restart resume from saved game
        inputMap.put(KeyStroke.getKeyStroke("control S"), "exitAndSave");
        actionMap.put("exitAndSave", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveManager.exitAndSave();
            }
        });

        // CTRL-R open load game menu
        inputMap.put(KeyStroke.getKeyStroke("control R"), "loadSaveFilePicker");
        actionMap.put("loadSaveFilePicker", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveManager.loadSaveFilePicker();
            }
        });
        // CTRL-1 start new game at level 1
        inputMap.put(KeyStroke.getKeyStroke("control 1"), "switchLevel1");
        actionMap.put("switchLevel1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.createGame("levels/level1.json");
                gui.setCurrentLevel(1);

            }
        });
        // CTRL-2 start new game at level 2
        inputMap.put(KeyStroke.getKeyStroke("control 2"), "switchLevel2");
        actionMap.put("switchLevel2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.createGame("levels/level2.json");
                gui.setCurrentLevel(2);
            }
        });
        // SPACE pause game
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pauseGame");
        actionMap.put("pauseGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.pauseGame(false);
            }
        });
        // ESC resume game
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "resumeGame");
        actionMap.put("resumeGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.resumeGame();

            }
        });
    }
    protected void disableUserInput() {
        inputMap.clear(); // remove all binds
        setupInterfaceControls(); // bring back interface controls but leave game controls disabled
    }
    protected void enableUserInput() {
        setupPlayerControls(); // Re-enable player movement
    }
}

