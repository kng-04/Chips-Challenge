package nz.ac.wgtn.swen225.lc.domain;

public class ExitTile extends Tile {
    /*
    Empty ExitTile constructor
     */
    public ExitTile() {
        this.canMoveInto = true;
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }
    /*
    Marks level as complete and moves on to next state
     */
    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("Chap reached the exit! Level complete.");
        game.completeLevel(); // Calls a method to mark the level as complete
    }
}
