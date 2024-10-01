package nz.ac.wgtn.swen225.lc.domain;

public class KeyTile extends Tile {

    private Color color;

    @Override
    public boolean canMoveInto(){
        return true;
    }
    @Override
    public void interact(Chap chap, Game game){
        game.collectTile(this);
        System.out.println("Chap picked up a " + color + " key.");
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
