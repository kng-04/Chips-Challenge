package nz.ac.wgtn.swen225.lc.domain;
import nz.ac.wgtn.swen225.lc.app.Gui;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import java.io.IOException;
import java.util.*;


public class Game {
    private int width, height, secondsLeft;
    private List<Characters> characters;
    private List<Tile> inventory, tiles;

    private int currentLevel;

    /**
     * Constructs a Game instance with specified parameters.
     *
     * @param width       the width of the game board
     * @param height      the height of the game board
     * @param secondsLeft the initial time left for the game
     * @param characters  the list of characters in the game
     * @param inventory   the initial inventory of the player
     * @param tiles       the list of tiles present in the game
     */
    public Game(int width, int height, int secondsLeft, List<Characters> characters, List<Tile> inventory, List<Tile> tiles) {
        this.width = width;
        this.height = height;
        this.secondsLeft = secondsLeft;
        this.characters = characters; //get(0) = chap
        this.inventory = inventory;
        this.tiles = tiles;
        this.currentLevel = 1;
    }

    /**
     * Default constructor initializing the game with the first level.
     */
    public Game(){
        this.currentLevel = 1;
    }

    //================================Tiles Operations========================================

    public void replaceTileWith(Tile tile){
        Tile temp = null;
        for(Tile t : tiles){
            if(t.getX() == tile.getX() && tile.getY() == t.getY()){
                temp = t;
            }
        }
        if(temp!=null){tiles.remove(temp); tiles.add(tile);}
    }

    public void addTile(Tile tile){
        this.tiles.add(tile);
    }

    public Tile findTile(int x, int y) {
        return this.tiles.stream().filter(t-> t.getX() == x && t.getY() == y).findFirst().orElseThrow();
    }

        public Tile findTile(Class<?> tileClass) {
            return this.tiles.stream()
                    .filter(t -> tileClass.isInstance(t))  // Check if the tile is an instance of the provided class
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No tile found of type: " + tileClass.getSimpleName()));
        }

    // KeyTile and TreasureTile can be collected
    public void collectTile(Tile tile) {
        if (tile instanceof KeyTile || tile instanceof TreasureTile) {
            // move from tiles to inventory, and create a new FreeTile at original place
            this.tiles.remove(tile);
            this.inventory.add(tile);
            Tile newFreeTile = new FreeTile();
            newFreeTile.setX(tile.x);
            newFreeTile.setY(tile.y);
            this.tiles.add(newFreeTile);
            if(tile instanceof TreasureTile && treasuresLeft() == 0){
                replaceTileWith(new FreeTile(this.findTile(ExitLockTile.class).getX(), this.findTile(ExitLockTile.class).getY()));
            }
        } else {
            throw new IllegalArgumentException(tile.getClass().getSimpleName() + " cannot be collected");
        }
    }

    /**
     * Uses a key of a specified color from the inventory.
     *
     * @param color the color of the key to use
     * @return true if the key was successfully used, false otherwise
     */
    public boolean useKey(Color color){
        Optional<Tile> opt = this.inventory.stream()
                .filter(t-> t instanceof KeyTile && ((KeyTile)t).getColor().equals(color))
                .findFirst();
        if(opt.isPresent()) {
            KeyTile keyTile = (KeyTile)opt.get();
            this.inventory.remove(keyTile);
            return true;
        } else {
            return false;
        }
    }

    //================================Treasures Operations========================================
    /**
     * Counts the number of TreasureTiles left in the game.
     *
     * @return the count of TreasureTiles still present
     */
    public long treasuresLeft() {
        return this.tiles.stream().filter(t-> t instanceof TreasureTile).count();
    }

    /**
     * Returns the number of TreasureTiles collected by the player.
     *
     * @return the count of TreasureTiles in the inventory
     */
    public long treasuresPickedUp(){
        return this.inventory.stream().filter(t-> t instanceof TreasureTile).count();
    }

    //================================Level Operations========================================

    /**
     * Completes the current level and loads the next level.
     * Updates the game state with the new level data.
     */
    public void completeLevel(Gui currentGui) {
        if (currentLevel == 2) {
            currentGui.close();
            // Show the game over screen after completing level 2
            SwingUtilities.invokeLater(() -> {
                Gui newGui = new Gui();
                newGui.showGameOverScreen();
            });
            return;
        }
        currentLevel++;

        String nextLevelFile = "levels/level" + currentLevel + ".json";
        try {
            Game nextGame = Persistency.loadGame(nextLevelFile);  // Load the next level game data
            // Update game state with the new level data
            this.width = nextGame.width;
            this.height = nextGame.height;
            this.secondsLeft = nextGame.secondsLeft;
            this.characters = nextGame.characters;
            this.inventory = nextGame.inventory;
            this.tiles = nextGame.tiles;
        } catch (IOException e) {
            System.out.println("Unable to load next level (Game): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Unable to load level (Game)" + currentLevel, "Error", JOptionPane.ERROR_MESSAGE);
            // Reset the level to avoid proceeding if there's an error
            currentLevel--;
        }
    }

    //================================Getters Operations========================================

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public List<KeyTile> getKeyInventory(){
        return this.inventory.stream()
                .filter(t-> t instanceof KeyTile)
                .map(t -> (KeyTile) t)
                .toList();
    }

    public int getSecondsLeft() { return secondsLeft; }
    public void setSecondsLeft(int seconds) { secondsLeft = seconds;}

    public int getCurrentLevel() { return currentLevel; }
    public List<Characters> getCharacters() { return characters; }
    public List<Tile> getInventory() { return inventory; }

    public List<Tile> getTiles() { return tiles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return width == game.width && height == game.height && secondsLeft == game.secondsLeft && Objects.equals(characters, game.characters) && Objects.equals(inventory, game.inventory) && Objects.equals(tiles, game.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, secondsLeft, characters, inventory, tiles);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Game.class.getSimpleName() + "[", "]")
                .add("width=" + width)
                .add("height=" + height)
                .add("secondsLeft=" + secondsLeft)
                .add("characters=" + characters)
                .add("inventory=" + inventory)
                .add("tiles=" + tiles)
                .toString();
    }
}
