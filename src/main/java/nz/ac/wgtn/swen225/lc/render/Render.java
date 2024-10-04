package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.domain.KeyTile;
import nz.ac.wgtn.swen225.lc.domain.Tile;
import nz.ac.wgtn.swen225.lc.domain.TreasureTile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Render extends JPanel {
    private Tile[][] board;
    private int x;
    private int y;
    private List<KeyTile> keys;
    private List<TreasureTile> treasure;
    public Game game;
    public Clip clip;

    public Render(String gameFile) throws IOException {
        // Load the game using the loadGame method from Persistency
        this.game = Persistency.loadGame(gameFile);

        // Initialize board dimensions
        this.x = game.getWidth();
        this.y = game.getHeight();
    }

    public void paintComponent(Graphics g) {}

    public void playBackgroundMusic() {}

    public void stopBackgroundMusic() {}
}
