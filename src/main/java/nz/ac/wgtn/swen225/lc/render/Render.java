package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.app.Gui;
import nz.ac.wgtn.swen225.lc.app.Controller;
import nz.ac.wgtn.swen225.lc.app.LevelTimer;
import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Render extends JPanel {
    private Game game;
    private final Map<String, BufferedImage> images;
    private final List<CoordinateEntity> entities = new ArrayList<>();
    private final int IMG_SIZE = 32; // 32x32 tile size
    private final LevelTimer levelTimer;
    private int currentLevel;

    public Render(Game game, Map<String, BufferedImage> images, int currentLevel) {
        if (game == null || images == null) {
            throw new IllegalArgumentException("Game and images must not be null");
        }

        this.game = game;
        this.images = images;
        this.levelTimer = levelTimer;
        this.currentLevel = currentLevel;
        updateEntities();
    }

    private void updateEntities() {
        entities.clear();
        entities.addAll(game.getTiles());
        entities.addAll(game.getCharacters());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clears the panel before painting

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Clear and get the latest entities (tiles & characters)
        List<CoordinateEntity> entities = new ArrayList<>();
        entities.addAll(game.getTiles());
        entities.addAll(game.getCharacters());

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

    /**
     * Triggers re-rendering when entities in the game change or move.
     */
    public void updateRender() {
        updateEntities();
        repaint();
    }

    public void resetChapPosition() {
        // Assuming Chap is a single instance in the game.
        Characters character = game.getCharacters().get(0); // Get the Chap instance from the game
        if (character instanceof Chap) {
            Chap chap = (Chap) character;
            chap.setPosition(0, 0); // Reset position to (0, 0) or to the starting position of the level
            updateRender(); // Update the render to reflect the new position
        }
    }

    public void onReachEndTile() {
        levelTimer.stop();
        long seconds = levelTimer.getElapsedTime();

        // Show win screen with time taken
        JOptionPane.showMessageDialog(null,
                "Level Completed!\n" +
                        "Time taken: " + seconds + " seconds.",
                "Level Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Advance to the next level
        if (game.hasNextLevel()) {
            int nextLevel = currentLevel + 1;
            Gui guiInstance = (Gui) SwingUtilities.getWindowAncestor(this);
            guiInstance.loadLevel(nextLevel);
        } else {
            // Game finished
            JOptionPane.showMessageDialog(null, "Congratulations! You have completed all levels!");
            Controller.frame().dispose(); // Close the game window
        }
    }

    public void playBackgroundMusic() {}
    public void stopBackgroundMusic() {}
}
