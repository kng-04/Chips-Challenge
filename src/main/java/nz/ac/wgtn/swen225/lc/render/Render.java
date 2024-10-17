package nz.ac.wgtn.swen225.lc.render;

import nz.ac.wgtn.swen225.lc.domain.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Render extends JPanel {
    private Game game;
    private final Map<String, BufferedImage> images;
    private final List<CoordinateEntity> entities = new ArrayList<>();
    private final int IMG_SIZE = 32; // 32x32 tile size
    public Clip clip;
    JPanel pauseLabelPanel;

    public Render(Game game, Map<String, BufferedImage> images) {
        if (game == null || images == null) {
            throw new IllegalArgumentException("Game and images must not be null");
        }

        this.game = game;
        this.images = images;
        setupPauseLabel();
        updateEntities();
    }

    private void updateEntities() {
        entities.clear();
        entities.addAll(game.getTiles());
        entities.addAll(game.getCharacters());
    }

    // Creates a simple label telling the user the game is paused
    private void setupPauseLabel(){
        pauseLabelPanel = new JPanel();
        pauseLabelPanel.setBackground(Color.WHITE);
        var pauseLabel = new JLabel("Game Is Paused! Press ESC to resume");
        pauseLabel.setForeground(Color.RED);
        pauseLabel.setFont(new Font("Sans", Font.BOLD, 24));
        pauseLabelPanel.add(pauseLabel);
        this.add(pauseLabelPanel);
        pauseLabelPanel.setVisible(false);
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

    public void pauseRender() {
        pauseLabelPanel.setVisible(true);
    }
    public void unpauseRender() {
        pauseLabelPanel.setVisible(false);
    }

    public void playBackgroundMusic() {
        File audioFile = new File("images/ThemeSong.wav");
        if (!audioFile.exists()) {
            System.out.println("Audio file not found: " + audioFile.getAbsolutePath());
            return;
        }

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the music
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
            this.clip.flush();
            this.clip.close();
        }
    }
}
