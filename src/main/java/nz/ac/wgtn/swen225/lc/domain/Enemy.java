package nz.ac.wgtn.swen225.lc.domain;

public class Enemy extends Characters {

    /*
    Empty constructor used to initialize enemy using json file in persistance
     */
    public Enemy() {
    }

    /*
    Enemy with x and y parameters to be placed onto map
     */

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void move(int dx, int dy, Game game){
        //implement logic for enemy movement
    }

}
