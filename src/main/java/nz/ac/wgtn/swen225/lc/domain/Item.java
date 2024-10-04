package nz.ac.wgtn.swen225.lc.domain;

public abstract class Item{
    protected Location location;
    protected boolean collected = false;

    public Item(Location location){
        this.location = location;
    }

    /**
     * Override constructor
     * @return
     */
    public Item(Location location, boolean Collected){
        this.location = location;
        this.collected = collected;
    }

    public boolean isKey() {return false;}

    public boolean isTreasure() { return false;}

    public boolean collected() {return collected;}

    public void collect() {collected = true;}
}
