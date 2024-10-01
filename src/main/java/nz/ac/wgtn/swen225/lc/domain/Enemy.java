package nz.ac.wgtn.swen225.lc.domain;

public class Enemy extends Characters {
    private int x, y;

    public Enemy() {
    }

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void move(int dx, int dy, Tile[][] maze){
        //implement logic for enemy movement
    }

}
