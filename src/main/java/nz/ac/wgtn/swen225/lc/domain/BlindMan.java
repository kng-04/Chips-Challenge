package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.app.Gui;

import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlindMan extends Characters{

    private Game game;
    private boolean canMove = true;
    public BlindMan(){}
    public Timer blindManTimer;
    boolean interacted;

    public BlindMan(int x, int y){
        this.x = x;
        this.y = y;
        this.interacted = false;
        startMovement();
    }

    public void setGame(Game game){this.game = game;}

    public void startMovement(){

            blindManTimer = new Timer(700, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(canMove) {
                        move(0, 0, game);  // Handle movement in each tick
                        Gui.renderPanel.repaint();
                    }
                }
            });
            blindManTimer.start();

    }
    @Override
    public void move(int dx, int dy, Game game) {
        if(!interacted) {
            Random rand = new Random();
            int newX = this.x + rand.nextInt(3) - 1;
            int newY = this.y + rand.nextInt(3) - 1;

            if (newX >= 0 && newX < game.getWidth() && newY >= 0 && newY < game.getHeight()) {
                Tile tile = game.findTile(newX, newY);
                if (tile instanceof FreeTile) {
                    this.x = newX;
                    this.y = newY;
                } else {
                    this.move(0, 0, game);
                }
            }
        }
    }
    boolean interact(){
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
                interacted = true;
                blindManTimer.stop();
                return true;  // Chap is found on a surrounding tile
            }
        }
        return false; //if not found return false by default
    }

    public void setCannotMove(){
        canMove = false;  // Disable movement
        if (blindManTimer != null && blindManTimer.isRunning()) {
            blindManTimer.stop();  // Stop the timer
        }}

    public void setCanMove(){
        canMove = true;  // Enable movement
        if (blindManTimer != null && !blindManTimer.isRunning()) {
            blindManTimer.start();  // Restart the timer
        }
    }

}
