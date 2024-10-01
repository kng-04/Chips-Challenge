package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Gui extends JFrame{
    Gui() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadMenu();

        new Controller(this); // Setup controller for keybindings

        setVisible(true);
    }

    private void loadMenu(){

        // Main Screen
        var gameArea = new JPanel();
        var title = new JLabel("Game Area");
        gameArea.setBackground(Color.WHITE);
        gameArea.add(title);

        // Sidebar
        var sidebar = new JPanel(new GridLayout(4,1));
        var levelLabel = new JLabel("Level: 0", SwingConstants.CENTER);
        var timeLabel = new JLabel("Time: 0", SwingConstants.CENTER);
        var scoreLabel = new JLabel("Chips: 0", SwingConstants.CENTER);

        sidebar.setBackground(Color.GRAY);
        sidebar.add(levelLabel);
        sidebar.add(timeLabel);
        sidebar.add(scoreLabel);

        // Key inventory
        var keyInventory = new JPanel(new GridLayout(2,4,20,20));
        keyInventory.setBorder(new EmptyBorder(10,10,10,10));
        keyInventory.setBackground(Color.GRAY);
        for(int i=0; i<8; i++){
            var emptySlot = new JPanel();
            emptySlot.setBackground(Color.red);
            emptySlot.setPreferredSize(new Dimension(1,1));
            keyInventory.add(emptySlot, SwingConstants.CENTER);
        }
        sidebar.add(keyInventory);

        new LevelTimer(60, timeLabel); // TODO needs to be started once player first moves?

        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameArea, sidebar);
        splitPane.setResizeWeight(0.67);
        splitPane.setOneTouchExpandable(false);
        splitPane.setEnabled(false); // So user can not resize
        add(splitPane);

        createMenuBar();

        setPreferredSize(new Dimension(1200,800));
        pack();
    }

    // Creates all elements relating to the menubar
    private void createMenuBar(){
        // MenuBar
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Game submenu
        var mGame = new JMenu("Game");
        var miQuit= new JMenuItem("Quit");
        var miPause = new JMenuItem("Pause");
        miQuit.addActionListener(e -> this.dispose());
        miPause.addActionListener(null); // TODO add pause
        mGame.add(miPause);
        mGame.add(miQuit);



        // Level Submenu
        var mLevel = new JMenu("Level");
        var miSave = new JMenuItem("Save");
        var miLoad = new JMenuItem("Load");
        var miRestart = new JMenuItem("Restart");
        mLevel.add(miSave);
        mLevel.add(miLoad);
        mLevel.add(miRestart);

        // Help Submenu
        var help = new JMenu("Help");
        var miHelp = new JMenuItem("Get Help");
        help.add(miHelp);

        // Add menubar items
        menuBar.add(mGame);
        menuBar.add(mLevel);
        menuBar.add(help);
    }
}
