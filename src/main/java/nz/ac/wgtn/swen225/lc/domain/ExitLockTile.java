package nz.ac.wgtn.swen225.lc.domain;

public class ExitLockTile extends Tile {

    /*
    Empty ExitLock constructor
     */
        public ExitLockTile() {
            this.canMoveInto = false; // Default is locked
        }

        @Override
        public boolean canMoveInto() {
            return this.canMoveInto;
        }

        /*
        Checks if all treasures are picked up
        Unlocks and is replaced by new FreeTile
         */
        @Override
        public void interact(Chap chap, Game game) {
            if (game.treasuresLeft() == 0) { //if chap has picked up all the treasures
                game.replaceTileWith(new FreeTile(this.x, this.y)); //remove lock and replace with FreeTile
            } else {
                throw new IllegalStateException("Chap has not picked up all the treasure!");
            }
        }
}
