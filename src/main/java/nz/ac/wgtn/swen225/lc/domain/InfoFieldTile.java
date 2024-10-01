package nz.ac.wgtn.swen225.lc.domain;

public class InfoFieldTile extends Tile {

    @Override
    public boolean canMoveInto() {
        return true;
    }

    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("Hint: "); //temporary - display UI.TextBox with hint
    }
}
