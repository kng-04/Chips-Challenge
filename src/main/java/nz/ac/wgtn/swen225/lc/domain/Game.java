package nz.ac.wgtn.swen225.lc.domain;
import java.util.*;


public class Game {
    private Tiles[][] maze;
    private int totalTreasures;
    private int height, width, secondsLeft;
    private List<Characters> characters;
    private List<Tiles> inventory, tiles;
    private boolean mapComplete;

    public Game(int height, int width, int secondsLeft,  List<Characters> characters, List<Tiles> inventory, List<Tiles> tiles  ) {
        this.height = height;
        this.width = width;
        this.secondsLeft = secondsLeft;
        this.characters = characters; //get(0) = chap
        this.inventory = inventory;
        this.tiles = tiles;
        this.maze = initializeMaze(); // Create your maze layout
        this.totalTreasures = countTreasuresInMaze();
    }

    private Tiles[][] initializeMaze() {
        // Create a maze with various tiles, keys, and treasures
        Tiles[][] grid = new Tiles[width][height];
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
        if(this.totalTreasures){mapComplete=true;}
    }
    public int getTotalTreasures(){
        return totalTreasures;
    }

    public void startGame() {
        while (!mapComplete) {
            //
        }

        System.out.println("Congratulations! You've completed the level.");
    }


}
