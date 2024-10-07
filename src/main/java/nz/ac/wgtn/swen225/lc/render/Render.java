package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.CoordinateEntity;
import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.domain.Tile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Render extends JPanel {
    private final Game game;
    private final Map<String, BufferedImage> images;
    private final List<CoordinateEntity> entities = new ArrayList<>();
    private final int IMG_SIZE = 32;  // 32x32 tile size

    public Render(Game game, Map<String, BufferedImage> images) {
        this.game = game;
        this.images = images;
        // Collect all entities (tiles and characters)
        entities.addAll(game.getTiles());
        entities.addAll(game.getCharacters());  // Add characters after tiles, so they are drawn on top
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clears the panel before painting

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Render each entity on the board
        for (CoordinateEntity entity : entities) {
            int x = entity.getX() * IMG_SIZE;
            int y = entity.getY() * IMG_SIZE;
            String name = entity.getClass().getSimpleName();

            // If entity is a Tile and has a color, append the color to the name
            if (entity instanceof Tile && ((Tile) entity).getColor() != null) {
                nz.ac.wgtn.swen225.lc.domain.Color color = ((Tile) entity).getColor();
                name += "-" + color.toString();
            }

            // Draw the corresponding image
            BufferedImage image = images.get(name);
            if (image != null) {
                g.drawImage(image, x, y, IMG_SIZE, IMG_SIZE, this);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(IMG_SIZE * game.getWidth(), IMG_SIZE * game.getHeight());
    }
}