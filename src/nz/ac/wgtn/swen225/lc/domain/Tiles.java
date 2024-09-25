package nz.ac.wgtn.swen225.lc.domain;

public abstract class Tiles {
    protected boolean canMoveInto; // Determines if Chap can move onto the tile

    public abstract boolean canMoveInto();

    public abstract void interact(Chap chap, Game game); // Handle interactions when Chap steps on the tile
}
