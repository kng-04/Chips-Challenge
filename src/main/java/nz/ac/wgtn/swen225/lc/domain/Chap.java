package nz.ac.wgtn.swen225.lc.domain;


import nz.ac.wgtn.swen225.lc.app.LevelTimer;

public class Chap extends Characters{
    game.addTile(new FreeTile(this.x - dx, this.y - dy));
    //on creation set first move to true, after it is false
    boolean firstMove = true;
    
    //private final LevelTimer levelTimer;

    /*public Chap(LevelTimer levelTimer) {
        this.levelTimer = levelTimer;
    }*/

    public Chap(){ //Needed for Initialization with Json File

    }


    /*
    Move Chap in direction x and direction y on game map
    Conditional check to make sure chap is:
        moving inside the map
        onto a tile he can move into
    If first move replace chap tile on initialization with new Free Tile
     */
    public void move(int dx, int dy, Game game) {

        int newX = this.x + dx;
        int newY = this.y + dy;

        if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight()) {
            Tile tile = game.findTile(newX, newY);
            if (tile.canMoveInto()) {
                this.x = newX;
                this.y = newY;
                tile.interact(this, game); // Interact with the tile Chap moves onto
<<<<<<< src/main/java/nz/ac/wgtn/swen225/lc/domain/Chap.java

                // Start timer on the first move
                if (firstMove) {
                    //levelTimer.start();
                    firstMove = false;
=======
                if(firstMove){
                    game.addTile(new FreeTile(this.x - dx, this.y - dy));
                    this.firstMove = false;
>>>>>>> src/main/java/nz/ac/wgtn/swen225/lc/domain/Chap.java
                }

                // Add FreeTile only if firstMove is true
                game.addTile(new FreeTile(this.x - dx, this.y - dy));
            } else {
                System.out.println("Chap cannot move onto that tile!");
            }
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
