package nz.ac.wgtn.swen225.lc.domain;


import nz.ac.wgtn.swen225.lc.app.LevelTimer;

public class Chap extends Characters{
    boolean firstMove = true;
    private final LevelTimer levelTimer;

    public Chap(LevelTimer levelTimer) {
        this.levelTimer = levelTimer;
    }

    public Chap(){ //Needed for Initialization with Json File

    }

    public void move(int dx, int dy, Game game) {

        int newX = this.x + dx;
        int newY = this.y + dy;

        if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight()) {
            Tile tile = game.findTile(newX, newY);
            if (tile.canMoveInto()) {
                this.x = newX;
                this.y = newY;
                tile.interact(this, game); // Interact with the tile Chap moves onto

                // Start timer on the first move
                if (firstMove) {
                    levelTimer.start();
                    firstMove = false;
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
