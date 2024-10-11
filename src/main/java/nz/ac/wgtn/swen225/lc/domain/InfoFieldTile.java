package nz.ac.wgtn.swen225.lc.domain;

public class InfoFieldTile extends Tile {
    /*
    Empty InfoFieldTile contructor
     */
    public InfoFieldTile(){this.canMoveInto = true;}
    @Override
    public boolean canMoveInto() {
        return this.canMoveInto; //Default always set to true
    }

    /*
    Prints text with hint of current level
     */
    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("Hint: Collect the Treasure Chips to unlock the door."); //temporary - display UI.TextBox with hint
    }
}
