package nz.ac.wgtn.swen225.lc.domain;

public class TreasureTile extends Tile {
    boolean canMoveInto;
    public TreasureTile(){
        this.canMoveInto = true;
    }

    public boolean canMoveInto() {
     return this.canMoveInto();
    }

    public void interact(Chap chap, Game game){
        game.collectTile(this);
        System.out.println("Chap picked up a treasure!");
    }
}
