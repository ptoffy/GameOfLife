package pt.gameoflife.view;

import pt.gameoflife.model.BoardHandler;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private final static int FRAME_WIDTH = 700, FRAME_HEIGHT = 780;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private JSplitPane splitPane; // Splits frame into main and button panels

    public Frame(BoardHandler boardHandler) {
        this.setupFrame();
        gamePanel = new GamePanel(boardHandler);
        boardHandler.initialize();

        this.setupButtonPanel();
        this.setupSplitPane();

        this.revalidate();
        this.repaint();
    }

    private void setupFrame() {
        this.setTitle("Life");
        this.getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setLayout(new GridLayout());
    }

    private void setupSplitPane() {
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(700);
        splitPane.setTopComponent(gamePanel);
        splitPane.setBottomComponent(controlPanel);
        this.getContentPane().add(splitPane);
        splitPane.setEnabled(false);
    }

    private void setupButtonPanel() {
        this.controlPanel = new ControlPanel();
    }

    public GamePanel getMainPanel() {
        return this.gamePanel;
    }

    public void setSpeedChangeListener(ChangeListener listener) {
        controlPanel.setSpeedChangeListener(listener);
    }

    public void setResetListener(ActionListener listener) {
        controlPanel.setResetListener(listener);
    }

    public void setPlayPauseListener(ActionListener listener) {
        controlPanel.setPlayPauseListener(listener);
    }
}
