package nz.ac.wgtn.swen225.lc.domain;
import java.util.*;


public class Chap extends Characters{
    private Map<String, Boolean> keys;
    private int treasuresCollected;
    private boolean levelCompleted = false;
    private Game game;

    public Chap(int startX, int startY, Game game) {
        this.x = startX;
        this.y = startY;
        this.keys = new HashMap<>();
        this.game = game;
    }

    public void move(int dx, int dy, Tiles[][] maze) {
        int newX = this.x + dx;
        int newY = this.y + dy;

        if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length) {
            if (maze[newX][newY].canMoveInto()) {
                this.x = newX;
                this.y = newY;
                maze[this.x][this.y].interact(this, game); // Interact with the tile Chap moves onto
            } else {
                System.out.println("Chap cannot move onto that tile!");
            }
        }
    }

    public void collectKey(String color) {
        keys.put(color, true);
    }

    public boolean hasKey(String color) {
        return keys.getOrDefault(color, false);
    }

    public int getTreasuresCollected(){
        return this.treasuresCollected;
    }

    public void collectTreasure() {
        treasuresCollected++;
        game.decrementTotalTreasures();
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void completeLevel() {
        levelCompleted = true;
    }
}
