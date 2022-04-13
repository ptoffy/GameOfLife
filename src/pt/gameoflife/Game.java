package pt.gameoflife;

import pt.gameoflife.controller.Player;
import pt.gameoflife.model.BoardHandler;
import pt.gameoflife.view.Frame;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        BoardHandler boardHandler = new BoardHandler();
        Frame frame = new Frame(boardHandler);
        new Player(frame, boardHandler);

        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

}
