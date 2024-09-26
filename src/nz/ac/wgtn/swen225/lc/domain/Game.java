package nz.ac.wgtn.swen225.lc.domain;
import java.util.*;


public class Game {
    private Chap chap;
    private Tiles[][] maze;
    private int totalTreasures;

    public Game() {
        this.maze = initializeMaze(); // Create your maze layout
        this.chap = new Chap(0, 0, this); // Starting position of Chap
        this.totalTreasures = countTreasuresInMaze();
    }

    private Tiles[][] initializeMaze() {
        // Create a 9x9 maze with various tiles, keys, and treasures
        Tiles[][] grid = new Tiles[9][9];
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new FreeTile(); // Default to FreeTile
            }
        }

        return grid;
    }

    private int countTreasuresInMaze() {
        int count = 0;
        for (Tiles[] row : maze) {
            for (Tiles tile : row) {
                if (tile instanceof TreasureTile) {
                    count++;
                }
            }
        }
        return count;
    }

    public void replaceTileWith(){
        
    }

    public void decrementTotalTreasures(){
        this.totalTreasures--;
    }
    public int getTotalTreasures(){
        return totalTreasures;
    }

    public void startGame() {
        while (!chap.isLevelCompleted()) {
            //
        }

        System.out.println("Congratulations! You've completed the level.");
    }


}
