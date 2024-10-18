package nz.ac.wgtn.swen225.lc.domain;


import nz.ac.wgtn.swen225.lc.app.LevelTimer;

import java.util.ArrayList;
import java.util.List;

public class Chap extends Characters{
    boolean firstMove = true;
    //private final LevelTimer levelTimer;
    private BlindMan blindMan;
    public Chap(){ //Needed for Initialization with Json File

    }


    private int lastX, lastY;

    /*
    Move Chap in direction x and direction y on game map
    Conditional check to make sure chap is:
        moving inside the map
        onto a tile he can move into
    If first move replace chap tile on initialization with new Free Tile
     */
    public void move(int dx, int dy, Game game) {
        boolean blindManPresent = false;
        int newX = this.x + dx;
        int newY = this.y + dy;
        if(blindMan!=null){
             blindManPresent = (blindMan.getX() == newX && blindMan.getY()== newY);
        }

        if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight() && !blindManPresent) {
            Tile tile = game.findTile(newX, newY);
            if (tile.canMoveInto() ) {
                lastX = this.x;
                lastY = this.y;
                this.x = newX;
                this.y = newY;
                tile.interact(this, game); // Interact with the tile Chap moves onto
                if(blindMan!=null){blindMan.interact();}

                // Start timer on the first move
                if (firstMove) {
                    //levelTimer.start();
                    firstMove = false;
                    game.setGame();
                }
            }
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addBlindMan(BlindMan b){this.blindMan = b;}

    public int getLastX(){return this.lastX;}
    public int getLastY(){return this.lastY;}

}
