package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.domain.Item;
import nz.ac.wgtn.swen225.lc.domain.Tile;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.List;

public class Render extends JPanel {
    private Tile[][] board;
    private int x;
    private int y;
    private List<Item> keys;
    private List<Item> treasure;
    public Game game;
    //public Load load;
    public Clip clip;

    public Render(Game game) {
        this.game = game;
//        this.load = this.game.getLoad();
//        this.keys = load.getKeys();
//        this.treasure = load.getTreasures();
        this.x = game.getWidth();
        this.y = game.getHeight();
//        this.board = load.getMap();
    }
}
