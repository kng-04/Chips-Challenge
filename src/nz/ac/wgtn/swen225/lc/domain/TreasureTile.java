package nz.ac.wgtn.swen225.lc.domain;

public class TreasureTile extends Tiles{
    boolean canMoveInto;
    TreasureTile(){
        this.canMoveInto = true;
    }

    public boolean canMoveInto() {
     return this.canMoveInto();
    }

    public void interact(Chap chap, Game game){
        chap.collectTreasure(); // Increment Chap's treasure count
        System.out.println("Chap picked up a treasure!");
    }
}
