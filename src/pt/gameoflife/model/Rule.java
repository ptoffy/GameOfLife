package pt.gameoflife.model;

public interface Rule {
    boolean nextState(int x, int y, Board grid);
}
