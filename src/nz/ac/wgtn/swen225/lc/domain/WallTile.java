package nz.ac.wgtn.swen225.lc.domain;

public class WallTile extends Tiles{

    public WallTile() {
        this.canMoveInto = false; // Default is locked
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    @Override
    public void interact(Chap chap, Game game) {
        throw new IllegalStateException("Chap cannot walk into a wall!");
    }
}
