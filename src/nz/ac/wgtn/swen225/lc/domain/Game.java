package nz.ac.wgtn.swen225.lc.domain;
import java.util.*;


public class Game {
    private Chap chap;
    private Tiles[][] maze;
    private int totalTreasures;

    public Game() {
        this.maze = initializeMaze(); // Create your maze layout
        this.chap = new Chap(0, 0); // Starting position of Chap
        this.totalTreasures = countTreasuresInMaze();
    }

    private Tiles[][] initializeMaze() {
        // Create a 5x5 maze with various tiles, keys, and treasures
        Tiles[][] grid = new Tiles[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new FreeTile(); // Default to FreeTile
            }
        }
        grid[0][0] = new WallTile();
        grid[1][1] = new KeyTile("blue");
        grid[2][2] = new LockedTile("blue");
        grid[3][3] = new TreasureTile();
        grid[4][4] = new ExitTile();
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

    public void startGame() {
        while (!chap.isLevelCompleted()) {
            // Get user input to move Chap (implement logic to get input)
            // Chap moves and interacts with tiles
        }

        System.out.println("Congratulations! You've completed the level.");
    }


}
