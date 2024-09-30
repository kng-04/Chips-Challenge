package nz.ac.wgtn.swen225.lc.domain;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Game {
    int width;
    int height;
    int secondsLeft;
    List<Characters> characters;
    List<Tile> inventory;
    // Chap and other characters that can move
    // static tiles that cannot move
    List<Tile> tiles;

    public Game() {
    }

    public Game(int width, int height, int secondsLeft, List<Characters> characters, List<Tile> inventory, List<Tile> tiles) {
        this.width = width;
        this.height = height;
        this.secondsLeft = secondsLeft;
        this.characters = characters;
        this.inventory = inventory;
        this.tiles = tiles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }

    public List<Tile> getInventory() {
        return inventory;
    }

    public void setInventory(List<Tile> inventory) {
        this.inventory = inventory;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
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
