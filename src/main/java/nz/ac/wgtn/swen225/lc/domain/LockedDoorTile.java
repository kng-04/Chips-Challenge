package nz.ac.wgtn.swen225.lc.domain;

public class LockedDoorTile extends Tile {
    private Color color;

    @Override
    public boolean canMoveInto() {
        return false;
    }

    @Override
    public void interact(Chap chap, Game game) {
        if (game.useKey(color)) {
            System.out.println("Door unlocked with " + color + " key!");
            this.canMoveInto = true; // Door is now unlocked
        } else {
            throw new IllegalStateException("Chap doesn't have the correct key!");
        }
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
