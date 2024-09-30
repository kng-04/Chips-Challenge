package nz.ac.wgtn.swen225.lc.domain;

public abstract class Characters extends CoordinateEntity{

    public abstract void move(int dx, int dy, Tile[][] maze);
}
