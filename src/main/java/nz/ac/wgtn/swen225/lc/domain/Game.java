package nz.ac.wgtn.swen225.lc.domain;
import java.util.*;


public class Game {
    private int totalTreasures;
    private int height, width, secondsLeft;
    private List<Characters> characters;
    private List<Tile> inventory, tiles;

    public Game(int height, int width, int secondsLeft, List<Characters> characters, List<Tile> inventory, List<Tile> tiles  ) {
        this.height = height;
        this.width = width;
        this.secondsLeft = secondsLeft;
        this.characters = characters; //get(0) = chap
        this.inventory = inventory;
        this.tiles = tiles;
        this.totalTreasures = countTreasuresInMaze();
    }

    public Game(){

    }



    private int countTreasuresInMaze() {
        return (int) tiles.stream()
                .filter(tile -> tile instanceof TreasureTile) // Filter tiles that are TreasureTile
                .count(); // Count them
    }

    public void replaceTileWith(Tile tile){

    }

    public void decrementTotalTreasures(){
        this.totalTreasures--;
        if(this.totalTreasures){mapComplete=true;}
    }
    public int getTotalTreasures(){
        return totalTreasures;
    }

    public void startGame() {
        Chap tempChap = (Chap)characters.get(0);
        while (tempChap.getTreasuresCollected() < totalTreasures) {
            //
        }


        System.out.println("Congratulations! You've completed the level.");
    }


}
