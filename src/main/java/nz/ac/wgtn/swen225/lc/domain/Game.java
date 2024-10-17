package nz.ac.wgtn.swen225.lc.domain;
import nz.ac.wgtn.swen225.lc.app.Gui;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;


public class Game {
    private int width, height, secondsLeft;
    private List<Characters> characters;
    private List<Tile> inventory, tiles;

    private int currentLevel;
    private Gui gui;

    public Game(int width, int height, int secondsLeft, List<Characters> characters, List<Tile> inventory, List<Tile> tiles) {
        this.width = width;
        this.height = height;
        this.secondsLeft = secondsLeft;
        this.characters = characters; //get(0) = chap
        this.inventory = inventory;
        this.tiles = tiles;
        this.currentLevel = 1;
    }

    public Game(Gui gui){
        this.gui = gui;
        this.currentLevel = 1;
    }

    public void replaceTileWith(Tile tile){
        System.out.println(getTiles());
        Tile temp = null;
        for(Tile t : tiles){
            if(t.getX() == tile.getX() && tile.getY() == t.getY()){
                System.out.println("Tile found");
                temp = t;
            }
        }
        if(temp!=null){tiles.remove(temp); tiles.add(tile);}
    }

    public void addTile(Tile tile){
        this.tiles.add(tile);
    }

    public long treasuresLeft() {
        return this.tiles.stream().filter(t-> t instanceof TreasureTile).count();
    }

    //returns amount of treasures picked up by chap
    public long treasuresPickedUp(){
        return this.inventory.stream().filter(t-> t instanceof TreasureTile).count();
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

    public boolean useKey(Color color){
        Optional<Tile> opt = this.inventory.stream()
                .filter(t-> t instanceof KeyTile && ((KeyTile)t).getColor().equals(color))
                .findFirst();
        if(opt.isPresent()) {
            KeyTile keyTile = (KeyTile)opt.get();
            this.inventory.remove(keyTile);
            System.out.println(this.getInventory());
            return true;
        } else {
            return false;
        }
    }

    public void completeLevel() {
        currentLevel++;
        String nextLevelFile = "levels/level" + currentLevel + ".json";

        // Attempt to load the next level
        try {
            File levelFile = new File(nextLevelFile);
            if (levelFile.exists()) {
                if (gui != null) {
                    gui.createGame(nextLevelFile);
                }
            } else {
                JOptionPane.showMessageDialog(gui, "You have completed all levels!", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                //currentLevel--;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(gui, "Error loading next level. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            //currentLevel--;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
    public void setSecondsLeft(int seconds) { secondsLeft = seconds;}

    public List<Characters> getCharacters() {
        return characters;
    }

    public List<Tile> getInventory() {
        return inventory;
    }

    public List<Tile> getTiles() {
        return tiles;
    }


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
