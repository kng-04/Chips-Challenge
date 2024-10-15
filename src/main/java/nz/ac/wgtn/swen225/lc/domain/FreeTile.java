package nz.ac.wgtn.swen225.lc.domain;

public class FreeTile extends Tile {

    /*
    Empty ExitTile constructor
     */
    public FreeTile() {
        this.canMoveInto = true;
    }

    /*
    Constructor with parameters to allow for ingame tile replacement
     */
    public FreeTile(int x, int y){
        this.x = x;
        this.y = y;
        this.canMoveInto = true;
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    @Override
    public void interact(Chap chap, Game game) {
        // No special interaction for FreeTile
    }
}
