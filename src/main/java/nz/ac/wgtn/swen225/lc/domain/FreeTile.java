package nz.ac.wgtn.swen225.lc.domain;

public class FreeTile extends Tile {

    public FreeTile() {
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
