package nz.ac.wgtn.swen225.lc.domain;

import java.util.Random;

public class BlindMan extends Characters{

    public BlindMan(){}

    public BlindMan(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int dx, int dy, Game game) {
        Random rand = new Random();
        int newX = this.x + rand.nextInt(-1, 1);
        int newY = this.y + rand.nextInt(-1, 1);

        if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight()) {
            Tile tile = game.findTile(newX, newY);
            if (tile instanceof FreeTile) {
                this.x = newX;
                this.y = newY;
            } else {
                this.move(0, 0,game );
            }
        }
    }


}
