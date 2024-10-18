package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.app.Gui;

public class ExitTile extends Tile {

    public ExitTile() {
        this.canMoveInto = true;
    }

    private Gui getCurrentGui() { return Gui.getCurrentInstance(); }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }
    /*
    Marks level as complete and moves on to next state
     */
    @Override
    public void interact(Chap chap, Game game) {
        Gui currentGui = getCurrentGui();
        game.completeLevel(currentGui); // Calls a method to mark the level as complete
    }
}
