package pt.gameoflife.model;

import java.util.Random;

public class Board {
    private final int BOARD_SIZE = 250;
    private boolean[][] board;

    public Board() {
        initialize();
    }

    public boolean getCellState(int x, int y) {
        if (x < BOARD_SIZE && x >= 0 && y < BOARD_SIZE && y >= 0)
            return board[x][y];
        return false;
    }

    public void setCellState(int x, int y, boolean state) {
        if (x < BOARD_SIZE && x >= 0 && y < BOARD_SIZE && y >= 0)
            board[x][y] = state;
    }

    public void initialize() {
        this.board = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                setCellState(j, i, new Random().nextInt(99) + 1 < 50);
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }
}
