package pt.gameoflife.controller;

import pt.gameoflife.model.BoardHandler;
import pt.gameoflife.model.ClassicRule;
import pt.gameoflife.model.Rule;
import pt.gameoflife.view.Frame;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
    private final Frame frame; // View
    private final BoardHandler boardHandler; // Model
    private final Rule rule;
    private boolean isRunning = false;

    public Player(Frame frame, BoardHandler boardHandler) {
        this.frame = frame;
        this.boardHandler = boardHandler;
        setupListeners();
        boardHandler.initialize();
        this.rule = new ClassicRule();
        this.boardHandler.addPropertyChangeListener(frame.getMainPanel());
    }

    public void setupListeners() {
        this.frame.setResetListener(new ResetListener());
        this.frame.setPlayPauseListener(new PlayPauseListener());
        this.frame.setSpeedChangeListener(new SpeedChangeListener());
    }

    public void updateGameState() {
        boardHandler.updateGameState(this.rule);
    }

    public void stop() {
        boardHandler.stop();
    }

    class SpeedChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            boardHandler.setUpdateSpeed(slider.getValue() * 10);
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning)
                boardHandler.initialize();
        }
    }

    class PlayPauseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (isRunning) {
                stop();
                source.setText("Play");
            }
            else {
                updateGameState();
                source.setText("Pause");
            }
            isRunning = !isRunning;
        }
    }
}
