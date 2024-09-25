package nz.ac.wgtn.swen225.lc.domain;

public class IntoField extends Tiles{

    String keyColor;
    String hint;
    public IntoField(String keyColor, String hint) {
        this.keyColor = keyColor;
        this.canMoveInto = true;
        this.hint = hint;
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("Hint: " + hint); //temporary - display UI.TextBox with hint
    }
}
