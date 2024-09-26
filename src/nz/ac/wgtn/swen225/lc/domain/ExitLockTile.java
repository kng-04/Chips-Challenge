package nz.ac.wgtn.swen225.lc.domain;

public class ExitLockTile extends Tiles{

        public ExitLockTile() {
            this.canMoveInto = false; // Default is locked
        }

        @Override
        public boolean canMoveInto() {
            return this.canMoveInto;
        }

        @Override
        public void interact(Chap chap, Game game) {
            if (chap.getTreasuresCollected()==game.getTotalTreasures()) {
                this.canMoveInto = true;
            } else {
                throw new IllegalStateException("Chap has not picked up all the treasure!");
            }
        }
}
