package nz.ac.wgtn.swen225.lc.domain;

public class ExitLockTile extends Tile {

        public ExitLockTile() {
            this.canMoveInto = false; // Default is locked
        }

        @Override
        public boolean canMoveInto() {
            return this.canMoveInto;
        }

        @Override
        public void interact(Chap chap, Game game) {
            if (game.treasuresLeft() == 0) {
                game.replaceTileWith(new FreeTile(this.x, this.y));
            } else {
                throw new IllegalStateException("Chap has not picked up all the treasure!");
            }
        }
}
