package nz.ac.wgtn.swen225.lc.domain;

import javax.swing.*;

public class InfoFieldTile extends Tile {
    /*
    Empty InfoFieldTile contructor
     */
    public InfoFieldTile(){this.canMoveInto = true;}
    @Override
    public boolean canMoveInto() {
        return this.canMoveInto; //Default always set to true
    }

    /*
    Prints text with hint of current level
     */
    @Override
    public void interact(Chap chap, Game game) {
        ImageIcon icon = null;
        try {icon = new ImageIcon("images/InfoFieldTile.jpg");}
        catch (Exception e) {e.printStackTrace();}

        JOptionPane.showMessageDialog(null,
                "Hint:\n Collect the keys to unlock the doors and all the treasure to escape!!!",
                "Hint", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}

