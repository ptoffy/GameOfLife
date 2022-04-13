package pt.gameoflife.model;

public abstract class LifeLikeRule implements Rule {
    public abstract boolean nextState(boolean state, int aliveNeighbourCount);

    @Override
    public boolean nextState(int x, int y, Board grid) {
        return nextState(grid.getCellState(x, y), countAliveNeighbours(x, y, grid));
    }

    private static int count(int x, int y, Board board) {
        final int[] neighborDeltas = new int[] { 0, 1, 0, -1, 1, 0, 1, 1, 1, -1, -1, 0, -1, 1, -1, -1 };
        int neighbourCount = 0;
        for (int k = 0; k < neighborDeltas.length; k += 2)
            if (board.getCellState(x + 1 + neighborDeltas[k], y + 1 + neighborDeltas[k + 1]))
                neighbourCount += 1;
        return neighbourCount;
    }

    private static int countAliveNeighbours(int x, int y, Board grid) {

        int neighbours = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if ((i != 0 || j != 0) && grid.getCellState(x + i, y + j))
                    neighbours += 1;
        return neighbours;
    }
}
