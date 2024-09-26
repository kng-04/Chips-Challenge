package nz.ac.wgtn.swen225.lc.domain;

public abstract class Characters {
    protected int x, y;

    public abstract void move(int dx, int dy, Tiles[][] maze);
}
