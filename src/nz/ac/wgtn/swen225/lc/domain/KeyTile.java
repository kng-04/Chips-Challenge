package nz.ac.wgtn.swen225.lc.domain;


public class KeyTile extends Tiles{

    private String color;

    public KeyTile(String color) {
        this.color = color;
        this.canMoveInto = true;
    }
    @Override
    public boolean canMoveInto(){
        return this.canMoveInto();
    }
    @Override
    public void interact(Chap chap, Game game){
        chap.collectKey(this.color); // Adds key to Chap's inventory
        System.out.println("Chap picked up a " + color + " key.");
    }
}
