package nz.ac.wgtn.swen225.lc.domain;

public class KeyTile extends Tile {

    //Color of current key
    private Color color;

    /*
    Empty Key Constructor
     */
    public KeyTile(){this.canMoveInto = true;}

    /*
    Key Constructor with parameters
     */
    public KeyTile(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.canMoveInto = true;
    }
    @Override
    public boolean canMoveInto(){
        return this.canMoveInto; //default always set to true
    }


    /*
    Collects Current KeyTile and allows for LockedDoors of same color to be open
    Replaces KeyTile with new FreeTile
     */
    @Override
    public void interact(Chap chap, Game game){
        game.collectTile(this);
        unlockDoor(game);
        game.replaceTileWith(new FreeTile(this.x, this.y));
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

    //Unlocks all Doors of current color temporarily while chap has this key in inventory
    public void unlockDoor(Game game){
        for(Tile t : game.getTiles()){
            if(t instanceof LockedDoorTile) {
                if (t.getColor().equals(this.color)) {
                    t.setCanMoveInto();
                }
            }
        }
    }


}
