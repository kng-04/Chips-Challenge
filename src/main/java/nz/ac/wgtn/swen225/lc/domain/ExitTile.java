package nz.ac.wgtn.swen225.lc.domain;

public class ExitTile extends Tiles{
    public ExitTile() {
        this.canMoveInto = true;
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("Chap reached the exit! Level complete.");
        chap.completeLevel(); // Calls a method to mark the level as complete
    }
}
