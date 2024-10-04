package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.domain.Color;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Render extends JPanel {
    private Tile[][] board;
    private int boardWidth;
    private int boardHeight;
    public Game game;

    private int focusWidth = 9;  // Focus area width, i.e., 9x9 grid around Chap
    private int focusHeight = 9;
    private Chap chap;

    private Map<String, Image> images;

    public Clip clip;
    private static final int TILE_SIZE = 32;

    public Render(String gameFile) throws IOException {
        // Load the game using the loadGame method from Persistency
        this.game = Persistency.loadGame(gameFile);

        // Initialize board dimensions
        this.boardWidth = game.getWidth();
        this.boardHeight = game.getHeight();
        this.board = new Tile[boardHeight][boardWidth];

        //this.chap = game.getChap();
        loadImages();
    }

    private void loadImages() throws IOException {
        //images = Persistency.loadImages(); //javafx needs to be changed to swing
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTiles(g);
        drawPlayer(g);
    }

    private void drawTiles(Graphics g) {
        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {
                Tile tile = game.findTile(col, row);
                if (tile != null) {
                    Image tileImage = images.get(tile.getClass().getSimpleName());
                    if (tileImage != null) {
                        g.drawImage(tileImage, col * 32, row * 32, null); // Assuming tile size is 32x32
                    }
                }
            }
        }
    }

    // Draw Chap on the screen
    private void drawPlayer(Graphics g) {
        Chap chap = game.getChap();
        if (chap != null) {
            Image chapImage = images.get("Chap");
            if (chapImage != null) {
                g.drawImage(chapImage, chap.getX() * 32, chap.getY() * 32, null);
            }
        }
    }

    public void playBackgroundMusic() {}

    public void stopBackgroundMusic() {}
}
