package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;

public class LevelTimer {
    private final Timer swingTimer;
    private int remainingSeconds;
    private final JLabel timeLabel;

    public LevelTimer(int seconds, JLabel timeLabel) {
        this.remainingSeconds = seconds;
        this.timeLabel = timeLabel;

        // Update the label every second
        swingTimer = new Timer(1000, e -> updateTimer());
        swingTimer.start();
    }

    private void updateTimer() {
        if (remainingSeconds > 0) {
            remainingSeconds--;
            timeLabel.setText(String.format("Time: %02d", remainingSeconds));
        } else {
            swingTimer.stop(); // Stop the timer when it reaches 0
            timeLabel.setText("Time: 000");
            // TODO restart level when timer stops
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
        timeLabel.setText(String.format("Time: %02d", remainingSeconds));
        start();
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }
}


