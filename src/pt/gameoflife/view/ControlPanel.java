package pt.gameoflife.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class ControlPanel extends JPanel {
    private final JButton playPauseButton, resetButton;
    private JSlider slider;

    public ControlPanel() {
        GridBagLayout boxLayout = new GridBagLayout();
        this.setLayout(boxLayout);
        playPauseButton = new JButton("Play");
        resetButton = new JButton("Reset");
        this.add(playPauseButton);
        this.add(resetButton);
        setupSpeedSlider();
    }

    private void setupSpeedSlider() {
        slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
        // Setup title for slider
        TitledBorder titledBorder = new TitledBorder(BorderFactory.createEmptyBorder(), "Speed");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        slider.setBorder(titledBorder);
        // Setup labels
        Hashtable<Integer, JLabel> speedLabels = new Hashtable<>();
        speedLabels.put(100, new JLabel("Min"));
        speedLabels.put(1, new JLabel("Max"));
        slider.setLabelTable(speedLabels);
        // Setup slider
        slider.setInverted(true);
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(1);
        this.add(slider);
    }

    public void setSpeedChangeListener(ChangeListener e) {
        this.slider.addChangeListener(e);
    }

    public void setResetListener(ActionListener e) {
        this.resetButton.addActionListener(e);
    }

    public void setPlayPauseListener(ActionListener e) {
        this.playPauseButton.addActionListener(e);
    }
}
