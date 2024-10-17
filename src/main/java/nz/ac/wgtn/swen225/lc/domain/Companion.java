package nz.ac.wgtn.swen225.lc.domain;

public class Companion extends Characters{
    private boolean sleeping = true;
    public Companion(){}//empty constructor for Json

    public Companion(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void move(int dx, int dy, Game game){
        if(!sleeping){
            this.x = game.getChap().getLastX();
            this.y = game.getChap().getLastY();
        }else{
            //Do nothing
        }
    }


    boolean checkSpace(Game game){
        int [][]space = {
                {this.x - 1, this.y -1}, {this.x, this.y-1}, {this.x+1, this.y-1}, //top left, top middle, top right
                {this.x -1, this.y},                     {this.x + 1, this.y},     //middle left,    Comp     ,middle right
                {this.x - 1, this.y +1}, {this.x, this.y+1}, {this.x+1, this.y+1}  //bottom left, bottom middle, bottom right
        };
        int tempX, tempY;
        for(int[] temp : space) {
            tempX = temp[0];
            tempY = temp[1];


            // Check if the tile contains Chap
            if (game.getChap().getX() == tempX && game.getChap().getY() == tempY) {
                sleeping = false;
                return true;  // Chap is found on a surrounding tile
            }
        }
        return false; //if not found return false by default
    }
}
