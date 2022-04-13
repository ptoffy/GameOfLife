package pt.gameoflife.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.CyclicBarrier;

public class BoardHandler {
    private Board gameState, nextGameState;
    private final int threadCount = 8;
    private CyclicBarrier synchronizationBarrier;
    private final PropertyChangeSupport propertyChangeSupport;
    private UpdateThread[] updateThreads;
    private int updateSpeed = 500;

    public BoardHandler() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.synchronizationBarrier = null;
    }

    public void initialize() {
        gameState = new Board();
        nextGameState = new Board();
        gameState.initialize();
        propertyChangeSupport.firePropertyChange("board", null, gameState);
    }

    private void onUpdateComplete() {
        if (synchronizationBarrier == null)
            return;
        Board temp = gameState;
        gameState = nextGameState;
        nextGameState = temp;
        propertyChangeSupport.firePropertyChange("board", null, gameState);
        try {
            Thread.sleep(updateSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean getCellState(int x, int y) {
        return gameState.getCellState(x, y);
    }

    public void stop() {
        if (synchronizationBarrier == null)
            return;
        for (UpdateThread thread : updateThreads) {
            thread.end();
        }
        synchronizationBarrier.reset();
        synchronizationBarrier = null;
        updateThreads = null;
    }

    public void updateGameState(Rule rule) {
        if (this.synchronizationBarrier != null)
            stop();
        this.updateThreads = new UpdateThread[threadCount];
        this.synchronizationBarrier = new CyclicBarrier(threadCount, this::onUpdateComplete);
        for (int i = 0; i < threadCount; i++) {
            updateThreads[i] = new UpdateThread(gameState, nextGameState, threadCount, i, synchronizationBarrier, rule);
            updateThreads[i].start();
        }
    }

    public void setUpdateSpeed(int updateSpeed) {
        this.updateSpeed = updateSpeed;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
}
