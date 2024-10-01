package nz.ac.wgtn.swen225.lc.domain;


public class Chap extends Characters{

    public void move(int dx, int dy, Game game) {
        int newX = this.x + dx;
        int newY = this.y + dy;

        if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight()) {
            Tile tile = game.findTile(newX, newY);
            if (tile.canMoveInto()) {
                this.x = newX;
                this.y = newY;
                tile.interact(this, game); // Interact with the tile Chap moves onto
            } else {
                System.out.println("Chap cannot move onto that tile!");
            }
        }
    }

}
