package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.Characters;
import nz.ac.wgtn.swen225.lc.domain.CoordinateEntity;
import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.domain.Tile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gui extends JFrame{

    public static Game game;  // Holds the game data
    private Map<String, BufferedImage> images; // Holds the game images
    private JPanel gameArea; // The panel where the game will render
    public static GamePanel gamePanel;

    boolean isHelpMenuOpen = false;

    Gui() {
        assert SwingUtilities.isEventDispatchThread();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadMenu();
        startGame();

        new Controller(this); // Setup controller for keybindings
        setVisible(true);
    }

    public class GamePanel extends JPanel {
        private final Game game;
        private final Map<String, BufferedImage> images;
        private final List<CoordinateEntity> entities = new ArrayList<>();
        private final int IMG_SIZE = 32;  // 32x32 tile size

        public GamePanel(Game game, Map<String, BufferedImage> images) {
            this.game = game;
            this.images = images;

            // Collect all entities (tiles and characters)
            entities.addAll(game.getTiles());
            entities.addAll(game.getCharacters());  // Add characters after tiles, so they are drawn on top
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);  // Clears the panel before painting

            // Draw background
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Render each entity on the board
            for (CoordinateEntity entity : entities) {
                int x = entity.getX() * IMG_SIZE;
                int y = entity.getY() * IMG_SIZE;
                String name = entity.getClass().getSimpleName();

                // If entity is a Tile and has a color, append the color to the name
                if (entity instanceof Tile && ((Tile) entity).getColor() != null) {
                    nz.ac.wgtn.swen225.lc.domain.Color color = ((Tile) entity).getColor();
                    name += "-" + color.toString();
                }

                // Draw the corresponding image
                BufferedImage image = images.get(name);
                if (image != null) {
                    g.drawImage(image, x, y, IMG_SIZE, IMG_SIZE, this);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(IMG_SIZE * game.getWidth(), IMG_SIZE * game.getHeight());
        }
    }

    private void startGame() {
        try {
            game = Persistency.loadGame("levels/level1.json");  // Load game data
        } catch (IOException e) {
            throw new RuntimeException("Error loading the game", e);
        }

        try {
            images = Persistency.loadImages();  // Load images
        } catch (IOException e) {
            throw new RuntimeException("Error loading the images", e);
        }

        // Create a new GamePanel and add it to the gameArea
        gamePanel = new GamePanel(game, images);
        gameArea.removeAll();  // Clear any previous components from the game area
        gameArea.add(gamePanel);
        gameArea.revalidate();
        gameArea.repaint();
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

        new LevelTimer(60, timeLabel); // TODO needs to be started once player first moves? and on new level

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
        var miPlay = new JMenuItem("Play");
        miQuit.addActionListener(e -> {
            // TODO save what level player was on but not level state same as a CTRL-X
            this.dispose();
        });
        miPause.addActionListener(null); // TODO add pause
        mGame.add(miPlay);
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

    private void pauseGame(){
        // Stop timer
        // disable input
        // stop game logic
        // show game is paused
    }
}
