package nz.ac.wgtn.swen225.lc.domain;

public class TreasureTile extends Tile {
    public TreasureTile(){this.canMoveInto = true;}

    public boolean canMoveInto() {
     return this.canMoveInto; //Default always set to true
    }

    /*
    Collects treasureTile
    Replace treasureTile with new FreeTile
     */
    public void interact(Chap chap, Game game){
        game.collectTile(this);
        System.out.println("/nTreasures left: " + game.treasuresLeft());
        System.out.println("Chap picked up a treasure!");
    }
}
