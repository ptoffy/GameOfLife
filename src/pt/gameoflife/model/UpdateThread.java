package pt.gameoflife.model;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UpdateThread extends Thread {
    private Board source, destination;
    private final int threadCount, threadIndex;
    private final CyclicBarrier synchronizationBarrier;
    private boolean hasEnded = false;
    private final Rule rule;


    public UpdateThread(Board source, Board destination, int threadCount,
                        int threadIndex, CyclicBarrier synchronizationBarrier, Rule rule) {
        this.source = source;
        this.destination = destination;
        this.threadCount = threadCount;
        this.threadIndex = threadIndex;
        this.synchronizationBarrier = synchronizationBarrier;
        this.rule = rule;
    }

    @Override
    public void run() {
        while (!hasEnded) {
            final int gridWidth = source.getBoardSize() - 2,
                    gridHeight = source.getBoardSize() - 2;
            final int cellsToProcess = gridWidth / threadCount + 1;
            main:
            for (int i = 0; i < gridHeight; i++) {
                final int currentThread = (threadIndex + i) % threadCount;
                final int stopAt = Math.min((currentThread + 1) * cellsToProcess, gridWidth);
                for (int j = currentThread * cellsToProcess; j < stopAt; j++) {
                    if (hasEnded)
                        break main;
                    destination.setCellState(i + 1, j + 1, rule.nextState(i + 1, j + 1, source));
                }
            }
            synchronized (rule) {
                Board temp = source;
                source = destination;
                destination = temp;
            }
            try {
                synchronizationBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                break;
            }
        }
    }

    public void end() {
        hasEnded = true;
    }
}
