package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controller {
    private final JFrame frame;

    public Controller(JFrame frame) {
        this.frame = frame;
        setupKeyBindings();
    }

    public void setupKeyBindings() {
         JPanel panel = (JPanel) frame.getContentPane();
         InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         ActionMap actionMap = panel.getActionMap();

         // CTRL-X exit game on restart go back to last unfinished level
         inputMap.put(KeyStroke.getKeyStroke("control X"), "exitWithoutSaving");
         actionMap.put("exitWithoutSaving", new AbstractAction() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.print("Closing without saving!");
                 // TODO save level
                 frame.dispose();
             }
         });

         // CTRL-S save game state then exit game on restart resume from saved game
        inputMap.put(KeyStroke.getKeyStroke("control S"), "exitAndSave");
        actionMap.put("exitAndSave", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Closing and saving!");
                // TODO add saving
                frame.dispose();
            }
        });

        // CTRL-R open load game menu
        inputMap.put(KeyStroke.getKeyStroke("control R"), "loadGame");
        actionMap.put("loadGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("loading game");
                // TODO add loading
            }
        });
        // CTRL-1 start new game at level 1
        inputMap.put(KeyStroke.getKeyStroke("control 1"), "switchLevel1");
        actionMap.put("switchLevel1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("switching to level 1");
                // TODO switch level

            }
        });
        // CTRL-2 start new game at level 2
        inputMap.put(KeyStroke.getKeyStroke("control 2"), "switchLevel2");
        actionMap.put("switchLevel2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("switching to level 2");
                // TODO switch level

            }
        });
        // SPACE pause game
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pauseGame");
        actionMap.put("pauseGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("pausing game");
                // TODO add game pausing
            }
        });
        // ESC resume game
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "resumeGame");
        actionMap.put("resumeGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("resuming game");
                // TODO resume game

            }
        });
        // UP, DOWN, LEFT, RIGHT Arrows move chap around maze
        // TODO add player controls
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUP");
        actionMap.put("moveUP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("moving chap up");
                Gui.game.getCharacters().get(0).move(0,-1,Gui.game);
                Gui.renderPanel.repaint();

            }
        });
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLEFT");
        actionMap.put("moveLEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("moving chap left");
                Gui.game.getCharacters().get(0).move(-1,0,Gui.game);
                Gui.renderPanel.repaint();

            }
        });
    }

}
