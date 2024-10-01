package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.domain.Tile;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.List;

public class render extends JPanel {
    private Tile[][] board;
    private int x;
    private int y;
    private List<Item> keys;
    private List<Item> treasure;
    public Game game;
    public Load load;
    public Clip clip;

    public render(Game game) {
        this.game = game;
        this.load = this.game.getLoad();
        this.keys = load.getKeys();
        this.treasure = load.getTreasures();
        this.x = load.getWidth();
        this.y = load.getHeight();
        this.board = load.getMap();
    }
}
