package nz.ac.wgtn.swen225.lc.domain;


public class Chap extends Characters{

    //on creation set first move to true, after it is false
    boolean firstMove = true;


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
                if(firstMove){
                    game.addTile(new FreeTile(this.x - dx, this.y - dy));
                    this.firstMove = false;
                }
            } else {
                System.out.println("Chap cannot move onto that tile!");
            }
        }
    }

}
