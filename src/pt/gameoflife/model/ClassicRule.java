package pt.gameoflife.model;

public class ClassicRule extends LifeLikeRule {

    /**
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under population.
     * 2. Any live cell with two or three live neighbors lives on to the next generation.
     * 3. Any live cell with more than three live neighbors dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * @param state The state of the cell to be changed
     * @param aliveNeighbourCount Number of alive neighbours
     * @return Whether the state of the cell should be alive or not
     */
    @Override
    public boolean nextState(boolean state, int aliveNeighbourCount) {
        if (state)
            return aliveNeighbourCount >= 2 && aliveNeighbourCount <= 3;
        else
            return aliveNeighbourCount == 3;
    }
}
