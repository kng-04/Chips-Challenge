package nz.ac.wgtn.swen225.lc.domain;

public class LockedDoorTile extends Tile {

    //Color of the LockedDoorTile
    private Color color;

    @Override
    public boolean canMoveInto() {
        return this.canMoveInto;
    }

    /*
    - Unlocks door and replaces LockedDoorTile with a new Free tile
    - Ensures other doors of same color are locked if
    Chap has no more keys with the same color
     */
    @Override
    public void interact(Chap chap, Game game) {
        System.out.println("locked door interacted" );
        if (game.useKey(this.color)) {
            System.out.println("Door unlocked with " + color + " key!");
            game.replaceTileWith(new FreeTile(this.x, this.y));
            lockDoor(game); //Used to lock doors of same color after key is used

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
    /*
    Checks if Chap has another key of the same color
    in order to know whether he can enter another LockedDoorTile of that color or not
     */
    public void lockDoor(Game game){
        boolean hasMatchingKey = false;

        // Check if the player still has a key of the same color in the inventory
        for (Tile t : game.getInventory()) {
            if (t instanceof KeyTile && t.getColor().equals(this.color)) {
                hasMatchingKey = true;  // Chap has a matching key, so no need to lock doors
                break;  // Exit the loop early since we found the key
            }
        }

        // If Chap doesn't have the key, lock all doors of the same color on the map
        if (!hasMatchingKey) {
            for (Tile t : game.getTiles()) {
                if (t instanceof LockedDoorTile && t.getColor().equals(this.color)) {
                    t.setCannotMoveInto();  // Lock the door (set canMoveInto to false)
                    System.out.println("Locked door at (" + t.getX() + ", " + t.getY() + ")");
                }
            }
        }
    }
}
