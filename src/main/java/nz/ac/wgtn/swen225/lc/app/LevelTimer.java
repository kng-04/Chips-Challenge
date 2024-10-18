package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class LevelTimer {
    private final Gui gui;
    private final Timer swingTimer;
    private int remainingSeconds;
    private final JLabel timeLabel;

    public LevelTimer(Gui gui, int seconds, JLabel timeLabel) {
        this.gui = gui;
        this.remainingSeconds = seconds;
        this.timeLabel = timeLabel;

        // Update the label every second
        swingTimer = new Timer(1000, e -> updateTimer());
        swingTimer.start();
    }

    private void updateTimer() {
        if (remainingSeconds > 0) {
            remainingSeconds--;
            gui.updateLabels();
            timeLabel.setText(String.format("Time: %03d", remainingSeconds));
        } else {
            swingTimer.stop(); // Stop the timer when it reaches 0
            timeLabel.setText("Time: 000");
            timeLabel.setForeground(Color.RED);
            JOptionPane.showMessageDialog(gui,"You ran out of time press okay to retry the level");
            gui.createGame("levels/level"+gui.currentLevel+".json");
        }
    }

    public void start() {
        swingTimer.start();
    }

    // Method to stop the timer
    public void stop() {
        swingTimer.stop();
    }

    public void reset(int seconds) {
        stop();
        this.remainingSeconds = seconds;
        timeLabel.setText(String.format("Time: %03d", remainingSeconds));
        start();
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }
}


