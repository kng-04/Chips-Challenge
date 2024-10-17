package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.render.Render;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Gui extends JFrame{

    public static Game game;  // Holds the game data
    public static Render renderPanel;
    public SaveManager saveManager;
    private final Controller controller;

    private JPanel gameArea; // The panel where the game will render
    protected LevelTimer levelTimer;
    protected int currentLevel = 1; // TODO needs to be updated
    private JLabel timeLabel;

    private JPanel keyInventory;

    protected final JFileChooser fileChooser = new JFileChooser();

    boolean isHelpMenuOpen = false;

    Gui() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        saveManager = new SaveManager(this);
        controller = new Controller(this, saveManager); // Setup controller for keybindings

        // Make file chooser only show json files
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Json", "json"));
        fileChooser.setFileFilter(fileChooser.getChoosableFileFilters()[1]);

        fileChooser.setCurrentDirectory(new File("levels/saves")); // set to directory the program is run in

        loadMenu();
        levelTimer = new LevelTimer(60, timeLabel);
        levelTimer.stop();
        saveManager.readConfig();
        if(saveManager.fileToLoad != null){
            System.out.println("loading previous");
            createGame(saveManager.fileToLoad);
        }
        else {
            createGame("levels/level1.json");
        }

        setVisible(true);
    }

    protected void createGame(String jsonFileName) {
        try {
            game = Persistency.loadGame(jsonFileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load save file. Loading level 1 instead", "Error", JOptionPane.ERROR_MESSAGE);
            saveManager.writeConfig(""); // remove the invalid file name
            createGame("levels/level1.json");
        }
        levelTimer.reset(game.getSecondsLeft());
        // Holds the game images
        Map<String, BufferedImage> images;
        try {
            images = Persistency.loadImages();  // Load images
        } catch (IOException e) {
            throw new RuntimeException("Error loading the images", e);
        }

        // Create a new GamePanel and add it to the gameArea
        renderPanel = new Render(game, images);
        gameArea.removeAll();
        gameArea.add(renderPanel);
        gameArea.revalidate();
        gameArea.repaint();

        // Start playing background music
        renderPanel.playBackgroundMusic();

        //pauseGame();
    }

    private void loadMenu(){
        setTitle("Larry Croft's Adventures");
        // Main Screen
        gameArea = new JPanel(); // Game will render here
        var title = new JLabel("Game Area");
        gameArea.setBackground(Color.WHITE);
        gameArea.add(title);

        // Sidebar
        var sidebar = new JPanel(new GridLayout(4,1));
        var levelLabel = new JLabel("Level: 1", SwingConstants.CENTER);
        timeLabel = new JLabel("Time: 0", SwingConstants.CENTER);
        var scoreLabel = new JLabel("Chips Left: 0", SwingConstants.CENTER);

        sidebar.setBackground(Color.GRAY);
        sidebar.add(levelLabel);
        sidebar.add(timeLabel);
        sidebar.add(scoreLabel);

        // Key inventory
        keyInventory = new JPanel(new GridLayout(2, 4,-45,-75));
        keyInventory.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        keyInventory.setBackground(Color.GRAY);

        for (int i = 0; i < 8; i++) {
            // Load the FreeTile image directly when creating the icon
            BufferedImage freeTileImage = null;
            try {freeTileImage = ImageIO.read(new File("images/FreeTile.jpg"));
            } catch (IOException e) {e.printStackTrace();}

            Image scaledImage = freeTileImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            var tileSlot = new JLabel(new ImageIcon(scaledImage));
            tileSlot.setHorizontalAlignment(SwingConstants.CENTER);
            tileSlot.setPreferredSize(new Dimension(70, 70));
            keyInventory.add(tileSlot);
        }
        sidebar.add(keyInventory);

        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameArea, sidebar);
        splitPane.setResizeWeight(0.67);
        splitPane.setOneTouchExpandable(false);
        splitPane.setEnabled(false); // Disable user resizing
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
        var miPlay = new JMenuItem("Play");
        miQuit.addActionListener(e -> saveManager.exitWithoutSaving());
        miPause.addActionListener(e -> pauseGame());
        miPlay.addActionListener(e -> resumeGame());
        mGame.add(miPlay);
        mGame.add(miPause);
        mGame.add(miQuit);


        // Level Submenu
        var mLevel = new JMenu("Level");
        var miSave = new JMenuItem("Save");
        var miLoad = new JMenuItem("Load");
        var miRestart = new JMenuItem("Restart");
        miSave.addActionListener(e -> {
            saveManager.autoSave();
            JOptionPane.showMessageDialog(this, "Game was saved");
        });
        miLoad.addActionListener(e -> saveManager.loadSaveFilePicker());
        miRestart.addActionListener(e -> saveManager.resetLevel());
        mLevel.add(miSave);
        mLevel.add(miLoad);
        mLevel.add(miRestart);

        // Help Submenu
        var help = new JMenu("Help");
        var miHelp = new JMenuItem("Get Help");
        miHelp.addActionListener(e -> {
            if(!isHelpMenuOpen){
                createHelpMenu();
            }
        });
        help.add(miHelp);

        // Add menubar items
        menuBar.add(mGame);
        menuBar.add(mLevel);
        menuBar.add(help);
    }

    /**
     * Creates a new window containing the rules of the game
     */
    private void createHelpMenu(){
        isHelpMenuOpen = true;
        var helpScreen = new JFrame("Help Page");

        var helpPanel = new JPanel();
        var helpText = new JTextArea("""
                Goal:
                The goal of Larry Croft's Adventures is to collect all the chips and advance to the next level before the time runs out. To collect all the chips you must overcome various challenges such as finding keys to open locked doors.
                
                Controls:
                1. CTRL-X - Exit the game: The current game state will be lost. The next time the game is started, it will resume from the last unfinished level.
                2. CTRL-S - Exit the game and save: Saves the game state. The game will resume the next time the application is started.
                3. CTRL-R - Resume a saved game: Opens a file selector to choose a saved game to be loaded.
                4. CTRL-1 - Start a new game at level 1.
                5. CTRL-2 - Start a new game at level 2.
                6. SPACE - Pause the game and display a “Game is Paused” dialog.
                7. ESC - Close the “Game is Paused” dialog and resume the game.
                8. UP, DOWN, LEFT, RIGHT ARROWS - Move Chap within the maze.
                """);
        helpText.setLineWrap(true);
        helpText.setRows(30);
        helpText.setColumns(50);
        helpText.setWrapStyleWord(true);
        helpText.setEditable(false);
        var closeHelp = new JButton("Close");
        closeHelp.addActionListener(e -> {
            isHelpMenuOpen = false;
            helpScreen.dispose();
        });
        var helpScroll = new JScrollPane(helpText);


        helpPanel.setLayout(new BorderLayout());
        helpScreen.add(helpPanel);
        helpPanel.add(helpScroll, BorderLayout.CENTER);
        helpScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        helpScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Text wraps
        helpScroll.setBorder(new EmptyBorder(10,10,10,10));
        helpPanel.add(closeHelp, BorderLayout.SOUTH);
        helpScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //helpScreen.setPreferredSize(new Dimension(800,400));
        helpScreen.setVisible(true);

        helpScreen.pack();
    }

    private boolean isPaused = false;

    protected void pauseGame(){
        if (isPaused) {return;}
        isPaused = true;

        controller.disableUserInput();
        renderPanel.stopBackgroundMusic();
        renderPanel.pauseRender();
        levelTimer.stop();

    }
    protected void resumeGame(){
        if(!isPaused){return;}
        isPaused = false;

        controller.enableUserInput();
        renderPanel.playBackgroundMusic();
        renderPanel.unpauseRender();
        levelTimer.start();
    }
}