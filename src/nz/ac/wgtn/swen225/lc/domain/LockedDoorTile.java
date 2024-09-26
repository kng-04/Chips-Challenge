package nz.ac.wgtn.swen225.lc.domain;

public class LockedDoorTile extends Tiles{
    private String keyColor;

    public LockedDoorTile(String keyColor) {
        this.keyColor = keyColor;
        this.canMoveInto = false; // Default is locked
    }

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    @Override
    public void interact(Chap chap, Game game) {
        if (chap.hasKey(this.keyColor)) {
            System.out.println("Door unlocked with " + keyColor + " key!");
            this.canMoveInto = true; // Door is now unlocked
        } else {
            throw new IllegalStateException("Chap doesn't have the correct key!");
        }
    }
}
