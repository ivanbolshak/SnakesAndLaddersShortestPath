package com.snakesladders.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @Test
    public void testBoardCreationAndSnakesLadders() {
        Board board = new Board(5, 6);

        assertEquals(5, board.getNumOfRows());
        assertEquals(6, board.getNumOfColumns());
        assertEquals(30, board.getTotalCells());

        board.addSnake(27, 1);
        board.addLadder(3, 22);

        assertTrue(board.hasSnake(27));
        assertTrue(board.hasLadder(3));
        assertEquals(1, board.getNextPosition(27));
        assertEquals(22, board.getNextPosition(3));
    }


    @Test
    public void testValidation() {
        int numRows = 5;
        int numCols = 6;

        Board board = new Board(numRows, numCols);
        int lastPosition = numRows * numCols;

        // Test invalid snake positions
        assertThrows(IllegalArgumentException.class, () -> board.addSnake(5, 5), "Snake head and tail cannot be the same");
        assertThrows(IllegalArgumentException.class, () -> board.addSnake(5, 10), "Snake tail cannot be ahead of its head");
        assertThrows(IllegalArgumentException.class, () -> board.addSnake(31, 5), "Snake head is out-of-bounds");

        assertThrows(IllegalArgumentException.class, () -> board.addLadder(5, 5), "Ladder bottom and top cannot be the same");
        assertThrows(IllegalArgumentException.class, () -> board.addLadder(10, 5), "Ladder top cannot be below its bottom");
        assertThrows(IllegalArgumentException.class, () -> board.addLadder(5, 31), "Ladder top is out-of-bounds");

        assertThrows(IllegalArgumentException.class, () -> board.addSnake(1, 5), "Should not allow snake at position 1");
        assertThrows(IllegalArgumentException.class, () -> board.addSnake(lastPosition, 5), "Should not allow snake at the last position");

        assertThrows(IllegalArgumentException.class, () -> board.addLadder(1, 5), "Should not allow ladder at position 1");
        assertThrows(IllegalArgumentException.class, () -> board.addLadder(lastPosition, 5), "Should not allow ladder at the last position");

        board.addSnake(10, 5);
        assertThrows(IllegalArgumentException.class, () -> board.addLadder(10, 15), "Should not allow ladder at a position that already has a snake");

        board.addLadder(15, 20);
        assertThrows(IllegalArgumentException.class, () -> board.addSnake(15, 5), "Should not allow snake at a position that already has a ladder");
    }

}
