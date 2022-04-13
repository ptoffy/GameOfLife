package pt.gameoflife.view;

import pt.gameoflife.model.BoardHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamePanel extends JPanel implements PropertyChangeListener, MouseMotionListener {
    private final BoardHandler boardHandler;
    private final int SQUARE_SIZE = 15;
    private boolean mouseOnSquare = false;

    public GamePanel(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        for (int i = 10; i < this.getHeight() - 10; i += SQUARE_SIZE) {
            for (int j = 10; j < this.getWidth() - 10; j += SQUARE_SIZE) {
                g.drawRect(j, i, SQUARE_SIZE, SQUARE_SIZE);
                if (boardHandler.getCellState(j / SQUARE_SIZE, i / SQUARE_SIZE)) {
                    g.setColor(Color.black);
                    g.fillRect(j, i, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.gray);
                }
            }
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("board")) {
            this.revalidate();
            this.repaint();
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseOnSquare = e.getX() > 10 && e.getX() < 700 && e.getY() > 10 && e.getY() < 700;
        this.repaint();
    }
}
