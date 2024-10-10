package nz.ac.wgtn.swen225.lc.domain;

public class KeyTile extends Tile {

    private Color color;

    public KeyTile(){}

    public KeyTile(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    @Override
    public boolean canMoveInto(){
        return true;
    }
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
