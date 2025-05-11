package com.snakesladders.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int numOfRows;
    private final int numOfColumns;
    private final int totalCells;
    private final Map<Integer, Integer> snakes;     //head, tail
    private final Map<Integer, Integer> ladders;    //bottom, top

    public Board(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.totalCells = numOfRows * numOfColumns;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public void addSnake(int head, int tail) {
        if (hasLadder(head)) {
            throw new IllegalArgumentException("Position " + head + " already has a ladder");
        }
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be greater than tail");
        }
        if (head > totalCells || 1 > tail) {
            throw new IllegalArgumentException("Snake positions must be within board bounds");
        }
        if (head == totalCells) {
            throw new IllegalArgumentException("Snake cannot be placed at the first or last position");
        }
        snakes.put(head, tail);
    }

    public void addLadder(int bottom, int top) {
        if (hasSnake(bottom)) {
            throw new IllegalArgumentException("Position " + bottom + " already has a snake");
        }
        if (top <= bottom) {
            throw new IllegalArgumentException("Ladder top must be greater than bottom");
        }
        if (top > totalCells || 1 > bottom) {
            throw new IllegalArgumentException("Ladder positions must be within board bounds");
        }
        if (bottom == 1) {
            throw new IllegalArgumentException("Ladder bottom cannot be placed at the first position");
        }
        ladders.put(bottom, top);
    }

    public int getNextPosition(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        } else if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        return position;
    }

    public boolean isWinningPosition(int position) {
        return position == totalCells;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public Map<Integer, Integer> getSnakes() {
        return new HashMap<>(snakes);
    }

    public Map<Integer, Integer> getLadders() {
        return new HashMap<>(ladders);
    }

    public boolean hasSnake(int position) {
        return snakes.containsKey(position);
    }

    public boolean hasLadder(int position) {
        return ladders.containsKey(position);
    }
}
